import { Component } from '@angular/core';
import {Contact} from "../../models/contact";

@Component({
  selector: 'app-current-friends',
  templateUrl: './current-friends.component.html',
  styleUrls: ['./current-friends.component.css']
})
export class CurrentFriendsComponent {
  friends: Contact[];

  constructor(){
    this.friends = [new Contact("Anouk", "Migliavacca", "assets/avatar/f2.png",
        "anouka", new Date(2003, 5, 23))];
  }
}
