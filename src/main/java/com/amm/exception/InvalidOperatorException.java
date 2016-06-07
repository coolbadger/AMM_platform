package com.amm.exception;

import com.amm.constant.ErrorCode;
import org.springframework.http.HttpStatus;

/**
 * Created by csw on 2016/5/26 19:34.
 * explain：
 */
public class InvalidOperatorException extends PlatformException {

    public InvalidOperatorException(String description) {
        super(HttpStatus.BAD_REQUEST, description, ErrorCode.INVAILDOPERATOR);
    }
}
