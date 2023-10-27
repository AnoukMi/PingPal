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

  constructor(private userService: UserService, private route: ActivatedRoute, private router: Router,
              private formBuilder: FormBuilder) {
    console.debug('### SignupComponent()');
    // Build the signup form with field validators
    this.editForm = this.formBuilder.group({
      login: ['', Validators.required],
      password: ['', Validators.required],
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
    });
  }

}
