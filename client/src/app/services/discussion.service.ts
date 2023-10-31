import { Injectable } from '@angular/core';
import {ConversationDTO, ConversationService, MessageDTO, MessageService} from "../api";
import {BehaviorSubject, firstValueFrom, Observable} from "rxjs";
import {WebSocketSubject} from "rxjs/internal/observable/dom/WebSocketSubject";
import {webSocket} from "rxjs/webSocket";
import {HttpClient} from "@angular/common/http";
import {WebSocketService} from "./web-socket.service";
import {UserService} from "./user.service";

export class Discussion {
  interlocutor: string;           // address of the interlocutor
  messages: MessageDTO[];         // messages of the discussion

  constructor(props: { interlocutor: string, messages: MessageDTO[] }) {
    this.interlocutor = props.interlocutor;
    this.messages = props.messages;
  }

  /**
   * @return the id of the discussions (built from the address of the interlocutor)
   */
  getId() {
    return this.interlocutor.replace('@', '');
  }

  getInterlocutor(): string{
    return this.interlocutor;
  }
}

@Injectable({
  providedIn: 'root'
})
export class DiscussionService {

  public discussions: Discussion[] = []; // array of discussion (this object must never be reassigned)
  private apiUrl = 'user/conversation/conversations'

  constructor(private messageService: MessageService,
              private conversationService: ConversationService,
              private userService: UserService,
              private http: HttpClient,
              private webSocketService: WebSocketService) {
    console.debug('### DiscussionService()');
  }


  /**
   * Initialize discussions of the current user.
   */
  async initializeDiscussions() {

    // Get all messages related to the current user
    let messages = await firstValueFrom(this.messageService.userMessageUserIDMessagesGet(this.userService.getCurrentUserAddress()));

    // Reset all discussions (without reassigning the array)
    this.discussions.length = 0;

    // Build discussions from messages
    const currentUserAddress = this.userService.getCurrentUserAddress();
    messages.forEach(message => {

      // If message is sent by current user: search for an existing discussion with the receiver,
      // otherwise, if message is received by current user: search for an existing discussion with the sender
      let _with = <string>(message.authorAddress === currentUserAddress ? message.recipientID : message.authorAddress);
      let discussion = this.discussions.find(discussion => discussion.interlocutor === _with);

      // If no discussion has been found, create a new one and add it to the array of discussions
      if (!discussion) {
        discussion = new Discussion({ interlocutor: _with, messages: [] });
        this.discussions.push(discussion);
      }

      // Add the message to the discussion
      discussion.messages.unshift(message);
    });
  }


  sendMessage(discussion: Discussion, content: string) {
     if (this.discussions.find(discussiontofind => discussion === discussiontofind)) {
      // Send the message to the recipient by posting it into the server
       console.log(`### Sending message in the discussion ${discussion}`);
      this.messageService.userMessageNewMessageRecipientPost(discussion.interlocutor, content)
        .subscribe(message => {
          console.log(`### Message added to the server`);
          // Add the message to the discussion (once completed by the server)
          discussion.messages.push(message);
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
    if (discussion){
      console.debug(`### the discussion with ${recipientAddress} already exists!`)
      return;
    } else {
      // Also add new conversation to the server : return a DTO
      this.conversationService.userConversationNewConversationInterlocutorPost(recipientAddress)
        .subscribe(conversation => {
          discussion = new Discussion({interlocutor : conversation.peerAddress, messages : []})
          console.log(`### ${conversation} added to the server`);
          this.discussions.push(discussion);
        });
    }
  }


  getConversations(): Observable<ConversationDTO[]> {
    return this.http.get<ConversationDTO[]>(this.apiUrl);
  }
}
