import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageEditProfileComponent } from './page-edit-profile.component';

describe('PageEditProfilComponent', () => {
  let component: PageEditProfileComponent;
  let fixture: ComponentFixture<PageEditProfileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageEditProfileComponent]
    });
    fixture = TestBed.createComponent(PageEditProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
