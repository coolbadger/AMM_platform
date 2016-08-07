package com.amm.service.impl;

import com.amm.entity.BaseOrgEntity;
import com.amm.exception.ObjectNotFoundException;
import com.amm.repository.BaseOrgRepository;
import com.amm.repository.OrgUserRepository;
import com.amm.service.BaseOrgService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by csw on 2016/7/24 19:35.
 * Explain:
 */
@Component("baseOrgService")
@Scope("prototype")
public class BaseOrgServiceImpl extends BaseService implements BaseOrgService {

    @Autowired
    private BaseOrgRepository baseOrgRepository;

    @Autowired
    private OrgUserRepository orgUserRepository;

    @Transactional
    public BaseOrgEntity createOrg(BaseOrgEntity baseOrgEntity) {

        Validate.notNull(baseOrgEntity, "The baseOrg object must not be null, create failure.");

        BaseOrgEntity baseOrgEntity1 = baseOrgRepository.save(baseOrgEntity);

        return baseOrgEntity1;
    }

    public List<BaseOrgEntity> findAllBaseOrg() {

        return (List<BaseOrgEntity>) baseOrgRepository.findAll();
    }

    @Transactional
    public BaseOrgEntity updateBaseOrg(BaseOrgEntity baseOrg) {

        Validate.notNull(baseOrg.getId(), "The id of baseOrg must not be null, update failure.");
        Validate.notNull(baseOrg, "The baseOrg object must not be null, update failure.");

        BaseOrgEntity saved = this.findOne(baseOrg.getId());
        if(saved == null) {
            throw new ObjectNotFoundException("组织机构不存在");
        }

        //将传过来的需要改变的对象，赋值给从数据库中取出来的对象saved，然后对saved对象进行save保存
        saved = baseOrg.changeUpdateInfoToSave(saved);

        saved = baseOrgRepository.save(saved);

        return saved;
    }

    public BaseOrgEntity findOne(Integer id) {

        Validate.notNull(id, "The id must not be null, find failure.");

        return baseOrgRepository.findOne(id);
    }

    @Transactional
    public BaseOrgEntity deleteBaseOrg(Integer id) {

        Validate.notNull(id, "The id of baseOrg must not be null, delete failure.");

        BaseOrgEntity deleted = baseOrgRepository.findOne(id);
        if(deleted == null) {
            throw new ObjectNotFoundException("组织机构不存在");
        }
        deleted.setActive(Boolean.FALSE);//改变active为false，则表示数据库这条记录被删除
        baseOrgRepository.save(deleted);

        return deleted;
    }

    public List<BaseOrgEntity> findAllBaseOrgByActive(Boolean active) {

        return baseOrgRepository.findByActive(active);//按active字段为true查找，即为查找所有
    }
}
