import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
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
import { ScrollBarComponent } from './components/scroll-bar/scroll-bar.component';
import { UserWritingBoxComponent } from './components/user-writing-box/user-writing-box.component';
import { FriendBoxSharedMsgComponent } from './components/friend-box-shared-msg/friend-box-shared-msg.component';
import { DateSelectorComponent } from './components/date-selector/date-selector.component';

@NgModule({
  declarations: [
    AppComponent,
    PageSignInComponent,
    PageSignUpComponent,
    PageHomeComponent,
    PageConversationComponent,
    PageNewMessageComponent,
    PageEditProfileComponent,
    PageMyFriendsComponent,
    PageShareMessageComponent,
    PageNotFoundComponent,
    ToolBarUserComponent,
    ContactConversationBoxComponent,
    InterlocutorBubbleComponent,
    UserBubbleComponent,
    ScrollBarComponent,
    UserWritingBoxComponent,
    FriendBoxSharedMsgComponent,
    DateSelectorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
