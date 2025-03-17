package dev.goral.responses;

import dev.goral.interfaces.Response;

public class ErrorResponse implements Response {

    String errorMessage;
    int errorCode;

    public ErrorResponse(String errorMessage, int errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }
}
