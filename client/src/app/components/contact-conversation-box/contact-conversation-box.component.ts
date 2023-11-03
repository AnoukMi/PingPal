import {Component, Input} from '@angular/core';
import {Contact} from "../../models/contact";
import {Router} from "@angular/router";

@Component({
  selector: 'app-contact-conversation-frame',
  templateUrl: './contact-conversation-box.component.html',
  styleUrls: ['./contact-conversation-box.component.css']
})


export class ContactConversationBoxComponent {
  @Input() contact: Contact = new Contact();
  @Input() statusMessage: boolean = false;

  constructor(private router: Router) {
  }

  changeStatus(){
    this.statusMessage = !this.statusMessage;
    this.router.navigate(['/conversation/', this.contact.username]);
  }
}
