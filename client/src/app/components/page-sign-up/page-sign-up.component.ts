import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { UserService } from "../../services/user.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";


@Component({
  selector: 'app-page-sign-up',
  templateUrl: './page-sign-up.component.html',
  styleUrls: ['./page-sign-up.component.css']
})

export class PageSignUpComponent {
  // Signup form
  signupForm: FormGroup;
  loading = false; // true if sign in is in progress
  error: string | null = null; // error to display
  birthday: string = '';


  constructor(private userService: UserService, private route: ActivatedRoute, private router: Router,
              private formBuilder: FormBuilder) {
    console.debug('### SignupComponent()');
    // Build the signup form with field validators
    this.signupForm = this.formBuilder.group({
      login: ['', Validators.required],
      password: ['', Validators.required],
      confpassword: ['', Validators.required],
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
    });
  }

  /**
   * Change the birthday with the selected date
   * @param birthday The birthday in the date selector
   */
  receiptBirthChange(birthday: string){
    this.birthday=birthday;
  }
  /**
   * Get a given field of the signup form by its name.
   * @param name The name of the field to get
   * @return the field
   */
  getField(name: string) {
    return this.signupForm.controls[name];
  }

  /**
   * Check if a field of the signup form is invalid.
   * @param name The name of the field the check
   * @return true if the field is invalid
   */
  isInvalid(name: string) {
    const field = this.getField(name);
    return field.touched && field.errors;
  }

  /**
   * Check if the date of the signup form is invalid.
   * @param birthdate The current date selected
   * @return true if the date is invalid (not completed)
   */
  isInvalidDate(birthdate:string){
    const datePattern = /^\d{2}-\d{2}-\d{4}$/;
    return !datePattern.test(birthdate);
  }

  isInvalidPassword(pass1 : string, pass2 : string){
    return (pass1 !== pass2);
  }

  /**
   * Send data to the server to sign up the user
   */
  onSubmit() {
    // If form is invalid, cancel
    if (this.signupForm.invalid)
      return;

    // Sign up using form values
    this.loading = true;
    this.userService.signup(this.getField('login').value, this.getField('password').value,
      false, 0, this.getField('firstname').value, this.getField('lastname').value,
      this.birthday, `${this.getField('login').value}@pingpal`)
      .subscribe({
        next: _ => {
          // Return to signin
          this.router.navigate([this.route.snapshot.queryParams['returnURL'] || 'signIn']);
        },
        error: err => {
          this.error = err.error.message || err.message;
          this.loading = false;

        }
      });
  }

}

