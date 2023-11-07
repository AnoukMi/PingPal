import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Discussion, DiscussionService } from "../../services/discussion.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-page-new-conversation',
  templateUrl: './page-new-message.component.html',
  styleUrls: ['./page-new-message.component.css']
})

export class PageNewMessageComponent {
  newMsgForm: FormGroup;
  discussion!: Discussion;

  constructor(private formBuilder: FormBuilder,
              private discussionService: DiscussionService, private router: Router) {

    this.newMsgForm = this.formBuilder.group({
      recipient: ['', Validators.required],
      content: ['', Validators.required]
    });
  }

  /**
   * Get a given field of the signup form by its name.
   * @param name The name of the field to get
   * @return the field
   */
  getField(name: string) {
    return this.newMsgForm.controls[name];
  }

  /**
   * Send new message to the given recipient
   * Redirects to the conversation with the given recipient
   *
   */
  sendNewMessage(){
    this.discussionService.newDiscussion(this.getField('recipient').value);
    this.router.navigate(['/conversation/', this.getField('recipient').value]);
    this.discussionService.sendMessage(this.discussion, this.getField('content').value);
  }

}
