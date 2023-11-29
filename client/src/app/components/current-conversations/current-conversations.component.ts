import {ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
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

  async ngOnInit(){
    // Start listening for new messages and updating discussions
    this.discussionService.listenForNewMessages(this.stopListening,
      message => {
      console.debug(`### new message notified`, message);
      this.changeDetectorRef.detectChanges();
      });
    // Next line is supposed to display the conversations by order of creation
    // this.recentConv.sort((a, b) => b.date.getTime() - a.date.getTime());
  }

  ngOnDestroy() {
    // Stop listening for new messages
    this.stopListening.next(void 0);
  }

  // recentConv: ConversationDTO[] = [];
  // contacts: Contact[] = [];
  //
  // constructor(private discussionService: DiscussionService) {
  //   console.log(`### CurrentConversationsComponent()`);
  // }
  //
  // ngOnInit() {
  //   this.getConversations();
  //
  //   // Rafraîchir la liste des conversations toutes les secondes
  //   // setInterval(() => {
  //   //   this.getConversations();
  //   // }, 1000);
  // }
  //
  // getConversations(){
  //   this.discussionService.getConversations()
  //     .subscribe(conversations => {
  //       this.recentConv = conversations;
  //       for (let conversation of this.recentConv) {
  //         // Par la suite, chercher dans la base de données de nos utilisateurs l'user correspondant au PeerAddress pour
  //         // récupérer ses infos telles que le prénom, l'icon associé...
  //
  //         let contact = new Contact(conversation.peerAddress,
  //           "assets/avatar/1.png", new Date().toLocaleDateString());
  //
  //         if (!this.contacts.some(existingContact => existingContact.username === contact.username)) {
  //           this.contacts.push(contact);
  //           // reverse permet d'afficher du plus récent au plus ancien
  //           this.contacts= this.contacts.reverse();
  //         }
  //       }
  //     });
  // }

}
