import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientProfileDetailsPageComponent } from './patient-profile-details-page.component';

describe('PatientProfileDetailsPageComponent', () => {
  let component: PatientProfileDetailsPageComponent;
  let fixture: ComponentFixture<PatientProfileDetailsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientProfileDetailsPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientProfileDetailsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
