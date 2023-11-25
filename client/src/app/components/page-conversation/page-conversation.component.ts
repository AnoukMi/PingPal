import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ConversationDTO, MessageDTO, MessageService} from "../../api";
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
  conversation!: ConversationDTO;
  interlocutor: string = '';
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
      // this.discussion = this.discussionService.getDiscussion(_interlocutor);
      this.discussionService.getConversation(_interlocutor).subscribe(
        conversation => {
          this.conversation = conversation;
        });
      this.interlocutor = _interlocutor;
      this.router.navigate(['/conversation', this.interlocutor]);
    });
  }

  onSend() {
    console.log(`### sending the message`);
    if (!this.message.value?.trim()) return;
    // if(this.message == '') return;

    // this.discussionService.sendMessage(<Discussion>this.discussion, this.message.value);
    this.discussionService.sendMessageConversation(<ConversationDTO>this.conversation, this.interlocutor, this.message.value);
    // this.messageService.userMessageMessagesGet().subscribe(messages => {
    //   this.discussion.messages = messages;
    //   console.log(`### messages length : ${this.discussion.messages.length}`);
    // });

    // Clear the box to write messages
    this.message.setValue('');
  }
}
