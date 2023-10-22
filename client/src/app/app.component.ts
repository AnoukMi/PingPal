import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CurrentUser, UserService } from "./api";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'actionnable_mockup';
  currentUser: CurrentUser = undefined;

  constructor(private userService: UserService, protected router: Router) {
    console.debug('### AppComponent()');
    this.userService.currentUserObservable.subscribe(currentUser => this.currentUser = currentUser);
  }

  signout() {
    this.userService.signout().subscribe(_ => this.router.navigate(['/signin']));
  }


}
