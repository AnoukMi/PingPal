import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InterlocutorBubbleComponent } from './interlocutor-bubble.component';

describe('InterlocutorBubbleComponent', () => {
  let component: InterlocutorBubbleComponent;
  let fixture: ComponentFixture<InterlocutorBubbleComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InterlocutorBubbleComponent]
    });
    fixture = TestBed.createComponent(InterlocutorBubbleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
