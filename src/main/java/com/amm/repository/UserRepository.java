package com.amm.repository;

import com.amm.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by csw on 2016/6/8 10:59.
 * explain：
 */
public interface UserRepository extends PagingAndSortingRepository<User, Integer>{
    User findByUsernameAndPassword(String username, String password);
}
