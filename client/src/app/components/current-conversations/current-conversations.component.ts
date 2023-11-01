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

    // Rafraîchir la liste des conversations toutes les 3 secondes
    setInterval(() => {
      this.getConversations();
    }, 5000);
  }

  getConversations(){
    this.discussionService.getConversations()
      .subscribe(conversations => {
        this.recentConv = conversations;
        for (let conversation of this.recentConv) {
          let contact = new Contact(conversation.userID);
          this.contacts.push(contact);
        }
      });
  }

}
