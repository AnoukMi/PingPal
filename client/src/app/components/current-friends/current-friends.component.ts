import { Component } from '@angular/core';
import {Contact} from "../../models/contact";

@Component({
  selector: 'app-current-friends',
  templateUrl: './current-friends.component.html',
  styleUrls: ['./current-friends.component.css']
})
export class CurrentFriendsComponent {
  friends: Contact[] = [];

  constructor(){

  }
}
