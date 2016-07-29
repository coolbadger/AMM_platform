package com.amm.service.impl;

import com.amm.entity.OrgUserEntity;
import com.amm.repository.OrgUserRepository;
import com.amm.service.OrgUserService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by csw on 2016/7/22 19:56.
 * Explain:
 */

@Component("orgUserService")
@Scope("prototype")
public class OrgUserServiceImpl extends BaseService implements OrgUserService{

    @Autowired
    private OrgUserRepository orgUserRepository;

    public OrgUserEntity findOrgUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        Validate.notNull(username, "The username must not be null");
        Validate.notNull(password, "The password must not be null");

        OrgUserEntity orgUser = orgUserRepository.findByUserNameAndPassword(username, password);

        return orgUser;
    }
}
