package com.amm.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by csw on 2016/5/26 18:23.
 *
 * explain：
 */
public class ErrorModel {

    @JsonProperty("errMsg")
    private String exceptionResult;

    @JsonProperty("errCode")
    private String errorCode;

    public String getExceptionResult() {
        return exceptionResult;
    }

    public void setExceptionResult(String exceptionResult) {
        this.exceptionResult = exceptionResult;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
