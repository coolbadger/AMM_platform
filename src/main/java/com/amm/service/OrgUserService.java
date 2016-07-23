package com.amm.service;

import com.amm.entity.OrgUserEntity;

/**
 * Created by csw on 2016/7/22 19:54.
 * Explain:
 */
public interface OrgUserService {

    OrgUserEntity findOrgUser(String username, String password);
}
