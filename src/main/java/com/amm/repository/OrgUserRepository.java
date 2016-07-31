package com.amm.repository;

import com.amm.entity.OrgUserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by csw on 2016/7/22 19:59.
 * Explain:
 */
public interface OrgUserRepository extends PagingAndSortingRepository<OrgUserEntity, Integer>{
    OrgUserEntity findByUserNameAndPassword(String username, String password);

    List<OrgUserEntity> findByActive(Boolean active);
}
