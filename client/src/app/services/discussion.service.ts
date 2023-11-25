import { Injectable } from '@angular/core';
import {ConversationDTO, ConversationService, MessageDTO, MessageService, NewMessageDTO} from "../api";
import {firstValueFrom, map, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {UserService} from "./user.service";

export class Discussion {
  interlocutor: string;           // address of the interlocutor
  messages: MessageDTO[];         // messages of the discussion
  date: Date;                     // date of creation of the discussion
  // lastMsgDate: Date;              // date of the last message exchanged

  constructor(props: { interlocutor: string, messages: MessageDTO[] }) {
    this.interlocutor = props.interlocutor;
    this.messages = props.messages;
    this.date = new Date();
    // this.lastMsgDate = this.messages.slice(-1)[0]
  }

  getId(){
    return this.interlocutor.replace('@', '');
  }
}

@Injectable({
  providedIn: 'root'
})
export class DiscussionService {

  public discussions: Discussion[] = []; // array of discussion (this object must never be reassigned)
  public conversations: ConversationDTO[] = [];

  constructor(private userService: UserService,
              private messageService: MessageService,
              private conversationService: ConversationService) {
    console.debug('### DiscussionService()');

    // We retrieve the conversations associated to the logged-in user
    this.getConversations()
      .subscribe(conv => {
        this.conversations = conv;
      });
  }

  /**
   * Initialize discussions of the current user.
   */
  async initializeDiscussions() {

    // Get all messages related to the current user
    const messages = await firstValueFrom(this.messageService.userMessageMessagesGet());

    // Reset all discussions (without reassigning the array)
    this.discussions.length = 0;

    // Build discussions from messages
    messages.forEach(message => {

      // Get the discussion related to the message
      const discussion = this.getTargetDiscussion(message);

      // Add the message to the discussion
      this.addMessageToDiscussion(discussion, message, true);
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
            // conv.messages.push(message);
          }
        );

      // // Get the discussion related to the message
      // const discussion = this.getTargetDiscussion(message);
      //
      // // Add the message to the discussion
      // this.addMessageToDiscussion(discussion, message);

      // Notify the new message
      onNewMessage(message);
    }

    source.onerror = ev => {
      console.error('###', ev);
    }

    stopListening.subscribe(_ => source.close());
  }

  /**
   * Search for the discussion related to a given message in the array of discussions.
   * Create a new one and add it the array of discussions if it does not exist yet.
   * @param message The message
   */
  private getTargetDiscussion(message: MessageDTO) {

    // If message is sent by current user: search for an existing discussion with the receiver,
    // otherwise, if message is received by current user: search for an existing discussion with the sender
    const _interlocutor = <string>(message.from === this.userService.getCurrentUserAddress() ? message.to : message.from);
    let discussion = this.discussions.find(discussion => discussion.interlocutor === _interlocutor);

    // If no discussion has been found, create a new one and add it to the array of discussions
    if (!discussion) {
      discussion = new Discussion({interlocutor: _interlocutor, messages: []});
      this.discussions.push(discussion);
    }

    return discussion;
  }

  /**
   * Add a message at the beginning or at the end of a discussion.
   * If the message is already present, nothing is done.
   * @param discussion The discussion
   * @param message The message
   * @param beginning True if the message must be added at the beginning of the discussion (default: false)
   */
  private addMessageToDiscussion(discussion: Discussion, message: MessageDTO, beginning = false) {

    // If a message with the same id is already present in the discussion, nothing to do
    if (discussion.messages.find(m => m.id == message.id))
      return;

    // Add the message to the discussion
    if (beginning)
      discussion.messages.unshift(message);
    else
      discussion.messages.push(message);
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
    if (conversation.messages.find(m => m.id == message.id))
      return;

    // Add the message to the discussion
    if (beginning)
      conversation.messages.unshift(message);
    else
      conversation.messages.push(message);
  }

  /** Send a message to an interlocutor
   *
   * @param discussion The discussion between logged user and targeted interlocutor
   * @param body The content of the message
   */
  sendMessage(discussion: Discussion, body: string) {
    console.log(`### sendMessage() of DiscussionService()`);
    const newMessage: NewMessageDTO = {body: body, type: 'text/plain', to: discussion.interlocutor};
    this.messageService.userMessagePost(newMessage).subscribe(message => {
      discussion.messages.push(message);
    });
    console.log(`### sending message`);
    // if (this.discussions.find(discussiontofind => discussion === discussiontofind)) {
    //
    //   // Send the message to the recipient by posting it into the server
    //   console.log(`### Sending message in the discussion ${discussion}`);
    //
    //   this.messageService.userMessagePost(encodeURIComponent(discussion.interlocutor), content)
    //     .subscribe(message => {
    //       console.log(`### Message added to the server`);
    //       // Add the message to the discussion (once completed by the server)
    //       discussion.messages.push(message);
    //       // Mettre le statusMessage à false
    //     });
    // }
  }

  /** Send a message to an interlocutor
   *
   * @param conversation The discussion between logged user and targeted interlocutor
   * @param _interlocutor The address of the interlocutor
   * @param body The content of the message
   */
  sendMessageConversation(conversation: ConversationDTO, _interlocutor: string, body: string) {
    console.log(`### sendMessage() of DiscussionService() but with Conversation`);
    const newMessage: NewMessageDTO = {body: body, type: 'text/plain', to: _interlocutor};
    this.messageService.userMessagePost(newMessage)
      .subscribe(message => {
      this.addMessageToConversation(conversation, message);
    });
    console.log(`### sending message`);
  }

  /**
   * Create a new empty discussion with a given interlocutor.
   * If a discussion with the interlocutor already exists, no new discussion is created.
   * @param _interlocutor The address of the interlocutor
   */
  newDiscussion(_interlocutor: string) {
    console.debug(`### creation of the discussion with ${_interlocutor}`);

    // Search for an existing discussion with the interlocutor
    let discussion = this.discussions
      .find(discussion => discussion.interlocutor === _interlocutor);

    // If a discussion already exists, nothing to do
    if (discussion) {
      console.debug(`### the discussion with ${_interlocutor} already exists!`)
      return;
    }

    discussion = new Discussion({interlocutor: _interlocutor, messages: []});
    this.discussions.push(discussion);
  }


  /**
   * Create a conversation with a given interlocutor if it does not exist already
   * @param _interlocutor The address of the interlocutor
   */
  newConversation(_interlocutor: string){
    console.debug(`### creation of the conversation with ${_interlocutor}`);

    this.conversationService.userConversationNewConversationInterlocutorPost(_interlocutor);
  }

  /** Return true if a discussion exists with the given parameter
    */
  searchDiscussion(_interlocutor: string){
    let discussion = this.discussions
      .find(discussion => discussion.interlocutor === _interlocutor);

    return !!discussion;
  }
  /**
   * Return the discussion with the given parameter
   * @param _interlocutor The address of the interlocutor
   */
  getDiscussion(_interlocutor: string){
    // Search for an existing discussion with the interlocutor
    let discussion = this.discussions
      .find(discussion => discussion.interlocutor === _interlocutor);
    if(discussion instanceof Discussion){
      return discussion;
    }else{
      throw new Error(`No discussion found with ${_interlocutor}`);
    }
  }

  getConversation(recipient: string) {
    return this.conversationService.userConversationInterlocutorGet(recipient);
  }

  getConversations(){
    return this.conversationService.userConversationConversationsGet();
  }
}
