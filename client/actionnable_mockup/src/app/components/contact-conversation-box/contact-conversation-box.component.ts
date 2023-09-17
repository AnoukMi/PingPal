import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-contact-conversation-frame',
  templateUrl: './contact-conversation-box.component.html',
  styleUrls: ['./contact-conversation-box.component.css']
})
export class ContactConversationBoxComponent {
  @Input() contactName: string = '';
  read: string = 'Read message';
  unread: string ='Unread message';
  sent: string = 'Sent message';
  @Input() statusMessage: boolean = false;
}
