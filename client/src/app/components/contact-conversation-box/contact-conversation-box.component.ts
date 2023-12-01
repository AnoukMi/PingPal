import {Component, Input} from '@angular/core';
import {Contact} from "../../models/contact";
import {Router} from "@angular/router";
import {Discussion} from "../../services/discussion.service";
import {ConversationDTO} from "../../api";

@Component({
  selector: 'app-contact-conversation-frame',
  templateUrl: './contact-conversation-box.component.html',
  styleUrls: ['./contact-conversation-box.component.css']
})


export class ContactConversationBoxComponent {
  @Input() conversation!: ConversationDTO;
  @Input() interlocutor: string = '';
  lastMsgDate: string = '';
  read: boolean = false;
  constructor(private router: Router) {

    // Display the time of the last message sent
    // const time = new Date(this.conversation.timestamp);
    //
    // const hours = time.getHours().toString().padStart(2, '0');
    // const minutes = time.getMinutes().toString().padStart(2, '0');
    //
    // this.lastMsgDate = `${hours}:${minutes}`;
  }

  changeStatus() {
    this.read = true;
    this.router.navigate(['/conversation', this.interlocutor]);
  }
}
