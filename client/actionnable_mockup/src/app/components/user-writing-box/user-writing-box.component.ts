import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-user-writing-frame',
  templateUrl: './user-writing-box.component.html',
  styleUrls: ['./user-writing-box.component.css']
})
export class UserWritingBoxComponent {
  @Input() placeholder: string = '';
  @Input() type: string = '';
}
