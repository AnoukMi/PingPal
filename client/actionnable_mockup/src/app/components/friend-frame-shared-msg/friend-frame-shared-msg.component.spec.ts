import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FriendFrameSharedMsgComponent } from './friend-frame-shared-msg.component';

describe('FriendFrameSharedMsgComponent', () => {
  let component: FriendFrameSharedMsgComponent;
  let fixture: ComponentFixture<FriendFrameSharedMsgComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FriendFrameSharedMsgComponent]
    });
    fixture = TestBed.createComponent(FriendFrameSharedMsgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
