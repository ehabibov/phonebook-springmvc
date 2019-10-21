package com.ehabibov.springmvc.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"statusCode", "url", "message"})
public class ExceptionInfo {

    private int statusCode;
    private String url;
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("ExceptionInfo(statusCode=%s,url=%s,message=%s)", this.statusCode, this.url, this.message);
    }
}
