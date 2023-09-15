import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-user-bubble',
  templateUrl: './user-bubble.component.html',
  styleUrls: ['./user-bubble.component.css']
})
export class UserBubbleComponent {
    @Input() content: string = '';
}
