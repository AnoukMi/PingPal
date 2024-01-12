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
  error: string | undefined = undefined; // error to display
  lastClickedButton: string = '';

  constructor(private route: ActivatedRoute, private router: Router, private contactProfileService: ContactProfileService, private userService: UserService, private formBuilder: FormBuilder) {
    this.shareForm = this.formBuilder.group({
      message: ['', [Validators.required, Validators.maxLength(45)]],
    });
    this.userService.getLogin().subscribe(login => {
      this.login = login || '';
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
    // @ts-ignore
    if (this.shareForm.get('message').hasError('maxlength')) {
      this.error = "Too long! Maximum length is 45 characters.";
    } else {
      if(this.lastClickedButton !== 'delete') {
        this.error = "Message published.";
      }
        this.contactProfileService.toShareMessage(this.login, this.getField("message").value);
      this.lastClickedButton = 'publish';

    }
  }

  onPreview(){
    this.myself.setMessage(this.getField("message").value);
  }

  onDelete(){
    this.contactProfileService.deleteSharedMessage();
    this.lastClickedButton = 'delete';
    this.error = "Message deleted.";
  }


  protected readonly undefined = undefined;
}
