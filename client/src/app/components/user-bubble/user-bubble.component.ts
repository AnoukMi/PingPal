import { Component, Input } from '@angular/core';
import {MessageDTO} from "../../api";


@Component({
  selector: 'app-user-bubble',
  templateUrl: './user-bubble.component.html',
  styleUrls: ['./user-bubble.component.css']
})
export class UserBubbleComponent {
  @Input() message!: MessageDTO;

  // @Input() author: string = '';
  // @Input() body: string = '';
  // @Input() date: string = '';


}
