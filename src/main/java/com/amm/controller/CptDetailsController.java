package com.amm.controller;

import com.amm.entity.BaseOrgEntity;
import com.amm.entity.MachineEntity;
import com.amm.service.BaseOrgService;
import com.amm.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 杨思名 on 2017/2/9.
 */


@RestController
@RequestMapping("api/CptDetails")
public class CptDetailsController extends BaseController{

    @Autowired
    private BaseOrgService baseOrgService;

    @Autowired
    private MachineService machineService;

    @RequestMapping(method = RequestMethod.GET)
    public List<BaseOrgEntity> getAllByActive(@RequestParam(required = false, defaultValue = "true") Boolean active) {
        List<BaseOrgEntity> ListBaseOrgEntity=baseOrgService.findAllBaseOrgByActive(active);
        for(int i=0;i<ListBaseOrgEntity.size();i++){
            Integer BaseOrgId=ListBaseOrgEntity.get(i).getId();
            String OrgName=ListBaseOrgEntity.get(i).getOrgName();
            List<MachineEntity>  listMachineEntity=machineService.findByOrgId(BaseOrgId);
            Integer machSize=listMachineEntity.size();
        }
        return ListBaseOrgEntity;
    }


}
