import { Component, Input } from '@angular/core';
import {MessageDTO} from "../../api";
import {DatePipe} from "@angular/common";


@Component({
  selector: 'app-user-bubble',
  templateUrl: './user-bubble.component.html',
  styleUrls: ['./user-bubble.component.css']
})
export class UserBubbleComponent {
  @Input() message!: MessageDTO;

  // constructor(private datePipe: DatePipe) {
  // }
  //
  // getTime(timestamp: number){
  //   return this.datePipe.transform(timestamp, 'HH:mm') || '';
  // }
}
