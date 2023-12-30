import {AfterViewChecked, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ConversationDTO} from "../../api";
import {DiscussionService} from "../../services/discussion.service";
import {ActivatedRoute} from "@angular/router";
import { FormControl } from "@angular/forms";

@Component({
  selector: 'app-page-conversation',
  templateUrl: './page-conversation.component.html',
  styleUrls: ['./page-conversation.component.css']
})
export class PageConversationComponent implements OnInit, AfterViewChecked{
  @ViewChild('scrollContainer') scrollContainer!: ElementRef;
  private userScrolled = false;
  conversation!: ConversationDTO;
  interlocutor: string = '';
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
      this.discussionService.getConversation(this.interlocutor)
        .subscribe(conversation => {
          this.conversation = conversation;
        });
    }, 5000);
  }


  onSend() {
    console.log(`### sending the message`);
    if (!this.message.value?.trim()) return;
    if(this.message.value == '') return;

    this.discussionService.sendMessage(<ConversationDTO>this.conversation, this.interlocutor, this.message.value);

    this.message.reset();
    this.resetScrollFlag();
  }

  // next 4 methods are to make sure the scrollbar is at the lowest to show the most recent messages,
  // but still goes up if the user wants to
  ngAfterViewChecked() {
    if (!this.userScrolled) {
      this.scrollToBottom();
    }
  }

  scrollToBottom() {
    this.scrollContainer.nativeElement.scrollTop = this.scrollContainer.nativeElement.scrollHeight;
  }

  onScroll() {
    this.userScrolled = true;
    if (this.scrollContainer.nativeElement.scrollHeight - this.scrollContainer.nativeElement.scrollTop ===
      this.scrollContainer.nativeElement.clientHeight) {
      this.resetScrollFlag();
    }
  }

  resetScrollFlag() {
    this.userScrolled = false;
  }
}
