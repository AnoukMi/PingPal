import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageNewMessageComponent } from './page-new-message.component';

describe('PageNewConversationComponent', () => {
  let component: PageNewMessageComponent;
  let fixture: ComponentFixture<PageNewMessageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageNewMessageComponent]
    });
    fixture = TestBed.createComponent(PageNewMessageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
