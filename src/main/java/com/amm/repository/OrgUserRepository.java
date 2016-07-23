package com.amm.repository;

import com.amm.entity.OrgUserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by csw on 2016/7/22 19:59.
 * Explain:
 */
public interface OrgUserRepository extends PagingAndSortingRepository<OrgUserEntity, Integer>{
    OrgUserEntity findByUserAndPassword(String username, String password);
}
