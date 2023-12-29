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
  read: boolean = false;


  constructor(private contactProfileService: ContactProfileService,
              private router: Router){


  }

  ngOnInit() {
    console.log(`conversation with ${this.interlocutor}`);
    this.findContact();
    this.lastMessageBody = this.conversation.messagesDTOS[this.conversation.messagesDTOS.length-1].body;
  }

  findContact(){
    if(this.interlocutor.endsWith("@pingpal")) {
      this.contactProfileService.getOneUser(this.interlocutor.split("@")[0]).subscribe(
        contact => {
          this.contact = contact;
        });
    }
  }

  changeStatus() {
    this.read = true;
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

  receivedMessage(){
    this.read = false;
  }

}
