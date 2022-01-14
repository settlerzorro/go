package com.goout.train.model.response;

public class RestResponse {
    private int code;
    private String message;
    private Object data;

    private RestResponse() {
    }

    private RestResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static RestResponse getResponse(int code, String message, Object data) {
        RestResponse restResponse = new RestResponse(code, message, data);
        return restResponse;
    }

    public static RestResponse getResponse(ResultCode resultCode, Object data) {
        return getResponse(resultCode.code(), resultCode.message(), data);
    }

    public static RestResponse getResponse(ResultCode resultCode) {
        return getResponse(resultCode, null);
    }

    public static RestResponse getResponse(int code, String message) {
        return getResponse(code, message, null);
    }

    public static RestResponse succuess(Object data) {
        return getResponse(ResultCode.SUCCESS, data);
    }

    public static RestResponse fail(String message) {
        return getResponse(ResultCode.FAIL.code(), message);
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}