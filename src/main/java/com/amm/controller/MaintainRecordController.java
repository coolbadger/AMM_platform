package com.amm.controller;

import com.amm.entity.MachineEntity;
import com.amm.entity.MaintainRecordEntity;
import com.amm.entity.client.Maintainrecord;
import com.amm.exception.ObjectNotFoundException;
import com.amm.service.MachTerminalService;
import com.amm.service.MachineService;
import com.amm.service.MaintainRecordService;
import org.apache.commons.lang3.Validate;
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
@RequestMapping("api/MaintainRecord")
public class MaintainRecordController extends BaseController{


    @Autowired
    private MachTerminalService machTerminalService;

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
        Validate.notNull(id, "The id of machine must not be null, update failure.");
        return  maintainRecordService.findById(id);
    }

    /*删除*/
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id){
        Validate.notNull(id, "The id of machine must not be null, update failure.");
        maintainRecordService.delete(id);
    }

    /*修改*/
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public MaintainRecordEntity update(@PathVariable Integer id,@RequestBody Maintainrecord maintainrecord){
        Validate.notNull(id, "The id of machine must not be null, update failure.");
        maintainrecord.setId(id);
        MaintainRecordEntity maintainRecord=new MaintainRecordEntity();
        maintainRecord.setMaintainInfo(maintainrecord.getMaintainInfo());
        maintainRecord.setId(maintainrecord.getId());
        maintainRecord.setMachId(maintainrecord.getMachId());
        MaintainRecordEntity maintainRecordEntity=maintainRecordService.update(maintainRecord);
        return  maintainRecordEntity;
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MaintainRecordEntity create(@RequestBody(required = true) MaintainRecordEntity maintainRecordEntity) {
        MaintainRecordEntity m=new MaintainRecordEntity();
        Integer machId=maintainRecordEntity.getMachId();
       /* if(machTerminalService.findByMachId(machId)==null&&machTerminalService.findByMachId(machId).equals("")){
            throw new ObjectNotFoundException("不存在此machId");
        }else{*/
            m.setMaintainInfo(maintainRecordEntity.getMaintainInfo());
            m.setMachId(machId);
       /* }*/
        MaintainRecordEntity created=maintainRecordService.create(m);

        return created;
    }
}
