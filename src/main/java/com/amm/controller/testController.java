package com.amm.controller;

import com.amm.entity.MachineEntity;
import com.amm.service.MachineService;
import com.amm.service.MaintainRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 杨思名 on 2016/11/17.
 */

@RestController
@RequestMapping("api/users")
public class testController extends BaseController{
    @Autowired
    private MachineService machineService;

    @Autowired
    private MaintainRecordService maintainRecordService;


    @RequestMapping(method = RequestMethod.GET)
    public List<MachineEntity> getAllByActive(@RequestParam(required = false, defaultValue = "true") Boolean active) {

        List a=maintainRecordService.findAll(active);

       // List<MachineEntity> machineEntityList = machineService.findAllByActive(active);

        return a;
    }


}
