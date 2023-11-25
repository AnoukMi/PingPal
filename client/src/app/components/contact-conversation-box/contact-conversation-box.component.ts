import {Component, Input} from '@angular/core';
import {Contact} from "../../models/contact";
import {Router} from "@angular/router";
import {Discussion} from "../../services/discussion.service";

@Component({
  selector: 'app-contact-conversation-frame',
  templateUrl: './contact-conversation-box.component.html',
  styleUrls: ['./contact-conversation-box.component.css']
})


export class ContactConversationBoxComponent {
  @Input() discussion!: Discussion;
  @Input() statusMessage: boolean = false;
  @Input() sentMessage: boolean = false;
  hour: string = '';

  constructor(private router: Router) {
    // const hours = this.discussion.date.getHours().toString().padStart(2, '0');
    // const minutes = this.discussion.date.getMinutes().toString().padStart(2, '0');
    // this.hour = `${hours}:${minutes}`;
  }

  changeStatus() {
    this.statusMessage = !this.statusMessage;
    this.router.navigate(['/conversation', this.discussion.interlocutor]);
  }
}
