package com.amm.controller;

import com.amm.constant.ExceptionCode;
import com.amm.entity.OrgUserEntity;
import com.amm.model.ResultModel;
import com.amm.service.OrgUserService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by csw on 2016/7/22 19:45.
 * Explain: 管理员用户接口
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

    @RequestMapping(method = RequestMethod.POST)
    public OrgUserEntity create(@RequestParam(value = "baseOrgId", required = true) Integer baseOrgId,
                                @RequestBody(required = true) OrgUserEntity orgUserEntity) {

        Validate.notNull(orgUserEntity, "The orgUser must not be null, create failure");
        Validate.notNull(baseOrgId, "The baseOrgId must not be null, create failure");


        return null;
    }
}
