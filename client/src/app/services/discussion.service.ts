import { Injectable } from '@angular/core';
import {ConversationDTO, ConversationService, MessageDTO, MessageService} from "../api";
import {firstValueFrom, map, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {UserService} from "./user.service";

export class Discussion {
  interlocutor: string;           // address of the interlocutor
  messages: MessageDTO[];         // messages of the discussion

  constructor(props: { interlocutor: string, messages: MessageDTO[] }) {
    this.interlocutor = props.interlocutor;
    this.messages = props.messages;
  }
}

@Injectable({
  providedIn: 'root'
})
export class DiscussionService {

  public discussions: Discussion[] = []; // array of discussion (this object must never be reassigned)

  constructor(private messageService: MessageService,
              private conversationService: ConversationService) {
    console.debug('### DiscussionService()');
  }


  sendMessage(discussion: Discussion, content: string) {
    if (this.discussions.find(discussiontofind => discussion === discussiontofind)) {

      // Send the message to the recipient by posting it into the server
      console.log(`### Sending message in the discussion ${discussion}`);

      this.messageService.userMessageNewMessageRecipientPost(encodeURIComponent(discussion.interlocutor), content)
        .subscribe(message => {
          console.log(`### Message added to the server`);
          // Add the message to the discussion (once completed by the server)
          discussion.messages.push(message);
          // Mettre le statusMessage à false
        });
    }
  }

  /**
   * Create a new empty discussion with a given interlocutor.
   * If a discussion with the interlocutor already exists, no new discussion is created.
   * @param recipientAddress The address of the interlocutor
   */
  newDiscussion(recipientAddress: string) {
    console.debug(`### creation of the discussion with ${recipientAddress}`);

    // Search for an existing discussion with the interlocutor
    let discussion = this.discussions
      .find(discussion => discussion.interlocutor === recipientAddress);

    // If a discussion already exists, nothing to do
    if (discussion) {
      console.debug(`### the discussion with ${recipientAddress} already exists!`)
      return;
    }else{

    // Also add new conversation to the server : return a DTO
    this.conversationService.userConversationNewConversationInterlocutorPost(encodeURIComponent(recipientAddress))
      .subscribe(conversation => {
        discussion = new Discussion({interlocutor: conversation.peerAddress, messages: []})
        console.log(`### ${conversation} added to the server`);
        this.discussions.push(discussion);
      });
    }
  }

  getConversation(recipient: string) {
    return this.conversationService.userConversationLoginGet(recipient);
  }

  getConversations(){
    return this.conversationService.userConversationConversationsGet();
  }

  getMessages(recipient: string){
    return this.messageService.userMessageUserIDMessagesGet(encodeURIComponent(recipient));
  }
}
