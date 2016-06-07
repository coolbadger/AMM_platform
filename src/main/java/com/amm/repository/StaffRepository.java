package com.amm.repository;

import com.amm.entity.Staff;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by bilibili on 2015/10/23.
 */
public interface StaffRepository extends PagingAndSortingRepository<Staff, Integer> {
}
