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
import { ContactConversationFrameComponent } from './components/contact-conversation-frame/contact-conversation-frame.component';
import { InterlocutorBubbleComponent } from './components/interlocutor-bubble/interlocutor-bubble.component';
import { UserBubbleComponent } from './components/user-bubble/user-bubble.component';
import { ScrollBarComponent } from './components/scroll-bar/scroll-bar.component';
import { UserWritingFrameComponent } from './components/user-writing-frame/user-writing-frame.component';
import { FriendFrameSharedMsgComponent } from './components/friend-frame-shared-msg/friend-frame-shared-msg.component';

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
    ContactConversationFrameComponent,
    InterlocutorBubbleComponent,
    UserBubbleComponent,
    ScrollBarComponent,
    UserWritingFrameComponent,
    FriendFrameSharedMsgComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
