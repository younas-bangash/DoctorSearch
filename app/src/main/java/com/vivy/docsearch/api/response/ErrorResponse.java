package com.vivy.docsearch.api.response;

/**
 * Model class for representing the error results
 */
public class ErrorResponse {
    private String error;
    private String timestamp;
    private int status;
    private String message;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorResponse(){
        // Empty constructor
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
