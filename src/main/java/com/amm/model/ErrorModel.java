package com.amm.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by csw on 2016/5/26 18:23.
 *
 * explain：
 */
public class ErrorModel {

    @JsonProperty("msg")
    private String msg;

    @JsonProperty("code")
    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
