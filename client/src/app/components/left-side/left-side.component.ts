import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CurrentUser, UserService } from "../../services/user.service";

@Component({
  selector: 'app-left-side',
  templateUrl: './left-side.component.html',
  styleUrls: ['./left-side.component.css']
})
export class LeftSideComponent {
  login : string ='';
  firstname : string='';
  lastname : string='';
  icon : number=0;
  birthday: string='';
  currentUser: CurrentUser = undefined;
  constructor(private userService: UserService, protected router: Router) {
    console.debug('### AppComponent()');
    this.userService.getLogin().subscribe(login => {
      this.login = login || ''; // '' par défaut car si null ou undefined pas de valeur string possible
    });

    this.userService.getFirstname().subscribe(firstname => {
      this.firstname = firstname || '';
    });

    this.userService.getLastname().subscribe(lastname => {
      this.lastname = lastname || '';
    });

    this.userService.getIcon().subscribe(icon => {
      this.icon = icon || 0;
    });

    this.userService.getBirthday().subscribe(birthday => {
      this.birthday = birthday || '';
    });
    this.userService.currentUserObservable.subscribe(currentUser => this.currentUser = currentUser);
  }


  signout() {
    this.userService.signout().subscribe(_ => this.router.navigate(['signIn']));
  }


}
