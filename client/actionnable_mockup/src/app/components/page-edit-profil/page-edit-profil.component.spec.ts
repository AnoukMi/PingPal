import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageEditProfilComponent } from './page-edit-profil.component';

describe('PageEditProfilComponent', () => {
  let component: PageEditProfilComponent;
  let fixture: ComponentFixture<PageEditProfilComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageEditProfilComponent]
    });
    fixture = TestBed.createComponent(PageEditProfilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
