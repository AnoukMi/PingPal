import {Component, OnInit} from '@angular/core';
import { Contact } from "../../models/contact";
import { ConversationDTO } from "../../api";
import {Discussion, DiscussionService} from "../../services/discussion.service";

@Component({
  selector: 'app-current-conversations',
  templateUrl: './current-conversations.component.html',
  styleUrls: ['./current-conversations.component.css']
})
export class CurrentConversationsComponent implements OnInit {
  recentConv: ConversationDTO[] = [];
  contacts: Contact[] = [];

  constructor(private discussionService: DiscussionService) {
    console.log(`### CurrentConversationsComponent()`);
  }

  ngOnInit() {
    this.getConversations();

    // Rafraîchir la liste des conversations toutes les secondes
    setInterval(() => {
      this.getConversations();
    }, 1000);
  }

  getConversations(){
    this.discussionService.getConversations()
      .subscribe(conversations => {
        this.recentConv = conversations;
        for (let conversation of this.recentConv) {
          // Par la suite, chercher dans la base de données de nos utilisateurs l'user correspondant au PeerAddress pour
          // récupérer ses infos telles que le prénom, l'icon associé...

          let contact = new Contact(conversation.peerAddress,
            "assets/avatar/1.png", new Date().toLocaleDateString());

          if (!this.contacts.some(existingContact => existingContact.username === contact.username)) {
            this.contacts.push(contact);
            // reverse permet d'afficher du plus récent au plus ancien
            this.contacts= this.contacts.reverse();
          }
        }
      });
  }

}
