import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageNewConversationComponent } from './page-new-conversation.component';

describe('PageNewConversationComponent', () => {
  let component: PageNewConversationComponent;
  let fixture: ComponentFixture<PageNewConversationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageNewConversationComponent]
    });
    fixture = TestBed.createComponent(PageNewConversationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
