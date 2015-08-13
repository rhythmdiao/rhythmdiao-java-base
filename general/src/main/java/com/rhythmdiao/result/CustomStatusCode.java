package com.rhythmdiao.result;

public enum CustomStatusCode {
    SUCCESS(200), NOT_FOUND(404), BAD_PARAMETER(400), SERVER_ERROR(500), UNKNOWN_HTTP_SOURCE(888);

    private int statusCode;

    CustomStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
