import {Component, Input} from '@angular/core';
import {Contact} from "../../models/contact";

@Component({
  selector: 'app-contact-conversation-frame',
  templateUrl: './contact-conversation-box.component.html',
  styleUrls: ['./contact-conversation-box.component.css']
})


export class ContactConversationBoxComponent {
  @Input() contact: Contact = new Contact();
  @Input() statusMessage: boolean = false;

  constructor() {
  }
}
