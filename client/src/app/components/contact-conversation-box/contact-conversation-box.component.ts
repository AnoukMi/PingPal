import {Component, Input} from '@angular/core';
import {Router} from "@angular/router";
import {ConversationDTO} from "../../api";
import {ContactProfileService} from "../../services/contact.service";
import {Contact} from "../../models/contact";

@Component({
  selector: 'app-contact-conversation-frame',
  templateUrl: './contact-conversation-box.component.html',
  styleUrls: ['./contact-conversation-box.component.css']
})


export class ContactConversationBoxComponent {
  conversation!: ConversationDTO;
  @Input() interlocutor: string = '';
  @Input() lastMessageBody: string = '';
  @Input() lastMessageTime: number = 0;
  contact!: Contact;
  read: boolean = false;

  constructor(private contactProfileService: ContactProfileService,
              private router: Router) {

      if(this.interlocutor.endsWith("@pingpal")) {
        this.contactProfileService.getOneUser(this.interlocutor.split("@")[0]).subscribe(
          contact => {
            this.contact = contact;
          });
      }
  }

  changeStatus() {
    this.read = true;
    this.router.navigate(['/conversation', this.interlocutor]);
  }

  isPartOfPingpal(){
    return this.interlocutor.endsWith("@pingpal");
  }

  lastMessage(){
    return this.conversation.messagesDTOS[this.conversation.messagesDTOS.length-1].body;
  }

  lastMessageDate(){
    const time = new Date(this.lastMessageTime * 1000);

    const hours = time.getHours().toString().padStart(2, '0');
    const minutes = time.getMinutes().toString().padStart(2, '0');

    return `${hours}:${minutes}`;
  }
}
