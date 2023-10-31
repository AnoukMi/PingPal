import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import { MessageDTO } from "../../api";
import {Discussion, DiscussionService} from "../../services/discussion.service";
import {UserService} from "../../services/user.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {
  @Input() discussion!: Discussion;
  @Input() messages!: MessageDTO[];

  constructor() {

  }

}
