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
export class PageConversationComponent {
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

  onSend() {
    console.log(`### sending the message`);
    if (!this.message.value?.trim()) return;
    if(this.message.value == '') return;

    this.discussionService.sendMessage(<ConversationDTO>this.conversation, this.interlocutor, this.message.value);

    // Clear the box to write messages
    this.message.setValue('');
    this.messageInput.nativeElement.reset();
    this.message.reset();


    // It does not clear the box, so we reroute on the same page whenever a message is sent
    // this.route.navigate(['/conversation/', this.interlocutor]);
    // this.route.navigate([this.activatedRoute.snapshot.queryParams['returnURL'] || '/conversation/', this.interlocutor]);
  }
}
