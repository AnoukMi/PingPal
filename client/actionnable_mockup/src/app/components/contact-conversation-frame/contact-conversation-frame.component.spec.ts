import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactConversationFrameComponent } from './contact-conversation-frame.component';

describe('ContactConversationFrameComponent', () => {
  let component: ContactConversationFrameComponent;
  let fixture: ComponentFixture<ContactConversationFrameComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ContactConversationFrameComponent]
    });
    fixture = TestBed.createComponent(ContactConversationFrameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
