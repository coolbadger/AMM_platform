package com.amm.controller;

import com.amm.constant.ExceptionCode;
import com.amm.model.ResultModel;
import org.apache.commons.lang3.Validate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by csw on 2016/7/25 22:56.
 * Explain: 系统管理员接口
 */

@RestController
@RequestMapping("api/system")
public class SystemController {

    // TODO:2017/3/1 需要新增用户权限列表返回
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultModel login(@RequestParam(value = "password", required = false) String password,
                             @RequestParam(value = "username", required = false) String username) {
        Validate.notNull(username, "The username must not be null");
        Validate.notNull(password, "The password must not be null");

        ResultModel resultModel = new ResultModel(ExceptionCode.LOGIN_SUCCESS);

        // todo：test
        return resultModel;
    }
}
