package com.amm.controller;

import com.amm.constant.ExceptionCode;
import com.amm.entity.BaseOrgEntity;
import com.amm.entity.OrgUserEntity;
import com.amm.exception.InvalidOperatorException;
import com.amm.model.ResultModel;
import com.amm.service.BaseOrgService;
import com.amm.service.OrgUserService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by csw on 2016/7/22 19:45.
 * Explain: 管理员用户接口
 */

@RestController
@RequestMapping("api/orgUsers")
public class OrgUserController extends BaseController{

    @Autowired
    private OrgUserService orgUserService;

    @Autowired
    private BaseOrgService baseOrgService;

    /**
     * 管理员登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultModel login(@RequestParam(value = "password", required = false) String password,
                             @RequestParam(value = "username", required = false) String username) {
        Validate.notNull(username, "The username must not be null.");
        Validate.notNull(password, "The password must not be null.");

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
    @ResponseStatus(HttpStatus.CREATED)
    public OrgUserEntity create(@RequestBody(required = true) OrgUserEntity orgUserEntity) {

        Validate.notNull(orgUserEntity, "The orgUser must not be null, create failure.");
        Validate.notNull(orgUserEntity.getUserName(), "The userName of orgUser must not be null, create failure.");
        Validate.notNull(orgUserEntity.getPassword(), "The password of orgUser must not be null, create failure.");

        if(!orgUserService.isValidUserName(orgUserEntity.getUserName())) {
            throw new InvalidOperatorException("用户名无效，数据库中已存在！");
        }

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = userDetails.getUsername();
        String password = userDetails.getPassword();
        OrgUserEntity currentUser = orgUserService.findOrgUser(userName, password);
        Validate.notNull(currentUser, "The currentUser is null, no user login, create failure.");

        orgUserEntity.setOrgId(currentUser.getOrgId());
        orgUserEntity.setCreator(currentUser.getUserName());
        orgUserEntity.setCreateTime(new Date());

        OrgUserEntity created = orgUserService.createOrgUser(orgUserEntity);

        return created;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<OrgUserEntity> getAllByActive(@RequestParam(required = false, defaultValue = "true") Boolean active) {

        List<OrgUserEntity> orgUserEntityList = orgUserService.findAllOrgUserByActive(active);

        return orgUserEntityList;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public OrgUserEntity update(@PathVariable Integer id,
                                @RequestBody OrgUserEntity orgUser) {

        Validate.notNull(id, "The id of orgUser must not be null, update failure.");
        Validate.notNull(orgUser, "The orgUser object must not be null, update failure.");

        orgUser.setId(id);

        OrgUserEntity updated = orgUserService.updateOrgUser(orgUser);

        return updated;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public OrgUserEntity findOne(@RequestParam(required = true) Integer id) {

        Validate.notNull(id, "The id must not be null, find failure.");

        return orgUserService.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public OrgUserEntity delete(@PathVariable Integer id) {

        Validate.notNull(id, "The id must not be null, delete failure.");

        return orgUserService.deleteOrgUser(id);
    }
}
