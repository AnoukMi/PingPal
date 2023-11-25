import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {MessageDTO, MessageService} from "../../api";
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
  // messages: MessageDTO[] = [];
  @ViewChild('messageInput') messageInput!: ElementRef;
  message = new FormControl();


  constructor(private discussionService: DiscussionService,
              private messageService: MessageService,
              private activatedRoute: ActivatedRoute,
              private router: Router) {

    // Know which conversation to display
    let _interlocutor = '';
    this.activatedRoute.params.subscribe(params => {
      _interlocutor = params['recipient'];
      console.log(`### current conversation with ${_interlocutor}`);
      this.discussion = this.discussionService.getDiscussion(_interlocutor);
      this.router.navigate(['/conversation', _interlocutor]);
    });
    // this.discussion = new Discussion({interlocutor: recipient, messages: []})
    // this.messages = this.discussion.messages;
  }

  onSend() {
    console.log(`### sending the message`);
    if (!this.message.value?.trim()) return;
    // if(this.message == '') return;

    this.discussionService.sendMessage(<Discussion>this.discussion, this.message.value);
    this.messageService.userMessageMessagesGet().subscribe(messages => {
      this.discussion.messages = messages;
    });
    console.log(`### messages length : ${this.discussion.messages.length}`);

    // Clear the box to write messages
    this.message.setValue('');
  }
}
