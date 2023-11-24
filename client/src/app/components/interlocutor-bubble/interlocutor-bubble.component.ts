import { Component, Input } from '@angular/core';
import {MessageDTO} from "../../api";

@Component({
  selector: 'app-interlocutor-bubble',
  templateUrl: './interlocutor-bubble.component.html',
  styleUrls: ['./interlocutor-bubble.component.css']
})
export class InterlocutorBubbleComponent {
  @Input() message!: MessageDTO;
  //@Input() author: string = '';
  //@Input() body: string = '';
  //@Input() date: string = '';

}
