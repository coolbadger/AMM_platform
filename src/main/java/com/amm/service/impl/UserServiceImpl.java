package com.amm.service.impl;

import com.amm.entity.User;
import com.amm.repository.UserRepository;
import com.amm.service.UserService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by csw on 2016/6/8 11:01.
 * explain：
 */
@Component("userService")
@Scope("prototype")
public class UserServiceImpl extends BaseService implements UserService{

    @Autowired
    private UserRepository userRepository;

    public User findUser(String username, String password) {
        Validate.notNull(username, "The username must not be null");
        Validate.notNull(password, "The password must not be null");

        User user = userRepository.findByUsernameAndPassword(username, password);
        return user;
    }
}
