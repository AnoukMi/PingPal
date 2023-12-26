import { Component, Input } from '@angular/core';
import {MessageDTO} from "../../api";
import {DatePipe} from "@angular/common";


@Component({
  selector: 'app-user-bubble',
  templateUrl: './user-bubble.component.html',
  styleUrls: ['./user-bubble.component.css']
})
export class UserBubbleComponent {
  @Input() message!: MessageDTO;

  constructor() {
  }

  getTime() {

    const time = new Date(this.message.timestamp);

    const hours = time.getHours().toString().padStart(2, '0');
    const minutes = time.getMinutes().toString().padStart(2, '0');

    return `${hours}:${minutes}`;

  }
}
