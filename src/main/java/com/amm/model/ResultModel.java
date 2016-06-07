package com.amm.model;

import com.amm.constant.ExceptionCode;

/**
 * Created by csw on 2016/5/25.
 * annotation：结果模板
 */

public class ResultModel {

    private final Integer code;

    private final String msg;

    public ResultModel(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultModel(ExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.msg = exceptionCode.name();
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
