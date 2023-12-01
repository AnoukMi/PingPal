import { Component, OnInit } from '@angular/core';
import { ContactProfileService } from "../../services/contact.service";
import { Contact } from "../../models/contact";

@Component({
  selector: 'app-current-friends',
  templateUrl: './current-friends.component.html',
  styleUrls: ['./current-friends.component.css']
})
export class CurrentFriendsComponent implements OnInit {
  friends: Contact[] = [];

  constructor(private contactProfileService: ContactProfileService) {}

  ngOnInit(): void {
    this.contactProfileService.getAllUsers().subscribe(
      (contacts: Contact[]) => {
        this.friends = contacts;
      },
      (error) => {
        console.error('Error when getting users', error);
      }
    );
  }

}
