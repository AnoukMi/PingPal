import {ChangeDetectorRef, Component, Input, OnDestroy, OnInit} from '@angular/core';
import {ConversationDTO} from "../../api";
import {DiscussionService} from "../../services/discussion.service";
import {Subject} from "rxjs";
import {UserService} from "../../services/user.service";
import {ContactProfileService} from "../../services/contact.service";

@Component({
  selector: 'app-current-conversations',
  templateUrl: './current-conversations.component.html',
  styleUrls: ['./current-conversations.component.css']
})
export class CurrentConversationsComponent implements OnInit, OnDestroy {
  recentConversations! : ConversationDTO[];
  loggedUser: string = '';
  rightConv!: ConversationDTO;
  @Input() isSearched: boolean = false;
  @Input() searchedConv!: string;
  icon: number = 0;

  private stopListening = new Subject<void>();

  constructor(private changeDetectorRef: ChangeDetectorRef,
              private discussionService: DiscussionService,
              private userService: UserService){

    this.discussionService.getConversations()
      .subscribe(conversations =>  {
        this.recentConversations = conversations;
      });
    this.userService.getLogin().subscribe(login => {
      this.loggedUser = login+"@pingpal" || '';
    });
  }

  ngOnInit(){
    // Start listening for new messages and updating discussions
    this.discussionService.listenForNewMessages(this.stopListening,
      message => {
      console.debug(`### new message notified`, message);
      this.changeDetectorRef.detectChanges();
      });

    // Refresh conversations every second
    setInterval(() => {
      this.getConversations();
    }, 1000);
  }

  ngOnDestroy() {
    // Stop listening for new messages
    this.stopListening.next(void 0);
  }

  getConversations(){
    this.discussionService.getConversations()
      .subscribe(conversations => {
        this.recentConversations = conversations;
      });
  }

  /**
   * Return the right conversation according to what the logged-in user typed in the search bar
   * @param _login The searched login or beginning of address or address
   */
  getRightConversation(_login: string){
    for(let i = 0; i < this.recentConversations.length; i++){
      if(this.recentConversations[i].user1.startsWith(_login) || this.recentConversations[i].user2.startsWith(_login)){
        this.rightConv = this.recentConversations[i];
        break;
      }
    }
    return this.rightConv;
  }

  getLastMessageBody(_login: string){
    const messages = this.getRightConversation(_login).messagesDTOS;
    return messages[messages.length-1].body;
}

  getLastMessageTime(_login: string){
    const messages = this.getRightConversation(_login).messagesDTOS;
    return messages[messages.length-1].timestamp;
  }

  // /**
  //  * Tell if the interlocutor is from Pingpal or not
  //  * @param conversation The conversation between the logged-in user and the interlocutor
  //  */
  // fromPingpal(conversation: ConversationDTO) {
  //   let isPart = false;
  //   if (conversation.user1 == this.loggedUser) {
  //     if (conversation.user2.endsWith("@pingpal")) {
  //       isPart = true;
  //       this.contactProfileService.getOneUser(conversation.user2.split("@")[0]).subscribe(
  //         contact => {
  //           this.icon = contact.icon;
  //         }
  //       )
  //     }
  //   } else if (conversation.user2 == this.loggedUser) {
  //     if (conversation.user1.endsWith("@pingpal")) {
  //       isPart = true;
  //       this.contactProfileService.getOneUser(conversation.user1.split("@")[0]).subscribe(
  //         contact => {
  //           this.icon = contact.icon;
  //         }
  //       )
  //     }
  //   }
  //   return isPart;
  // }
}
