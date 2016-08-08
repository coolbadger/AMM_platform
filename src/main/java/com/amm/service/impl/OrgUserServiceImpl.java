package com.amm.service.impl;

import com.amm.entity.OrgUserEntity;
import com.amm.exception.ObjectNotFoundException;
import com.amm.repository.OrgUserRepository;
import com.amm.service.OrgUserService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    @Override
    public OrgUserEntity findByUserName(String userName) {

        Validate.notNull(userName, "The userName must not be null, find failure.");

        return orgUserRepository.findByUserName(userName);
    }

    @Transactional
    public OrgUserEntity createOrgUser(OrgUserEntity orgUserEntity) {
        Validate.notNull(orgUserEntity, "The orgUser must not be null, create failure.");

        OrgUserEntity created = orgUserRepository.save(orgUserEntity);

        return created;
    }

    public List<OrgUserEntity> findAllOrgUserByActive(Boolean active) {

        return orgUserRepository.findByActive(active);//按active字段为true查找，即为查找所有
    }

    @Transactional
    public OrgUserEntity updateOrgUser(OrgUserEntity orgUser) {

        Validate.notNull(orgUser.getId(), "The id of orgUser must not be null, update failure.");
        Validate.notNull(orgUser, "The orgUser object must not be null, update failure.");

        OrgUserEntity saved = this.findById(orgUser.getId());
        if(saved == null) {
            throw new ObjectNotFoundException("用户不存在");
        }

        saved = orgUser.changeUpdateInfoToSave(saved);

        saved = orgUserRepository.save(saved);

        return saved;
    }

    public OrgUserEntity findById(Integer id) {

        Validate.notNull(id, "The id must not be null, find failure.");

        return orgUserRepository.findOne(id);
    }

    @Transactional
    public OrgUserEntity deleteOrgUser(Integer id) {

        Validate.notNull(id, "The id must not be null, delete failure.");

        OrgUserEntity deleted = this.findById(id);
        if(deleted == null) {
            throw new ObjectNotFoundException("用户不存在");
        }

        deleted.setActive(Boolean.FALSE);
        deleted = orgUserRepository.save(deleted);

        return deleted;
    }
}
