package com.api.nbp.exception;

public class NbpApiException extends RuntimeException {

    private final int statusCode;

    public NbpApiException(int statusCode, String responseBody) {
        super(resolveMessage(statusCode, responseBody));
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    private static String resolveMessage(int statusCode, String responseBody) {
        if (responseBody == null || responseBody.isBlank()) {
            return "NBP API returned HTTP status " + statusCode;
        }
        return responseBody;
    }
}
