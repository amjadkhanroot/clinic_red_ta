import {environment} from "../../environments/environment";
import {InjectionToken} from "@angular/core";

export const ROUTES_CONFIG = new InjectionToken('routes.config');

const baseAPIURL = environment.baseAPIURL;
// if it ended by a "/" that means it needs a Path Variable.

const basePaths = {
  home: '',
  auth: 'auth',
  clinic: 'clinic',
};

const homeRoutes = {
  routeName: '',

  routes: {
    page404: `${basePaths.home}` + 'not-found'
  },

};

const authRoutes = {
  routeName: 'auth',

  routes: {
    login: basePaths.auth + '/login',
    register: basePaths.auth + '/register',
  },
  endpoints: {
    login: baseAPIURL + '/auth/login',
    register: baseAPIURL + '/auth/register',
  }

};

const clinicRoutes = {
  routeName: 'clinic',

  routes: {
    profiles: '/doctor/patient-profiles',
  },
  endpoints: {
    PatientRoute: {
      getProfile: baseAPIURL + '/clinic/patient/profile',
      getRecords: baseAPIURL + '/clinic/patient/records',
      getRecordById: baseAPIURL + '/clinic/patient/records/',
    },

    DoctorRoute: {
      getAllPatient: baseAPIURL + '/clinic/doctor/patient-list',
      createProfile: baseAPIURL + '/clinic/doctor/patient-profiles/create',
      updateProfile: baseAPIURL + '/clinic/doctor/patient-profiles/update/',

      createRecord: baseAPIURL + '/clinic/doctor/records/create',
      updateRecord: baseAPIURL + '/clinic/doctor/records/update/',
      deleteRecord: baseAPIURL + '/clinic/doctor/records/delete/',

      profiles: baseAPIURL + '/clinic/doctor/patient-profiles',
      profilesId: baseAPIURL + '/clinic/doctor/patient-profiles/',
      recordsByProfile: baseAPIURL + '/clinic/doctor/records-by-profile/',
      recordById: baseAPIURL + '/clinic/doctor/records/',

      profilesHelpers: baseAPIURL + '/clinic/doctor/get-clinic-helpers',
      upload: baseAPIURL + '/clinic/doctor/upload-attachment/',
      download: baseAPIURL + '/clinic/doctor/download-attachment',
    }
  }
};

export const RoutesConfig: any = {
  basePaths,
  homeRoutes,
  authRoutes,
  clinicRoutes
};
