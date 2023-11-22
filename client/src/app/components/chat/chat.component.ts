import {Component, Input, OnInit} from '@angular/core';
import {MessageDTO} from "../../api";
import {Discussion, DiscussionService} from "../../services/discussion.service";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit{
  @Input() discussion!: Discussion;
  @Input() messages!: MessageDTO[];

  constructor(private discussionService: DiscussionService) {
  }

    ngOnInit() {
        this.getMessages();

        // Rafraîchir la liste des messages toutes les secondes
        // setInterval(() => {
        //     this.getMessages();
        // }, 1000);
    }

    getMessages() {
        this.discussionService.getMessages(this.discussion.interlocutor)
            .subscribe(newMessages => {
                    for (let message of newMessages) {
                      // We only add the new messages in the list of messages
                        if (!this.messages.includes(message)) {
                            this.messages.push(message);
                        }
                    }
                }
            );
    }
}
