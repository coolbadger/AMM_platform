package com.amm.controller;

import com.amm.entity.MachineEntity;
import com.amm.entity.MaintainRecordEntity;
import com.amm.service.MachineService;
import com.amm.service.MaintainRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 杨思名 on 2016/11/17.
 */

@RestController
@RequestMapping("api/MaintainRecor")
public class MaintainRecordController extends BaseController{
    @Autowired
    private MachineService machineService;

    @Autowired
    private MaintainRecordService maintainRecordService;

    /*查询所有维护农机信息*/
    @RequestMapping(method = RequestMethod.GET)
    public List<MaintainRecordEntity> getAllByActive(@RequestParam(required = false, defaultValue = "true") Boolean active) {
        List<MaintainRecordEntity> findalls=maintainRecordService.findAll(active);
        return findalls;
    }

    /*查询单个*/
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MaintainRecordEntity findOne(@PathVariable Integer id){
        return  maintainRecordService.findById(id);
    }




}
