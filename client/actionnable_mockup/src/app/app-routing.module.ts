import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PageConversationComponent} from "./components/page-conversation/page-conversation.component";
import {PageNotFoundComponent} from "./components/page-not-found/page-not-found.component";
import {PageHomeComponent} from "./components/page-home/page-home.component";
import {PageSignInComponent} from "./components/page-sign-in/page-sign-in.component";
import {PageSignUpComponent} from "./components/page-sign-up/page-sign-up.component";
import {PageMyFriendsComponent} from "./components/page-my-friends/page-my-friends.component";
import {PageNewConversationComponent} from "./components/page-new-conversation/page-new-conversation.component";
import {PageEditProfilComponent} from "./components/page-edit-profil/page-edit-profil.component";
import {PageShareMessageComponent} from "./components/page-share-message/page-share-message.component";

const routes: Routes = [
  {path: 'home', //quand l'utilisateur est sur ou rentre l' url avec /home
    component:PageHomeComponent}, //contient la page home avec le composant home codé
  {path: 'signIn', //page de connexion
    component:PageSignInComponent},
  {path: 'signUp', //page d'inscription
    component:PageSignUpComponent},
  {path: 'myFriends', //page des contacts amis enregistrés
    component:PageMyFriendsComponent},
  {path: 'newConversation', //page pour commencer une nouvelle conversation
    component:PageNewConversationComponent},
  {path: 'conversation', //page conversation avec un ami
    component:PageConversationComponent},
  {path: 'editProfile', //page modification du profil
    component:PageEditProfilComponent},
  {path: 'shareMessage', //page pour partager un message avec l'ensemble des amis
    component:PageShareMessageComponent},
  {path: '', //quand l'utilisateur est sur la racine
    redirectTo:'signIn',
    pathMatch:'full'}, //il est rédirigé vers la route de connexion, match total avec '' (vide) pour que ça marche
  {path: '**', //chemin par défaut, c'est à dire tout autre chemin que ce qui a été spécifié
    component:PageNotFoundComponent} //contient le composant page-not-found
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
