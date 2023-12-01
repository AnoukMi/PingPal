import { Component } from '@angular/core';
import {Contact} from "../../models/contact";
import {UserService} from "../../services/user.service";
import { ActivatedRoute, Router } from "@angular/router";
import {ContactProfileService} from "../../services/contact.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

@Component({
  selector: 'app-page-share-message',
  templateUrl: './page-share-message.component.html',
  styleUrls: ['./page-share-message.component.css']
})
export class PageShareMessageComponent {
  shareForm: FormGroup;
  myself: Contact = new Contact();
  login: string = '';

  constructor(private route: ActivatedRoute, private router: Router, private contactProfileService: ContactProfileService, private userService: UserService, private formBuilder: FormBuilder) {
    this.shareForm = this.formBuilder.group({
      message: ['', Validators.required],
    });
    this.userService.getLogin().subscribe(login => {
      this.login = login || ''; // '' par défaut car si null ou undefined pas de valeur string possible
    });
    this.contactProfileService.getOneUser(this.login).subscribe(
      (contact: Contact) => {
        this.myself= contact;
      }
    );
  }

  /**
   * Get a given field of the signup form by its name.
   * @param name The name of the field to get
   * @return the field
   */
  getField(name: string) {
    return this.shareForm.controls[name];
  }

  onPublish(){
    this.contactProfileService.toShareMessage(this.login, this.getField("message").value);
   // this.router.navigate([this.route.snapshot.queryParams['shareMessage']]);
  }

  onPreview(){
    this.myself.setMessage(this.getField("message").value);

  }

}
