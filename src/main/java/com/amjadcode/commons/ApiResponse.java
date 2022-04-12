package com.amjadcode.commons;


import lombok.Data;

@Data
public class ApiResponse {

    private Boolean success;
    private String code;
    private String message;
    private String description;
    private Object data;
    private String accessToken;

    public ApiResponse() {
    }

    public ApiResponse(Boolean success, String code, String message, String description) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public ApiResponse(Boolean success, String code, String message, String description, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.description = description;
        this.data = data;
    }

    public ApiResponse(Boolean success, String code, String message, String description, Object data, String accessToken) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.accessToken = accessToken;
        this.description = description;
        this.data = data;
    }
}
