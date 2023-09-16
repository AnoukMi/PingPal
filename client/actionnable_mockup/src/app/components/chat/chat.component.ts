import {Component } from '@angular/core';
import { UserBubbleComponent } from "../user-bubble/user-bubble.component";
import { InterlocutorBubbleComponent } from "../interlocutor-bubble/interlocutor-bubble.component";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {
  messages = [
    { UserBubbleComponent, InterlocutorBubbleComponent }
  ];

}
