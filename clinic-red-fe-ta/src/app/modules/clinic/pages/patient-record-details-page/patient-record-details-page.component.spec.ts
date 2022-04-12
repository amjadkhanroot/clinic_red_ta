import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientRecordDetailsPageComponent } from './patient-record-details-page.component';

describe('PatientRecordDetailsPageComponent', () => {
  let component: PatientRecordDetailsPageComponent;
  let fixture: ComponentFixture<PatientRecordDetailsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientRecordDetailsPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientRecordDetailsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
