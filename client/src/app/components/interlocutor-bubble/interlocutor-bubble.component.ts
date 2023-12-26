import { Component, Input } from '@angular/core';
import {MessageDTO} from "../../api";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-interlocutor-bubble',
  templateUrl: './interlocutor-bubble.component.html',
  styleUrls: ['./interlocutor-bubble.component.css']
})
export class InterlocutorBubbleComponent {
  @Input() message!: MessageDTO;

  constructor() {
  }

  getLogin(){
    return this.message.from?.split('@')[0];
  }

  getTime(){
    const time = new Date(this.message.timestamp);

    const hours = time.getHours().toString().padStart(2, '0');
    const minutes = time.getMinutes().toString().padStart(2, '0');

    return `${hours}:${minutes}`;
  }
}
