import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentFriendsComponent } from './current-friends.component';

describe('CurrentFriendsComponent', () => {
  let component: CurrentFriendsComponent;
  let fixture: ComponentFixture<CurrentFriendsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CurrentFriendsComponent]
    });
    fixture = TestBed.createComponent(CurrentFriendsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
