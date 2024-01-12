import { Injectable } from '@angular/core';
import {ConversationDTO, ConversationService, MessageDTO, MessageService, NewMessageDTO} from "../api";
import {Observable} from "rxjs";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class DiscussionService {

  public conversations: ConversationDTO[] = [];

  constructor(private messageService: MessageService,
              private conversationService: ConversationService,
              private router: Router) {
    console.debug('### DiscussionService()');

    // We retrieve the conversations associated to the logged-in user
    this.getConversations()
      .subscribe(conv => {
        this.conversations = conv;
      });
  }

  /**
   * Start listening for new messages and updating discussions.
   * @param stopListening Control observable: any event sent on this observable stops the process
   * @param onNewMessage Callback function called to notify each new message received
   */
  listenForNewMessages(stopListening: Observable<void>, onNewMessage: (message: MessageDTO) => void) {

    let source = new EventSource("/serverapi/user/message");

    source.onmessage = (event: MessageEvent) => {

      // Build the message from received data
      const message = JSON.parse(event.data);

      // Get the conversation associated to the interlocutor
      const conversation =
        this.conversationService.userConversationInterlocutorGet(message.to()).subscribe(
          conv => {
            // Add the message to the conversation
            this.addMessageToConversation(conv, message);
          }
        );

      // Notify the new message
      onNewMessage(message);
    }

    source.onerror = ev => {
      console.error('###', ev);
    }

    stopListening.subscribe(_ => source.close());
  }

  /**
   * Add a message at the beginning or at the end of a discussion.
   * If the message is already present, nothing is done.
   * @param conversation The discussion
   * @param message The message
   * @param beginning True if the message must be added at the beginning of the discussion (default: false)
   */
  private addMessageToConversation(conversation: ConversationDTO, message: MessageDTO, beginning = false) {

    // If a message with the same id is already present in the discussion, nothing to do
    if (conversation.messagesDTOS.find(m => m.id == message.id))
      return;

    // Add the message to the discussion
    if (beginning)
      conversation.messagesDTOS.unshift(message);
    else
      conversation.messagesDTOS.push(message);
  }

  /** Send a message to an interlocutor
   *
   * @param conversation The discussion between logged user and targeted interlocutor
   * @param _interlocutor The address of the interlocutor
   * @param body The content of the message
   */
  sendMessage(conversation: ConversationDTO, _interlocutor: string, body: string) {
    console.log(`### sendMessage() of DiscussionService() but with Conversation`);
    const newMessage: NewMessageDTO = {body: body, type: 'text/plain', to: _interlocutor};
    this.messageService.userMessagePost(newMessage)
      .subscribe(message => {
      this.addMessageToConversation(conversation, message);
    });
    console.log(`### sending message`);
  }

  /**
   * Create a conversation with a given interlocutor if it does not exist already
   * @param _interlocutor The address of the interlocutor
   */
  newConversation(_interlocutor: string){
    console.debug(`### creation of the conversation with ${_interlocutor}`);

    this.conversationService.userConversationNewConversationInterlocutorPost(_interlocutor)
        .subscribe(
            ()=> { console.log(`### Conversation créée`);
              this.router.navigate(['/conversation', _interlocutor]);
            });
  }

  getConversation(recipient: string) {
    return this.conversationService.userConversationInterlocutorGet(recipient);
  }

  getConversations(){
    return this.conversationService.userConversationConversationsGet();
  }
}
