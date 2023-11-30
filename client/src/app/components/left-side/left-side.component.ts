import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CurrentUser, UserService } from "../../services/user.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ConversationDTO} from "../../api";

@Component({
  selector: 'app-left-side',
  templateUrl: './left-side.component.html',
  styleUrls: ['./left-side.component.css']
})
export class LeftSideComponent {
  login : string ='';
  firstname : string='';
  lastname : string='';
  icon : number=0;
  birthday: string='';
  currentUser: CurrentUser = undefined;
  searchForm: FormGroup;
  isSearched: boolean = false;
  searchedConv: string = '';
  constructor(private userService: UserService,
              protected router: Router,
              private formBuilder: FormBuilder) {
    this.userService.getLogin().subscribe(login => {
      this.login = login || ''; // '' par défaut car si null ou undefined pas de valeur string possible
    });

    this.userService.getFirstname().subscribe(firstname => {
      this.firstname = firstname || '';
    });

    this.userService.getLastname().subscribe(lastname => {
      this.lastname = lastname || '';
    });

    this.userService.getIcon().subscribe(icon => {
      this.icon = icon || 0;
    });

    this.userService.getBirthday().subscribe(birthday => {
      this.birthday = birthday || '';
    });
    console.debug('### AppComponent()');
    this.userService.currentUserObservable.subscribe(currentUser => this.setUser(currentUser));

    this.searchForm = this.formBuilder.group({
      username: ['', Validators.required],
    });

  }

  setUser(user : CurrentUser){
    this.currentUser = user;
    // update values (when edited for example)
    this.userService.getLogin().subscribe(login => {
      this.login = login || ''; // '' par défaut car si null ou undefined pas de valeur string possible
    });

    this.userService.getFirstname().subscribe(firstname => {
      this.firstname = firstname || '';
    });

    this.userService.getLastname().subscribe(lastname => {
      this.lastname = lastname || '';
    });

    this.userService.getIcon().subscribe(icon => {
      this.icon = icon || 0;
    });

    this.userService.getBirthday().subscribe(birthday => {
      this.birthday = birthday || '';
    });
  }


  signout() {
    this.userService.signout().subscribe(_ => this.router.navigate(['signIn']));
  }

  /**
   * Get a given field of the signup form by its name.
   * @param name The name of the field to get
   * @return the field
   */
  getField(name: string) {
    return this.searchForm.controls[name];
  }

  onSearch(){
    if(this.getField('username').value==""){
      this.isSearched = false;
    }else {
      this.isSearched = true;
      this.searchedConv = this.getField('username').value;
    }
  }

}
