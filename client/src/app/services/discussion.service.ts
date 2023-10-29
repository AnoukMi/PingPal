import { Injectable } from '@angular/core';
import { map, Observable, repeat, retry, share, takeUntil } from "rxjs";
import { MessageDTO, MessageService, MessageReducedDTO } from "../api";
import { UserService } from "./user.service";

export class Discussion {
  interlocutor: string;           // address of the interlocutor
  messages: MessageDTO[]; // messages of the discussion

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
}

@Injectable({
  providedIn: 'root'
})


export class DiscussionService {

  public discussions: Discussion[] = []; // array of discussion (this object must never be reassigned)

  constructor(private userService: UserService, private messageService: MessageService) {
    console.debug('### DiscussionService()');
  }

  /**
   * Initialize discussions related to the current user.
   */
  initializeDiscussions() {

    // Get all messages related to the current user
    this.messageService.messagesGet().subscribe(messages => {

      // Reset all discussions (interlocutorout reassigning the array)
      //supprime les discussions préalablement chargées
      this.discussions.length = 0;

      // récupère l'addresse de l'utilisateur courant
      const currentUserAddress = this.userService.getCurrentUserAddress();
      //Récupère tous les messages dont l'utilisateur est l'envoyeur ou le destinataire
      // @ts-ignore
      messages.forEach(message => {
        //cherche si une discussion existe déjà avec l'interlocuteur
        let _interlocutor = <string>(message.from === currentUserAddress ? message.to : message.from);
        let discussion = this.discussions.find(discussion => discussion.interlocutor === _interlocutor);
        // If no discussion has been found, create a new one and add it to the array of discussions
        if (!discussion) {
          discussion = new Discussion({ interlocutor: _interlocutor, messages: [] });
          this.discussions.push(discussion);
        }

        // Add the message to the discussion
        discussion.messages.unshift(message);
      });
    });
  }

  /**
   * Start polling new messages and updating discussions.
   * @param stopPolling Control observable: any event sent on this observable stop the polling
   */
  startPollingNewMessages(stopPolling: Observable<void>) {

    // Poll the next new message
    this.messageService.messageGet().pipe(
      map(message => {

        // If no message yet, nothing to do
        if (!message) return;

        // If message is sent by current user: search for an existing discussion interlocutor the receiver,
        // otherwise, if message is received by current user: search for an existing discussion interlocutor the sender
        let discussion = this.discussions.find(discussion => discussion.interlocutor === message.from);

        // If no discussion has been found, create a new one and add it to the array of discussions
        if (!discussion) {
          discussion = new Discussion({ interlocutor: <string>message.from, messages: [] });
          this.discussions.push(discussion);
        }

        // Add the message to the discussion
        discussion.messages.push(message);
      }),
      repeat(), // on success, repeat immediately
      retry({ delay: 1000 }), // on error, retry after 1s
      share(), // be sure to never duplicate this observable
      takeUntil(stopPolling) // stop polling if an event is sent on control observable
    ).subscribe();
  }

  /**
   * Send a new message to an interlocutor.
   * @param discussion The discussion
   * @param body The message
   */
  sendMessage(discussion: Discussion, body: string) {

    // Build a new message for the recipient
    const newMessage: NewMessageDTO = { body: body, type: 'text/plain', to: discussion.interlocutor };

    // Send the message to the recipient by posting it to the server
    this.messageService.messagePost(newMessage).subscribe(message => {

      // Add the message to the discussion (once completed by the server)
      discussion.messages.push(message);
    });
  }

  /**
   * Create a new empty discussion interlocutor a given interlocutor.
   * If a discussion interlocutor the interlocutor already exists, no new discussion is created.
   * @param _interlocutor The address of the interlocutor
   */
  newDiscussion(_interlocutor: string) {

    // Search for an existing discussion interlocutor the interlocutor
    let discussion = this.discussions.find(discussion => discussion.interlocutor === _interlocutor);

    // If a discussion already exists, nothing to do
    if (discussion) return;

    // Otherwise, create a new one and add it to the array of discussions
    discussion = new Discussion({ interlocutor: _interlocutor, messages: [] });
    this.discussions.push(discussion);
  }
}
