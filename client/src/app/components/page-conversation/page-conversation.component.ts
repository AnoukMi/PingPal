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
export class PageConversationComponent implements OnInit{
  conversation!: ConversationDTO;
  interlocutor: string = '';
  @ViewChild('messageInput') messageInput!: ElementRef;
  message = new FormControl();

  constructor(private discussionService: DiscussionService,
              private activatedRoute: ActivatedRoute) {

    // Know which conversation to display
    let _interlocutor = '';
    this.activatedRoute.params.subscribe(params => {
      _interlocutor = params['recipient'];
      this.discussionService.getConversation(_interlocutor)
        .subscribe(
          conversation => {
            this.conversation = conversation;
          });
      this.interlocutor = _interlocutor;
    });
  }

  ngOnInit(){
    setInterval(() => {
      this.getConversation();
    }, 1000);
  }

  getConversation(){
    this.discussionService.getConversation(this.interlocutor)
      .subscribe(conversation => {
        this.conversation = conversation;
      });
  }
  onSend() {
    console.log(`### sending the message`);
    if (!this.message.value?.trim()) return;
    if(this.message.value == '') return;

    this.discussionService.sendMessage(<ConversationDTO>this.conversation, this.interlocutor, this.message.value);

    // Clear the box to write messages
    this.message.setValue('');
    this.message.reset();
  }
}
