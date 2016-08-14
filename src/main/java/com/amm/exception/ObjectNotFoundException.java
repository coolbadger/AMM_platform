package com.amm.exception;

import com.amm.constant.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by csw on 2016/5/30 9:24.
 * explain：数据库中查找不到对象
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "所查找的对象不存在")
public class ObjectNotFoundException extends PlatformException {

    public ObjectNotFoundException(String description) {

        super(HttpStatus.NOT_FOUND, description, ErrorCode.USERNOTFOUND);
    }
}
