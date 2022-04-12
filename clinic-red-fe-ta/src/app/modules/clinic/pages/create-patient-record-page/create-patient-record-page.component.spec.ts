import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePatientRecordPageComponent } from './create-patient-record-page.component';

describe('CreatePatientRecordPageComponent', () => {
  let component: CreatePatientRecordPageComponent;
  let fixture: ComponentFixture<CreatePatientRecordPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatePatientRecordPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatePatientRecordPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
