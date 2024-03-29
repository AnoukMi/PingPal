import {NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {
  ApiModule,
  AuthenticationService,
  Configuration,
  ContactService,
  ConversationService, MessageService,
  ProfileService
} from './api';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from "@angular/forms";
import { FormsModule } from '@angular/forms';

import { PageSignInComponent } from './components/page-sign-in/page-sign-in.component';
import { PageSignUpComponent } from './components/page-sign-up/page-sign-up.component';
import { PageHomeComponent } from './components/page-home/page-home.component';
import { PageConversationComponent } from './components/page-conversation/page-conversation.component';
import { PageNewMessageComponent } from './components/page-new-message/page-new-message.component';
import { PageEditProfileComponent } from './components/page-edit-profile/page-edit-profile.component';
import { PageMyFriendsComponent } from './components/page-my-friends/page-my-friends.component';
import { PageShareMessageComponent } from './components/page-share-message/page-share-message.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { ToolBarUserComponent } from './components/tool-bar-user/tool-bar-user.component';
import { ContactConversationBoxComponent } from './components/contact-conversation-box/contact-conversation-box.component';
import { InterlocutorBubbleComponent } from './components/interlocutor-bubble/interlocutor-bubble.component';
import { UserBubbleComponent } from './components/user-bubble/user-bubble.component';
import { UserWritingFrameComponent } from './components/user-writing-frame/user-writing-frame.component';
import { FriendBoxSharedMsgComponent } from './components/friend-box-shared-msg/friend-box-shared-msg.component';
import { DateSelectorComponent } from './components/date-selector/date-selector.component';
import {ChatComponent} from "./components/chat/chat.component";
import { CurrentConversationsComponent } from './components/current-conversations/current-conversations.component';
import {CommonModule} from "@angular/common";
import { FriendComponent } from './components/friend/friend.component';
import { CurrentFriendsComponent } from './components/current-friends/current-friends.component';
import { LeftSideComponent } from './components/left-side/left-side.component';
import {UserService} from "./services/user.service";
import {ScrollBarComponent} from "./components/scroll-bar/scroll-bar.component";
import { ListAvatarsComponent } from './components/list-avatars/list-avatars.component';
import {ContactProfileService} from "./services/contact.service";
// import { PopupComponent } from './popup/popup.component';
// import { MouseComponent } from './components/mouse/mouse.component';

@NgModule({
  declarations: [
    AppComponent,
    ChatComponent,
    ContactConversationBoxComponent,
    CurrentConversationsComponent,
    CurrentFriendsComponent,
    DateSelectorComponent,
    FriendComponent,
    FriendBoxSharedMsgComponent,
    InterlocutorBubbleComponent,
    LeftSideComponent,
    PageConversationComponent,
    PageEditProfileComponent,
    PageHomeComponent,
    PageMyFriendsComponent,
    PageNewMessageComponent,
    PageNotFoundComponent,
    PageShareMessageComponent,
    PageSignInComponent,
    PageSignUpComponent,
    ScrollBarComponent,
    ToolBarUserComponent,
    UserBubbleComponent,
    UserWritingFrameComponent,
    ListAvatarsComponent,
    // PopupComponent,
     //MouseComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    // To avoid CORS issues and limitations, we use an Angular reverse proxy to access the server API
    // (see proxy-config.json: it specifies that all HTTP calls to /serverapi/* URLs should be redirected to the server API at http://localhost:8080/*)
    // Here we configure the generated API client for it to use this base path.
    ApiModule.forRoot(() => new Configuration({ basePath: '/serverapi' }))

  ],
  providers: [AuthenticationService, ProfileService, ConversationService, MessageService, ContactService, UserService, ContactProfileService],
  bootstrap: [AppComponent]
})
export class AppModule { }
