import {Component, Input} from '@angular/core';
import {Router} from "@angular/router";
import {ConversationDTO} from "../../api";
import {ContactProfileService} from "../../services/contact.service";
import {Contact} from "../../models/contact";
import {DiscussionService} from "../../services/discussion.service";

@Component({
  selector: 'app-contact-conversation-frame',
  templateUrl: './contact-conversation-box.component.html',
  styleUrls: ['./contact-conversation-box.component.css']
})


export class ContactConversationBoxComponent {
  conversation!: ConversationDTO;
  contact!: Contact;
  read: boolean = false;
  @Input() interlocutor: string = '';
  @Input() lastMessageBody: string = '';
  @Input() lastMessageTime: number = 0;


  constructor(private contactProfileService: ContactProfileService,
              private router: Router) {

      if(this.interlocutor.endsWith("@pingpal")) {
        this.contactProfileService.getOneUser(this.interlocutor.split("@")[0]).subscribe(
          contact => {
            this.contact = contact;
            console.log(`contact : ${this.contact.username}`);
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

  lastMessageDate(){
    const time = new Date(this.lastMessageTime);

    const hours = time.getHours().toString().padStart(2, '0');
    const minutes = time.getMinutes().toString().padStart(2, '0');

    return `${hours}:${minutes}`;
  }
}
