import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Discussion, DiscussionService } from "../../services/discussion.service";
import { Router } from "@angular/router";
import {ConversationService} from "../../api";

@Component({
  selector: 'app-page-new-conversation',
  templateUrl: './page-new-message.component.html',
  styleUrls: ['./page-new-message.component.css']
})

export class PageNewMessageComponent {
  newConvForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private discussionService: DiscussionService,
              private router: Router) {

    this.newConvForm = this.formBuilder.group({
      recipient: ['', Validators.required]
    });
  }

  /**
   * Get a given field of the signup form by its name
   * @param name The name of the field to get
   * @return the field
   */
  getField(name: string) {
    return this.newConvForm.controls[name];
  }

  /**
   * Create a new conversation with a given recipient then redirects to the created conversation
   * If the conversation already exists, redirects to it
   */
  createConversation(){
    // // If the conversation already exists
    // if(this.discussionService.searchDiscussion(this.getField('recipient').value)){
    //   this.router.navigate(['/conversation/', this.getField('recipient').value]);
    // }
    // // If it is a new conversation
    // else{
    //   this.discussionService.newDiscussion(this.getField('recipient').value);
    //   this.router.navigate(['/conversation/', this.getField('recipient').value]);
    // }
    this.discussionService.newConversation(this.getField('recipient').value);
    this.router.navigate(['/conversation/', this.getField('recipient').value]);
  }
}
