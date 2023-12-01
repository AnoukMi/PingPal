import { Component } from '@angular/core';
import { Contact } from '../../models/contact';
import { ContactProfileService } from '../../services/contact.service';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

@Component({
  selector: 'app-page-my-friends',
  templateUrl: './page-my-friends.component.html',
  styleUrls: ['./page-my-friends.component.css']
})
export class PageMyFriendsComponent {
  searchForm: FormGroup;
  isSearched: boolean = false;
  selectedContact: Contact = new Contact();


  constructor(private contactProfileService: ContactProfileService, private formBuilder: FormBuilder) {
    this.searchForm = this.formBuilder.group({
      username: ['', Validators.required],
    });
  }

  onSearch() {
    if(this.getField('username').value==""){
      this.isSearched = false;
    }else{
      this.isSearched = true;
      this.getOneUser(this.getField('username').value);
    }
  }

  /**
   * Get a given field of the signup form by its name.
   * @param name The name of the field to get
   * @return the field
   */
  getField(name: string) {
    return this.searchForm.controls[name];
  }

  private getOneUser(username : string) {
    this.contactProfileService.getOneUser(username).subscribe(
      (contact: Contact) => {
        this.selectedContact = contact;
      },
      (error) => {
        console.error('Error when getting the user', error);
      }
    );
  }
}
