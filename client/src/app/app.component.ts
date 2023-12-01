import { Component } from '@angular/core';
import { CurrentUser, UserService } from "./services/user.service";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  currentUser: CurrentUser = undefined;

  constructor(private userService: UserService) {
    console.debug('### AppComponent()');
    this.userService.currentUserObservable
      .subscribe(currentUser => this.currentUser = currentUser);
  }


}
