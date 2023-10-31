import { Component, Input } from '@angular/core';
import { MessageDTO } from "../../api";
import { Discussion, DiscussionService } from "../../services/discussion.service";
import { ActivatedRoute, Router } from "@angular/router";
import {UserService} from "../../services/user.service";
import { FormBuilder, FormControl } from "@angular/forms";
import {WebSocketService} from "../../services/web-socket.service";
import { v4 as uuidv4 } from 'uuid';
import {HttpClient} from "@angular/common/http";
import {WebSocketSubject} from "rxjs/internal/observable/dom/WebSocketSubject";
import {webSocket} from "rxjs/webSocket";

@Component({
  selector: 'app-page-conversation',
  templateUrl: './page-conversation.component.html',
  styleUrls: ['./page-conversation.component.css']
})
export class PageConversationComponent {
  discussion!: Discussion;
  messages: MessageDTO[];
  messageInput = new FormControl();

  constructor(private discussionService: DiscussionService,
              private userService: UserService,
              private webSocketService: WebSocketService,
              private activatedRoute: ActivatedRoute) {

    // Know which conversation to display
    let recipient = '';
    this.activatedRoute.params.subscribe(params => {
      recipient = params['recipient'];
      console.log(`### current conversation with ${recipient}`);
    })
    this.discussionService.newDiscussion(recipient);
    for (let dis of this.discussionService.discussions) {
      if (dis.interlocutor === recipient) {
        this.discussion = dis;
        break;
      }
    }
    this.messages = this.discussion.messages;
  }

  // uuidv4() generates a random uuid
  onSend() {
    console.log(`### sending the message`);
    if (!this.messageInput.value?.trim()) return;

    const msg: MessageDTO = {
      recipientID: this.discussion.interlocutor,
      content: this.messageInput.value,
      msgID: uuidv4(),
      authorAddress: this.userService.getCurrentUserAddress(),
      date: new Date().toLocaleDateString(),
      edited: false
    }

    // Add the message to the conversation in the list of messages and in the server, also do the sending by web socket
    this.discussionService.sendMessage(this.discussion, this.messageInput.value);

    // Update the messages
    this.messages = this.discussion.messages;

    // Clear the box to write messages
    this.messageInput.setValue('');
  }
}
