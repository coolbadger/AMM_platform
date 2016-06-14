package com.amm.controller;

import com.amm.entity.Operator;
import com.amm.service.OperatorService;
import org.apache.commons.lang3.Validate;
import org.springframework.web.bind.annotation.*;

/**
 * Created by csw on 2016/6/7 19:11.
 * explain：
 */
@RestController
@RequestMapping("api/operators")
public class OperatorController extends BaseController{

    private OperatorService operatorService;

    @RequestMapping(method = RequestMethod.POST)
    public Operator create(@RequestParam(value = "realName") String realName,
                           @RequestParam(value = "password", defaultValue = "888888", required = false) String password,
                           @RequestParam(value = "phone") String phone,
                           @RequestBody(required = false) Operator operator) {
        Validate.notNull(realName, "userName must not be null");
        Validate.notNull(phone, "phone must not be null");
        return null;
    }
}
