package com.amjadcode.commons;

public enum ResponseErrorCode {
    ROLE_IS_NOT_FOUND("R400","Role is not found.");

    private final String code;
    private final String name;

    ResponseErrorCode(final String code, final String name) {
        this.code = code;
        this.name = name;
    }

    public String code() {
        return code;
    }
    public String codeDesc() {
        return name;
    }
}
