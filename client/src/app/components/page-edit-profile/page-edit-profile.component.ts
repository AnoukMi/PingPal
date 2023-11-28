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
  loading = false; // true if editing in progress
  error: string | null = null; // error to display
  birthday: string = '';
  icon:number=0;

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
    this.userService.getIcon().subscribe(currenticon => {this.icon = currenticon as number});
  }

  /**
   * Change the birthday with the selected date
   * @param birthday The birthday in the date selector
   */
  receiptBirthChange(birthday: string){
    this.birthday=birthday;
  }

  /**
   * Change the icon with the selected icon
   * @param icon The icon selected by click
   */
  receiptIconChange(icon: number){
    this.icon=icon;
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
    // check first if field si not null and if touched (may be valid ?)
    if (field && field.touched) {
      return field.errors ; // then check errors (return true for invalid if there are errors)
    }
    return false;  // if field is undefined or not touched
  }

  /**
   * Send data to the server to edit the user
   */
  onSubmit() {
    // Edit using form values
    this.loading = true;
    let login = '';
    this.userService.getLogin().subscribe((username) => {// observable username
      // @ts-ignore
      login = username.toString(); // convertit observable en string pour pouvoir l'utiliser en argument de la fonction
    });
    // for each field, take the old value if the new value is invalid (empty)
    let edfirstname = '';
    if(this.isInvalid(this.getField('firstname').value)||this.getField('firstname').value==''){
      this.userService.getFirstname().subscribe((userfirst) => {// observable username
        // @ts-ignore
        edfirstname = userfirst.toString(); // convertit observable en string pour pouvoir l'utiliser en argument de la fonction
      });
    } else {
      edfirstname = this.getField('firstname').value;
    }
    let edlastname = '';
    if(this.isInvalid(this.getField('lastname').value)||this.getField('lastname').value==''){
      this.userService.getLastname().subscribe((userlast) => {// observable username
        // @ts-ignore
        edlastname = userlast.toString(); // convertit observable en string pour pouvoir l'utiliser en argument de la fonction
      });
    } else {
      edlastname = this.getField('lastname').value;
    }
    let edbirthday = '';
    if(this.isInvalidDate(this.birthday)){
      this.userService.getBirthday().subscribe((userdate) => {// observable username
        // @ts-ignore
        edbirthday = userdate.toString(); // convertit observable en string pour pouvoir l'utiliser en argument de la fonction
      });
    } else {
      edbirthday = this.birthday;
    }

    // call the service (wrapped route PATCH)
    this.userService.edit(login, this.getField('validPassword').value,
      false, this.icon, edfirstname, edlastname, edbirthday, login+"@pingpal")
      .subscribe({
        next: _ => {
          // Return to home
          this.router.navigate([this.route.snapshot.queryParams['returnURL'] || 'home']);
        },
        error: err => {
          this.error = err.error.message || err.message;
          this.loading = false;

        }
      });
  }

  /**
   * Send data to the server to delete the user
   */

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
        this.userService.signout().subscribe(_ => this.router.navigate(['signIn']));
      },
      error: err => {
        this.error = err.error.message || err.message;
        this.loading = false;
      }
    });
  }

}
