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
  @Input() discussion!: Discussion;
  message = new FormControl();
  @Input() conversation!: ConversationDTO;
  @Input() interlocutor: string = '';
  constructor(private discussionService: DiscussionService){
    console.debug(`### DiscussionComponent()`);
  }

  // @Input() messages!: MessageDTO[];
  //
  // constructor(private discussionService: DiscussionService) {
  // }
  //
  //   ngOnInit() {
  //       this.getMessages();
  //
  //       // Rafraîchir la liste des messages toutes les secondes
  //       // setInterval(() => {
  //       //     this.getMessages();
  //       // }, 1000);
  //   }
  //
  //   getMessages() {
  //       this.discussionService.getMessages(this.discussion.interlocutor)
  //           .subscribe(newMessages => {
  //                   for (let message of newMessages) {
  //                     // We only add the new messages in the list of messages
  //                       if (!this.messages.includes(message)) {
  //                           this.messages.push(message);
  //                       }
  //                   }
  //               }
  //           );
  //   }

}
