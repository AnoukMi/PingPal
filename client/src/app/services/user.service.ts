import { Injectable } from '@angular/core';
import { catchError, map, Observable, of, Subject } from "rxjs";
import { AuthenticationService, ProfileService, FullUserDTO } from "../api";
import { ContactProfileService} from "./contact.service";


// Information about the current user of the app:
// - user profile if signed-in
// - null if not signed-in
// - undefined if unknown yet
export type CurrentUser = FullUserDTO | null | undefined;

@Injectable({
  providedIn: 'root'
})
export class UserService {

  // Current user of the app (unknown yet)
  private _currentUser: CurrentUser = undefined;

  // Subject and Observable allowing subscribers to be informed about a change of the current user
  private currentUserSubject = new Subject<CurrentUser>();
  currentUserObservable = this.currentUserSubject.asObservable();


  // @ts-ignore
  /**
   * Get the current user of the app.
   * @return the profile of the user if signed-in, null if not signed-in, or undefined if unknown.
   */
  get currentUser(): Observable<CurrentUser> {

    // If the current user is already known, return it directly
    if (this._currentUser !== undefined)
      return of(this._currentUser);

    // Otherwise, try to get his profile from the backend
    return this.profile().pipe(
      map(userProfile => {

        // Remember current user (it can be null if not signed in) and inform subscribers
        this.currentUser = userProfile;
        return userProfile;
      }),
      catchError(error => {

        // Current user is still unknown because of an unknown error (ie. server unreachable)
        this.currentUser = undefined;
        return of(undefined);
      })
    );
  }

  /**
   * Set the current user of the app and inform subscribers.
   * @param currentUser The profile of the user if signed-in, null if not signed-in, or undefined if unknown
   */
  private set currentUser(currentUser: CurrentUser) {
    this._currentUser = currentUser;
    this.currentUserSubject.next(currentUser);
  }

  constructor(private authenticationService: AuthenticationService, private profileService: ProfileService, private contactService : ContactProfileService) {
    console.debug('### UserService()');
  }

  /**
   * Wrapper of the POST /user/signup endpoint.
   * @param login
   * @param password
   * @param remember
   * @param icon
   * @param firstname
   * @param lastname
   * @param birthday
   * @param address
   */
  signup(login: string, password: string, remember: boolean, icon: number, firstname: string, lastname: string,
         birthday: string, address: string) {
    console.debug(`### signing up as ${login}...`);
    return this.authenticationService.userSignupPost({ login, password, remember, icon, firstname, lastname,
      birthday, address }).pipe(
      map(_ => console.debug('### signed up as', login))
    );
  }

  /**
   * Wrapper of the POST /user/signin endpoint.
   * @param login
   * @param password
   * @param remember
   */
  signin(login: string, password: string, remember: boolean) {
    console.debug(`### signing in as ${login}...`);
    this.contactService.signOnline(login,remember);
    return this.authenticationService.userSigninPost({ login, password, remember }).pipe(
      map(_ => {
        console.debug('### signed in as', login);
        this.currentUser = undefined; // current user profile is unknown (it will be refreshed from backend upon next access)
        return;
      }),
      catchError(error => {
        if (error.status === 409) {
          console.debug('### already signed in');
          return of(void 0);
        }
        throw error;
      })
    );
  }

  delete(login: string, password: string, remember: boolean) {
    console.debug(`### deleting ${login}...`);
    return this.authenticationService.userDeleteDelete({ login, password, remember }).pipe(
      map(_ => {
        console.debug('### deleted account :', login);
        this.signout(); //log out while deleting
        return;
      }),
      catchError(error => {
        if (error.status === 404) {
          console.debug('### this user doesn\'t exist');
          return of(void 0);
        }
        if (error.status === 401) {
          console.debug('### password error');
          return of(void 0);
        }
        throw error;
      })
    );
  }

  /**
   * Wrapper of the GET /user/profile endpoint.
   */
  profile(): Observable<CurrentUser> {
    console.debug('### getting profile...');
    return this.profileService.userProfileGet().pipe(
      map(userProfile => {
        console.debug('### user profile:', userProfile);
        return userProfile;
      }),
      catchError(error => {
        if (error.status === 403) {
          console.debug('### not signed in');
          return of(null); // current user is not signed in
        }
        throw error;
      })
    );
  }

  /**
   * Wrapper of the POST /user/logout endpoint.
   */
  signout() {
    console.debug('### signing out...');
    // @ts-ignore
    this.contactService.logoutOnline(this._currentUser.login);
    return this.authenticationService.userSignoutPost().pipe(
      map(_ => {
        console.debug('### signed out');
        this.currentUser = null;// current user is not signed in
        return;
      })
    )
  }


  /**
   * Wrapper of the PATCH /user/profile endpoint.
   */

  edit(login: string, password: string, remember: boolean, icon: number, firstname: string, lastname: string,
       birthday: string, address: string) {
    this._currentUser = { login, password, remember, icon, firstname, lastname,
      birthday, address }; // udpate currentuser
    this.currentUserSubject.next({ login, password, remember, icon, firstname, lastname,
      birthday, address }); //notify leftsidecomponent to change values of user's data
    console.debug('### editing...');
    return this.profileService.userProfilePatch({ login, password, remember, icon, firstname, lastname,
      birthday, address }).pipe(
      map(_ => {
        console.debug('### edited');
        return;
      })
    )
  }



  /**
   * @return the address of the signed-in user.
   */
  getCurrentUserAddress() {
    return this._currentUser?.login + '@pingpal';
  }

  /**
   * Get the login of the signed-in user.
   * @return login of the user if signed-in, null if not signed-in, or undefined if unknown.
   */
  getLogin(): Observable<string | null | undefined> {
    return this.currentUser.pipe(
      map(user => user ? user.login : null)
    );
  }

  /**
   * Get the firstname of the signed-in user.
   * @return firstname of the user if signed-in, null if not signed-in, or undefined if unknown.
   */
  getFirstname(): Observable<string | null | undefined> {
    return this.currentUser.pipe(
      map(user => user ? user.firstname : null)
    );
  }

  /**
   * Get the lastname of the signed-in user.
   * @return lastname of the user if signed-in, null if not signed-in, or undefined if unknown.
   */
  getLastname(): Observable<string | null | undefined> {
    return this.currentUser.pipe(
      map(user => user ? user.lastname : null)
    );
  }

  /**
   * Get the icon number of the signed-in user.
   * @return icon of the user if signed-in, null if not signed-in, or undefined if unknown.
   */
  getIcon(): Observable<number | null | undefined> {
    return this.currentUser.pipe(
      map(user => user ? user.icon : null)
    );
  }

  getBirthday(): Observable<string | null | undefined> {
    return this.currentUser.pipe(
      map(user => user ? user.birthday : null)
    );
  }
}
