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
  /*
  isOnline(login : string) : boolean {
    return //TODO, puis dans html ds boucle for appeler fonction pour voir si en ligne et si oui faire apparaitre mention "Online"
  } */

}
