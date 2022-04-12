import { TestBed } from '@angular/core/testing';

import { MyPatientRecordDetailsResolver } from './my-patient-record-details.resolver';

describe('MyPatientRecordDetailsResolver', () => {
  let resolver: MyPatientRecordDetailsResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(MyPatientRecordDetailsResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
