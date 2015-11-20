package com.rhythmdiao.result;

public enum StatusCode {
    SUCCESS(200), CREATED(201), BAD_REQUEST(400), UNAUTHORIZED(401), FORBIDDEN(403), NOT_FOUND(404), SERVER_ERROR(500);

    private int statusCode;

    StatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
