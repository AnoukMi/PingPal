import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {ConversationDTO} from "../../api";
import {ContactProfileService} from "../../services/contact.service";
import {Contact} from "../../models/contact";
import {DiscussionService} from "../../services/discussion.service";

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

  constructor(private contactProfileService: ContactProfileService,
              private discussionService: DiscussionService,
              private router: Router){

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

  routeToConv() {
    this.router.navigate(['/conversation', this.interlocutor]);
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
