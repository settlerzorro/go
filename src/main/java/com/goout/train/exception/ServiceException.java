package com.goout.train.exception;

import com.goout.train.enums.BusinessErrorCode;
import com.goout.train.enums.PlatformErrorCode;

public class ServiceException extends RuntimeException {
    /**
     * 错误码
     */
    private String code;

    /*public ServiceException( String code, String message) {
        super(message);
        this.code = code;
    }*/

    public ServiceException(BusinessErrorCode businessErrorCode) {
        super(businessErrorCode.getMessage());
        this.code = businessErrorCode.getCode();
    }

    public ServiceException(BusinessErrorCode businessErrorCode, String extraMessage) {
        super(businessErrorCode.getMessage() + "|" + extraMessage);
        this.code = businessErrorCode.getCode();
    }

    public ServiceException(BusinessErrorCode businessErrorCode, Throwable cause) {
        super(businessErrorCode.getMessage(), cause);
        this.code = businessErrorCode.getCode();
    }

    public ServiceException(BusinessErrorCode businessErrorCode, Throwable cause, String extraMessage) {
        super(businessErrorCode.getMessage() + "|" + extraMessage, cause);
        this.code = businessErrorCode.getCode();
    }

    public ServiceException(PlatformErrorCode platformErrorCode) {
        super(platformErrorCode.getMessage());
        this.code = platformErrorCode.getCode();
    }

    public ServiceException(PlatformErrorCode platformErrorCode, String extraMessage) {
        super(platformErrorCode.getMessage() + "|" + extraMessage);
        this.code = platformErrorCode.getCode();
    }

    public ServiceException(PlatformErrorCode businessErrorCode, Throwable cause) {
        super(businessErrorCode.getMessage(), cause);
        this.code = businessErrorCode.getCode();
    }

    public ServiceException(PlatformErrorCode businessErrorCode, Throwable cause, String extraMessage) {
        super(businessErrorCode.getMessage() + "|" + extraMessage, cause);
        this.code = businessErrorCode.getCode();
    }

    /*public ServiceException( String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }*/

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
