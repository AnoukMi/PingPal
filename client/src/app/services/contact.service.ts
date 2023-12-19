import { Injectable } from '@angular/core';
import { catchError, map, Observable, of, Subject, BehaviorSubject } from "rxjs";
import {ContactService, ContactProfileDTO, ProfileService} from "../api";
import {Contact} from "../models/contact";
import {CurrentUser} from "./user.service";

@Injectable({
  providedIn: 'root'
})

export class ContactProfileService {

  private friends: Contact[] = [];

  // Subject and Observable allowing subscribers to be informed about a change of the current user
  private contactInfo = new Subject<Contact>();
  contactInfoObservable = this.contactInfo.asObservable();

  constructor(private contactService: ContactService, private profileService: ProfileService) {
    this.initFriends();
  }

  /**
   * Initialisation de la liste d'utilisateurs
   */
  private initFriends(): void {
    this.contactService.userFriendsGet()
      .pipe(
        map((profiles: ContactProfileDTO[]) => {
          // Converti les profils en instances de Contact
          this.friends = profiles.map(profile => this.convertToContact(profile));
        })
      )
      .subscribe();
  }

  /**
   * Converti un ContactProfileDTO en model Contact
   */

  private convertToContact(profile: ContactProfileDTO): Contact {
    let res : boolean =false;
    this.contactService.userOnlineUserIDGet(profile.userID).subscribe(
      (result: boolean) => {
        res = result;});
    return new Contact(profile.userID, profile.icon, profile.firstname, profile.lastname, profile.birthday, profile.sharedMessage, res);
  }

  /**
   * Wrapper of the GET /user/friend{username} endpoint which convert into Contact model
   */
  getAllUsers(): Observable<Contact[]> {
    return new Observable(observer => {
      observer.next(this.friends);
      observer.complete();
    });
  }

  /**
   * Wrapper of the GET /user/friend{username} endpoint.
   */

  getOneUser(username : string):Observable<Contact>  {
    return this.contactService.userFriendUserIDGet(username).pipe(
      map((contactProfileDTO: ContactProfileDTO) => this.convertToContact(contactProfileDTO))
    );
  }

  /**
   * Wrapper of the POST /user/shareMessage endpoint.
   */
  toShareMessage(user : string, msg : string){
    this.profileService.userShareMessagePost(msg);
    this.friends.forEach(contact => {
      if (contact.username === user) {
        contact.setMessage(msg);
        this.contactInfo.next(contact); // Notifier le Subject
      }
    });
  }

  /**
   * Wrapper of the DELETE /user/shareMessage endpoint.
   */
  deleteSharedMessage(){
    this.profileService.userShareMessageDelete();
  }


  /**
   * Set online attribute when signin in
   */
  signOnline(user : string, online : boolean){
    this.friends.forEach(contact => {
      if (contact.username === user) {
        contact.setOnline(online);
        this.contactInfo.next(contact); // Notifier le Subject
      }
    });
  }

  /**
   * Set online attribute when logging out
   */
  logoutOnline(user : string){
    this.friends.forEach(contact => {
      if (contact.username === user) {
        contact.setOnline(false);
        this.contactInfo.next(contact); // Notifier le Subject
      }
    });
  }

}
