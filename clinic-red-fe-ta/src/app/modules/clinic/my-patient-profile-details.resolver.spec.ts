import { TestBed } from '@angular/core/testing';

import { MyPatientProfileDetailsResolver } from './my-patient-profile-details.resolver';

describe('MyPatientProfileDetailsResolver', () => {
  let resolver: MyPatientProfileDetailsResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(MyPatientProfileDetailsResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
