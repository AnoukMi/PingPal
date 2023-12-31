import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {ConversationDTO} from "../../api";
import {ContactProfileService} from "../../services/contact.service";
import {Contact} from "../../models/contact";
import {DiscussionService} from "../../services/discussion.service";
import {Location} from '@angular/common';

@Component({
  selector: 'app-contact-conversation-frame',
  templateUrl: './contact-conversation-box.component.html',
  styleUrls: ['./contact-conversation-box.component.css']
})


export class ContactConversationBoxComponent implements OnInit {
  @Input() conversation!: ConversationDTO;
  @Input() interlocutor: string = '';
  contact!: Contact;
  lastMessageBody: string = '';
  actualConv!: boolean;


  constructor(private contactProfileService: ContactProfileService,
              private discussionService: DiscussionService,
              private router: Router,
              private location: Location){

  }

  ngOnInit() {
    console.log(`conversation with ${this.interlocutor}`);
    this.findContact();
    this.getInfo();

  }

  findContact(){
    if(this.interlocutor.endsWith("@pingpal")) {
      this.contactProfileService.getOneUser(this.interlocutor.split("@")[0]).subscribe(
        contact => {
          this.contact = contact;
        });
    }
  }

  getInfo(){
    this.discussionService.getConversation(this.interlocutor).subscribe(
      conversation => {
        this.conversation = conversation;
        this.lastMessageBody = conversation.messagesDTOS[conversation.messagesDTOS.length - 1].body;
      }
    );
    this.lastMessageBody = this.conversation.messagesDTOS[this.conversation.messagesDTOS.length-1].body;
  }

  getRoute() {
    const currentUrl = this.location.path();
    const parts = currentUrl.split('/');

    const lastPart = parts[parts.length - 1];
    if (lastPart === this.interlocutor) {
      this.actualConv = true;
    }
  }

  routeToConv() {
    this.router.navigate(['/conversation', this.interlocutor]);
    this.getRoute();
  }

  isPartOfPingpal(){
    return this.interlocutor.endsWith("@pingpal");
  }

  lastMessageDate(){
    const time = new Date(this.conversation.timestamp);

    const hours = time.getHours().toString().padStart(2, '0');
    const minutes = time.getMinutes().toString().padStart(2, '0');

    return `${hours}:${minutes}`;
  }

}
