import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-interlocutor-bubble',
  templateUrl: './interlocutor-bubble.component.html',
  styleUrls: ['./interlocutor-bubble.component.css']
})
export class InterlocutorBubbleComponent {

  @Input() author: string = '';
  @Input() content: string = '';
  @Input() date: string = '';

}
