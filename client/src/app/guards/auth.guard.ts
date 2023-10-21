import { CanActivateFn, Router } from '@angular/router';
import { inject } from "@angular/core";
import { UserService } from '../api';
import { map } from "rxjs";

/**
 * Guard used to prevent access to routes needing the user to be authenticated.
 */
export const authGuard: CanActivateFn = (route, state) => {

  const userService = inject(UserService);
  const router = inject(Router);

  // Attempt to get the current user of the app
  return userService.currentUser.pipe(
    map(currentUser => {

      // If set, the user is authenticated: let the user access the route
      if (currentUser)
        return true;

      // If not set, the user is not authenticated: redirect to the signin page (set the return URL to guarded route)
      router.navigate(['signin'], { queryParams: { returnURL: state.url } });
      return false;
    })
  );
};

