package com.amm.controller;

import com.amm.constant.ExceptionCode;
import com.amm.entity.OrgUserEntity;
import com.amm.model.ResultModel;
import com.amm.service.OrgUserService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by csw on 2016/7/22 19:45.
 * Explain: 管理员用户表
 */

@RestController
@RequestMapping("api/orgUsers")
public class OrgUserController extends BaseController{

    @Autowired
    private OrgUserService orgUserService;

    /**
     * 管理员登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultModel login(@RequestParam(value = "password", required = false) String password,
                             @RequestParam(value = "username", required = false) String username) {
        Validate.notNull(username, "The username must not be null");
        Validate.notNull(password, "The password must not be null");

        ResultModel resultModel = null;
        OrgUserEntity orgUser = orgUserService.findOrgUser(username, password);
        if(orgUser != null) {
            resultModel = new ResultModel(ExceptionCode.LOGIN_SUCCESS);
        } else {
            resultModel = new ResultModel(ExceptionCode.LOGIN_FAILURE);
        }

        return resultModel;
    }
}
