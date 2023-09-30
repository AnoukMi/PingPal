import { Component } from '@angular/core';
import {Contact} from "../../models/contact";

@Component({
  selector: 'app-page-share-message',
  templateUrl: './page-share-message.component.html',
  styleUrls: ['./page-share-message.component.css']
})
export class PageShareMessageComponent {
  myself: Contact = new Contact("Ambre", "Léveillé",
    "assets/avatar/f3.png", "ivambre", new Date(2003, 10, 5))
}
