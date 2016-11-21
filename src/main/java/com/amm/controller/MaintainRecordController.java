package com.amm.controller;

import com.amm.entity.MachineEntity;
import com.amm.entity.MaintainRecordEntity;
import com.amm.entity.client.Maintainrecord;
import com.amm.service.MachineService;
import com.amm.service.MaintainRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
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
    public List<Maintainrecord> getAllByActive(@RequestParam(required = false, defaultValue = "true") Boolean active) {
        List<Maintainrecord> findalls=maintainRecordService.findAll(active);
        return findalls;
    }

    /*查询单个*/
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MaintainRecordEntity findOne(@PathVariable Integer id){
        return  maintainRecordService.findById(id);
    }

    /*删除*/
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id){
        maintainRecordService.delete(id);
    }


    /*修改*/
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public MaintainRecordEntity update(@PathVariable Integer id,@RequestBody MaintainRecordEntity MaintainRecord) throws UnsupportedEncodingException {
        String maintainInfo=MaintainRecord.getMaintainInfo();
        String stTitlePost = new String(maintainInfo.getBytes("ISO-8859-1"),"UTF-8");
        System.out.println(MaintainRecord.getMaintainInfo()+"id="+id+"MaintainRecord="+MaintainRecord);
        MaintainRecord.setId(id);
        MaintainRecordEntity maintainRecordEntity=maintainRecordService.update(MaintainRecord);
        return  maintainRecordEntity;
    }

}
