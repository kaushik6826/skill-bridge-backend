package com.skillBridge.user.dto;

public class CommonResponse {
    public String status;
    public String message;
    public Object result;
    public CommonResponse() {
    }
    public CommonResponse(String status, String message, Object result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getResult() {
        return result;
    }
    public void setResult(Object result) {
        this.result = result;
    }
}
