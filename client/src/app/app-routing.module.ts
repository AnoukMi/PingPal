import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { authGuard } from "./guards/auth.guard";
import {PageConversationComponent} from "./components/page-conversation/page-conversation.component";
import {PageNotFoundComponent} from "./components/page-not-found/page-not-found.component";
import {PageHomeComponent} from "./components/page-home/page-home.component";
import {PageSignInComponent} from "./components/page-sign-in/page-sign-in.component";
import {PageSignUpComponent} from "./components/page-sign-up/page-sign-up.component";
import {PageMyFriendsComponent} from "./components/page-my-friends/page-my-friends.component";
import {PageNewMessageComponent} from "./components/page-new-message/page-new-message.component";
import {PageEditProfileComponent} from "./components/page-edit-profile/page-edit-profile.component";
import {PageShareMessageComponent} from "./components/page-share-message/page-share-message.component";

const routes: Routes = [
  {path: 'signIn',
    component:PageSignInComponent}, //page de connexion
  {path: 'signUp',
    component:PageSignUpComponent}, //page d'inscription
  {path: 'home', //quand l'utilisateur est sur ou rentre l' url avec /home
    component:PageHomeComponent,
    canActivate: [authGuard]}, //appelle fonction de garde avant de charger page home pour vérifier autorisation d'accès
  {path: 'myFriends',
    component:PageMyFriendsComponent}, //page des contacts amis enregistrés
  {path: 'newMessage', //page pour commencer une nouvelle conversation
    component:PageNewMessageComponent},
  {path: 'conversation/:recipient', //page conversation avec un ami
    component:PageConversationComponent},
  {path: 'editProfile', //page modification du profil
    component:PageEditProfileComponent},
  {path: 'shareMessage', //page pour partager un message avec l'ensemble des amis
    component:PageShareMessageComponent},
  {path: 'shareMessage/shareMessage', redirectTo:'shareMessage', pathMatch:'full'},
  {path: 'signIn/home',  redirectTo:'home', pathMatch:'full'},//renvoie sur home par le clique à partir de signIn
  {path: 'signUp/home', redirectTo:'home', pathMatch:'full'}, //renvoie sur home par le clique à partir de signUn
  {path: 'signIn/signUp', redirectTo:'signUp', pathMatch:'full'},  //renvoie sur signUp par le clique à partir de signIn
  {path: 'conversation/:recipient/conversation', redirectTo:'conversation/:recipient', pathMatch:'full'}, //reste sur mm conv qd clique "send"
  {path: 'newMessage/conversation', redirectTo:'conversation', pathMatch:'full'}, //renvoie sur conv crée pour new msg
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
