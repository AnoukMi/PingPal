import {Component, Input} from '@angular/core';
import {Router} from "@angular/router";
import {ConversationDTO} from "../../api";

@Component({
  selector: 'app-contact-conversation-frame',
  templateUrl: './contact-conversation-box.component.html',
  styleUrls: ['./contact-conversation-box.component.css']
})


export class ContactConversationBoxComponent {
  @Input() conversation!: ConversationDTO;
  @Input() interlocutor: string = '';
  @Input() isPartOfPingpal: boolean = false;
  @Input() icon: number = 0;
  lastMsgDate: string = '';
  read: boolean = false;
  constructor(private router: Router) {



    // Does not work currently :
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

  lastMessage(){
    return this.conversation.messagesDTOS[this.conversation.messagesDTOS.length-1].body;
  }
}
