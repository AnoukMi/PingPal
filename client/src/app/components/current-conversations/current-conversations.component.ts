import {ChangeDetectorRef, Component, Input, OnDestroy, OnInit} from '@angular/core';
import { Contact } from "../../models/contact";
import {ConversationDTO, MessageService} from "../../api";
import {Discussion, DiscussionService} from "../../services/discussion.service";
import {Subject} from "rxjs";
import {UserService} from "../../services/user.service";

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

    // Next line is supposed to display the conversations by order of creation
    // this.recentConv.sort((a, b) => b.date.getTime() - a.date.getTime());
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

}
