package com.api.nbp.exception;

public class NbpApiException extends RuntimeException {

    private final int statusCode;

    public NbpApiException(int statusCode, String responseBody) {
        super(resolveMessage(statusCode, cleanResponseBody(responseBody)));
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    private static String resolveMessage(int statusCode, String responseBody) {
        return switch (statusCode) {
            case 400 -> responseBody == null
                    ? "NBP API rejected the request. Check currency code and date range; a single request cannot cover more than 93 days."
                    : "NBP API rejected the request: " + responseBody;
            case 404 -> responseBody == null
                    ? "NBP API returned no data for the given currency and date range."
                    : "NBP API returned no data: " + responseBody;
            case 429 -> "NBP API rate limit was exceeded. Try again later.";
            case 500, 502, 503, 504 -> "NBP API is temporarily unavailable. Try again later.";
            default -> responseBody == null
                    ? "NBP API returned HTTP status " + statusCode
                    : "NBP API returned HTTP status " + statusCode + ": " + responseBody;
        };
    }

    private static String cleanResponseBody(String responseBody) {
        if (responseBody == null || responseBody.isBlank()) {
            return null;
        }
        return responseBody.trim();
    }
}
