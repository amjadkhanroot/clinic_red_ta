export interface ILoggedIn {
  accountNonExpired: any;
  accountNonLocked: any;
  active: any;
  authorities: IAuthorities[]
  credentialsNonExpired: any;
  email: any;
  enabled: any;
  id:any;
  username: any;
}

interface IAuthorities {
  authority: any;

}

export const ROLES = {
  DOCTOR: "ROLE_DOCTOR",
  PATIENT: "ROLE_PATIENT",
}
