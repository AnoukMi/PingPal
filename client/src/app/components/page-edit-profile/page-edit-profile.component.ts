import { Component } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { UserService } from "../../services/user.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

@Component({
  selector: 'app-page-edit-profil',
  templateUrl: './page-edit-profile.component.html',
  styleUrls: ['./page-edit-profile.component.css']
})
export class PageEditProfileComponent {
  editForm: FormGroup;
  loading = false; // true if sign in is in progress
  error: string | null = null; // error to display
  birthday: string = '';

  constructor(private userService: UserService, private route: ActivatedRoute, private router: Router,
              private formBuilder: FormBuilder) {
    console.debug('### SignupComponent()');
    // Build the signup form with field validators
    this.editForm = this.formBuilder.group({
      avatar: [0, Validators.required],
      password: ['', Validators.required],
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      validPassword: ['', Validators.required],
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
   * Check if the date of the signup form is invalid.
   * @param birthdate The current date selected
   * @return true if the date is invalid (not completed)
   */
  isInvalidDate(birthdate:string){
    const datePattern = /^\d{2}-\d{2}-\d{4}$/;
    return !datePattern.test(birthdate);
  }

  /**
   * Get a given field of the edit form form by its name.
   * @param name The name of the field to get
   * @return the field
   */
  getField(name: string) {
    return this.editForm.controls[name];
  }

  /**
   * Check if a field of the edit form is invalid.
   * @param name The name of the field the check
   * @return true if the field is invalid
   */
  isInvalid(name: string) {
    const field = this.getField(name);
    return field.touched && field.errors;
  }

  onSubmit() {

    // If form is invalid, cancel
    if (this.editForm.invalid)
      return;

    // TODO
  }

  onDelete(formData: any) {
    // delete using form values
    this.loading = true;
    let login = '';
    this.userService.getLogin().subscribe((username) => {// observable username
      // @ts-ignore
      login = username.toString(); // convertit observable en string pour pouvoir l'utiliser en argument de la fonction
    });
    this.userService.delete(login, this.getField('validPassword').value, false).subscribe({
      next: _ => {
        // Redirect to the return URL or to sign in page
        this.router.navigate([this.route.snapshot.queryParams['returnURL'] || 'signIn']);
      },
      error: err => {
        this.error = err.error.message || err.message;
        this.loading = false;
      }
    });
  }

}
