import {Component, Input} from '@angular/core';
import {ConversationDTO} from "../../api";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent{
  @Input() conversation!: ConversationDTO;
  @Input() interlocutor: string = '';
  constructor(){
    console.debug(`### ChatComponent()`);
  }
}
