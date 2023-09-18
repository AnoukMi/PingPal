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
    this.recentConv = [new Contact("Anouk", "Migliavacca", "assets/avatar/f2.png"),
      new Contact("Vincent", "Barat", "assets/avatar/m3.png"),
      new Contact("Quentin", "Perez", "assets/avatar/m4.png"),
      new Contact("Vincent", "Barat", "assets/avatar/m5.png"),
      new Contact("Vincent", "Barat", "assets/avatar/m6.png"),
      new Contact("Vincent", "Barat", "assets/avatar/f5.png"),
      new Contact("Vincent", "Barat", "assets/avatar/f3.png"),
      new Contact("Vincent", "Barat", "assets/avatar/m1.png")];
  }
}
