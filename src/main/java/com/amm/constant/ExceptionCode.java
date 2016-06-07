package com.amm.constant;

/**
 * Created by csw on 2016/5/25.
 * annotation：
 */

public enum ExceptionCode {

    /**
     * Service error 3XXX
     * Not found: 4XXX
     * Invalid operator 5XXX
     */

    DELETE_SUCCESS(201),DELETE_FAIL(202);

    private Integer code;

    ExceptionCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
