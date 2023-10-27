import { Component } from '@angular/core';
import {Contact} from "../../models/contact";

@Component({
  selector: 'app-current-conversations',
  templateUrl: './current-conversations.component.html',
  styleUrls: ['./current-conversations.component.css']
})
export class CurrentConversationsComponent {
  recentConv: Contact[];

  constructor() {
    this.recentConv = [new Contact("Anouk", "Migliavacca", "assets/avatar/2.png"),
      new Contact("Vincent", "Barat", "assets/avatar/9.png"),
      new Contact("Quentin", "Perez", "assets/avatar/10.png"),
      new Contact("Vincent", "Barat", "assets/avatar/11.png"),
      new Contact("Vincent", "Barat", "assets/avatar/12.png"),
      new Contact("Vincent", "Barat", "assets/avatar/5.png"),
      new Contact("Vincent", "Barat", "assets/avatar/3.png"),
      new Contact("Vincent", "Barat", "assets/avatar/7.png")];
  }
}
