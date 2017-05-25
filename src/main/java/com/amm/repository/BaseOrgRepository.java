package com.amm.repository;

import com.amm.entity.BaseOrgEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by csw on 2016/7/24 19:42.
 * Explain:
 */
public interface BaseOrgRepository extends PagingAndSortingRepository<BaseOrgEntity, Integer> {

    List<BaseOrgEntity> findByActive(Boolean active);

    List<BaseOrgEntity> findByOrgCodeAndActive(String orgCode,boolean active);
}
