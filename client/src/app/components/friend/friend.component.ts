import {Component, Input} from '@angular/core';
import {Contact} from "../../models/contact";

@Component({
  selector: 'app-friend',
  templateUrl: './friend.component.html',
  styleUrls: ['./friend.component.css']
})
export class FriendComponent {
  @Input() contact: Contact = new Contact();
}
