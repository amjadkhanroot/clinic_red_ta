import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePatientProfilePageComponent } from './create-patient-profile-page.component';

describe('CreatePatientProfilePageComponent', () => {
  let component: CreatePatientProfilePageComponent;
  let fixture: ComponentFixture<CreatePatientProfilePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatePatientProfilePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatePatientProfilePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
