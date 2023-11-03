import { Component } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { UserService } from "../../services/user.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";


@Component({
  selector: 'app-page-sign-in',
  templateUrl: './page-sign-in.component.html',
  styleUrls: ['./page-sign-in.component.css']
})

export class PageSignInComponent {
  // Signin form
  signinForm: FormGroup;
  loading = false; // true if sign in is in progress
  error: string | undefined = undefined; // error to display
  constructor(private userService: UserService, private route: ActivatedRoute, private router: Router,
              private formBuilder: FormBuilder) {
    console.debug('### SigninComponent()');

    // Attempt to get the current user of the app
    this.userService.currentUser.subscribe(
      currentUser => {

        // If set, the user is already signed-in, redirect to the return URL or to home page
        if (currentUser)
          this.router.navigate([this.route.snapshot.queryParams['returnURL'] || 'home']);
      }
    );

    // Build the signin form with field validators
    this.signinForm = this.formBuilder.group({
      login: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  /**
   * Get a given field of the signin form by its name.
   * @param name The name of the field to get
   * @return the field
   */
  getField(name: string) {
    return this.signinForm.controls[name];
  }

  /**
   * Check if a field of the signin form is invalid.
   * @param name The name of the field the check
   * @return true if the field is invalid
   */
  isInvalid(name: string) {
    const field = this.getField(name);
    return field.touched && field.errors;
  }

  onSubmit() {

    // If form is invalid, cancel
    if (this.signinForm.invalid)
      return;

    // Sign in using form values
    this.loading = true;
    this.userService.signin(this.getField('login').value, this.getField('password').value,
      false).subscribe({
      next: _ => {

        // Redirect to the return URL or to home page

        this.router.navigate([this.route.snapshot.queryParams['returnURL'] || 'home']);
      },
      error: err => {
        this.error = err.error.message || err.message;
        this.loading = false;
      }
    });
  }
}

