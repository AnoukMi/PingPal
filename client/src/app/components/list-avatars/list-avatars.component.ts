import { Component, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-list-avatars',
  templateUrl: './list-avatars.component.html',
  styleUrls: ['./list-avatars.component.css']
})
export class ListAvatarsComponent {
  icon : number =0;
  @Output() iconChange=new EventEmitter<number>();

  onIconChange(selectedIcon : number) {
    console.log('onIconChange called with icon:', selectedIcon);
    this.icon = selectedIcon;
    this.iconChange.emit(this.icon);
  }

}
