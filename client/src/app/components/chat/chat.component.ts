import {Component, ElementRef, Input, ViewChild} from '@angular/core';
import {ConversationDTO, MessageDTO} from "../../api";
import {Discussion, DiscussionService} from "../../services/discussion.service";
import {FormControl} from "@angular/forms";

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
