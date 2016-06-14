package com.amm.service;

import com.amm.entity.User;

/**
 * Created by csw on 2016/6/8 11:01.
 * explain：
 */
public interface UserService {
    User findUser(String username, String password);
}
