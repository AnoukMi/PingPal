import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserWritingBoxComponent } from './user-writing-box.component';

describe('UserWritingFrameComponent', () => {
  let component: UserWritingBoxComponent;
  let fixture: ComponentFixture<UserWritingBoxComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserWritingBoxComponent]
    });
    fixture = TestBed.createComponent(UserWritingBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
