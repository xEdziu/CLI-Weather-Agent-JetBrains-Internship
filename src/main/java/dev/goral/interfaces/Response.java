package dev.goral.interfaces;

public interface Response {
    String errorMessage = null;
    int errorCode = 200;
    String getErrorMessage();
    int getErrorCode();
}
