package com.amm.controller;

import com.amm.constant.ExceptionCode;
import com.amm.entity.User;
import com.amm.exception.InvalidOperatorException;
import com.amm.model.ResultModel;
import com.amm.service.UserService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by csw on 2016/6/8 10:19.
 * explain：
 */
@RestController
@RequestMapping("api/users")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultModel userLogin(@RequestParam(required = false) String username,
                                 @RequestParam(required = false) String password,
                                 @RequestBody User user) {
//        Validate.notNull(username, "The username must not be null");
//        Validate.notNull(password, "The password must not be null");

        ResultModel resultModel = new ResultModel(ExceptionCode.LOGIN_SUCCESS);

//        User user2 = userService.findUser(username, password);
        User user2 = userService.findUser(user.getUsername(), user.getPassword());
//        if(user2 != null) {
//            resultModel = new ResultModel(ExceptionCode.LOGIN_SUCCESS);
//        } else {
//            throw new InvalidOperatorException("用户名或密码错误");
//        }
        return resultModel;
    }
}
