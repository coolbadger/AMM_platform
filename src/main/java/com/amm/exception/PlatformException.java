package com.amm.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by csw on 2016/5/26 19:29.
 * explain：
 */
public class PlatformException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private HttpStatus httpStatus;

    private String errorCode;

    public PlatformException() {
        super();
    }

    public PlatformException(String message, String errCode) {
        super(message);
        this.errorCode = errCode;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public PlatformException(HttpStatus httpStatus, String message, String errorCode) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
