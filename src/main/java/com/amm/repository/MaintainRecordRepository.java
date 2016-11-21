package com.amm.repository;

import com.amm.entity.MaintainRecordEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by 杨思名 on 2016/11/17.
 */
public interface MaintainRecordRepository extends PagingAndSortingRepository<MaintainRecordEntity,Integer>{


    List<MaintainRecordEntity> findAllBymachId(Boolean active);

    MaintainRecordEntity findByMachId(Integer id);

    MaintainRecordEntity findById(Integer id);


}
