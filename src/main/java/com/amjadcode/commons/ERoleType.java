package com.amjadcode.commons;

public enum ERoleType {
    ROLE_ADMIN,
    ROLE_PATIENT,
    ROLE_DOCTOR;



    public static Boolean isExist(String role){
        for (ERoleType record:  ERoleType.values()) {
            if (role.equals(record.name()))
                return true;
        }
        return false;
    }
}
