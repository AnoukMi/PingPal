import { Component } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { UserService } from "../../api";

@Component({
  selector: 'app-page-sign-up',
  templateUrl: './page-sign-up.component.html',
  styleUrls: ['./page-sign-up.component.css']
})

export class PageSignUpComponent {
  login: string ="";
  password: string ="";
  firstname: string ="";
  lastname: string ="";
  birthday: string ="";
  loading = false; // true if sign in is in progress
  error: string | null = null; // error to display

  constructor(private userService: UserService, private route: ActivatedRoute, private router: Router) {
    console.debug('### SignupComponent()');
  }

  onSubmit() {

    // Sign in using form values
    this.loading = true;
    this.userService.signup(this.login, this.password, false, 1, this.firstname, this.lastname, "2001-20-06", `${this.login}@pingpal`)
      .subscribe({
      next: _ => {
        // Return to signin
        this.router.navigate(['signIn']);
      },
      error: err => {
        this.error = err.error.message || err.message;
        this.loading = false;

      }
    });
  }
}


