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
    this.recentConv = [new Contact("Anouk", "Migliavacca", "assets/avatar/chien.png"),
      new Contact("Vincent", "Barat", "assets/avatar/chat.png"),
      new Contact("Quentin", "Perez", "assets/avatar/renard.png"),
      new Contact("Vincent", "Barat", "assets/avatar/chien.png"),
      new Contact("Vincent", "Barat", "assets/avatar/grenouille.png"),
      new Contact("Vincent", "Barat", "assets/avatar/coq.png"),
      new Contact("Vincent", "Barat", "assets/avatar/cochon.png"),
      new Contact("Vincent", "Barat", "assets/avatar/ours.png")];
  }
}
