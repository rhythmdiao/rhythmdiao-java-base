package rest.result;

public enum CustomStatusCode {
    SUCCESS(200), NOT_FOUND(404), BAD_PARAMETER(400), SERVER_ERROR(500);

    private int statusCode;

    private CustomStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}