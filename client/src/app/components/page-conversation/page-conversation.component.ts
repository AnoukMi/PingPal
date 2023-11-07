import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import { MessageDTO } from "../../api";
import { Discussion, DiscussionService } from "../../services/discussion.service";
import {ActivatedRoute, Router} from "@angular/router";
import { FormControl } from "@angular/forms";

@Component({
  selector: 'app-page-conversation',
  templateUrl: './page-conversation.component.html',
  styleUrls: ['./page-conversation.component.css']
})
export class PageConversationComponent{
  discussion!: Discussion;
  messages: MessageDTO[] = [];
  // @ViewChild('messageInput') messageInput!: ElementRef;
  message = new FormControl();

  constructor(private discussionService: DiscussionService,
              private activatedRoute: ActivatedRoute,
              private router: Router) {

    // Know which conversation to display
    let recipient = '';
    this.activatedRoute.params.subscribe(params => {
      recipient = params['recipient'];
      console.log(`### current conversation with ${recipient}`);
    })
    this.discussionService.newDiscussion(recipient);
    this.router.navigate(['/conversation/', recipient]);
    this.discussion = new Discussion({interlocutor: recipient, messages: []})
    this.messages = this.discussion.messages;

  }

  onSend() {
    console.log(`### sending the message`);
    if (!this.message.value?.trim()) return;
    // if(this.message == '') return;

    this.discussionService.sendMessage(this.discussion, this.message.value);

    // Update the messages
    this.messages = this.discussion.messages;

    // Clear the box to write messages
    this.message.setValue('');
  }
}
