package com.amm.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by csw on 2016/5/26 19:29.
 * explain：
 */
public class PlatformException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private HttpStatus httpStatus;

    private String code;

    public PlatformException() {
        super();
    }

    public PlatformException(String message, String code) {
        super(message);
        this.code = code;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public PlatformException(HttpStatus httpStatus, String message, String code) {
        super(message);
        this.httpStatus = httpStatus;
        this.code = code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
