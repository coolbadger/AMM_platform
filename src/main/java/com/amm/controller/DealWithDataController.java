package com.amm.controller;

import com.amm.entity.GpsRecordEntity;
import com.amm.service.GpsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 杨思名 on 2017/2/13.
 */

@RestController
@RequestMapping("api/DealWithData")
public class DealWithDataController {

    @Autowired
    private GpsRecordService gpsRecordService;

    @RequestMapping(method = RequestMethod.GET)
    public List<GpsRecordEntity> findAll(){
        return gpsRecordService.getFirst();
    }




}
