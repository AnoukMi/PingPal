import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageConversationComponent } from './page-conversation.component';

describe('PageConversationComponent', () => {
  let component: PageConversationComponent;
  let fixture: ComponentFixture<PageConversationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageConversationComponent]
    });
    fixture = TestBed.createComponent(PageConversationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
