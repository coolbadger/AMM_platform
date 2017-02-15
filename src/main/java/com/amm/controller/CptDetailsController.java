package com.amm.controller;

import com.amm.entity.*;
import com.amm.entity.client.CptDetails;
import com.amm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by 杨思名 on 2017/2/9.
 */


@RestController
@RequestMapping("api/CptDetails")
public class CptDetailsController extends BaseController{
    @Autowired
    private WorkerService workerService;
    @Autowired
    private BaseOrgService baseOrgService;
    @Autowired
    private MachineService machineService;

    @Autowired
    private GpsRecordService gpsRecordService;

    @Autowired
    private RefMachTerminalService refMachTerminalService;

    @RequestMapping(method = RequestMethod.GET)
    public List<CptDetails> getAllByActive(@RequestParam(required = false, defaultValue = "true") Boolean active) {
        List<BaseOrgEntity> ListBaseOrgEntity=baseOrgService.findAllBaseOrgByActive(active);
        List<CptDetails> ListCptDetails=new ArrayList<CptDetails>();
        for(int i=0;i<ListBaseOrgEntity.size();i++){
            CptDetails cptDetails=new CptDetails();
            Integer BaseOrgId=ListBaseOrgEntity.get(i).getId();
            String OrgName=ListBaseOrgEntity.get(i).getOrgName();
            List<MachineEntity>  listMachineEntity=machineService.findByOrgId(BaseOrgId);
            Integer machSize=listMachineEntity.size();
            cptDetails.setId(BaseOrgId);
            cptDetails.setOrgAddress(ListBaseOrgEntity.get(i).getOrgAddress());
            cptDetails.setOrgName(OrgName);
            cptDetails.setCreateTime( ListBaseOrgEntity.get(i).getCreateTime());
            cptDetails.setMachSize(machSize);
            cptDetails.setOrgContact(ListBaseOrgEntity.get(i).getOrgContact());
            cptDetails.setOrgEmail(ListBaseOrgEntity.get(i).getOrgEmail());
            cptDetails.setOrgTell(ListBaseOrgEntity.get(i).getOrgTell());
            cptDetails.setOrgCode(ListBaseOrgEntity.get(i).getOrgCode());
            cptDetails.setOrgFax(ListBaseOrgEntity.get(i).getOrgFax());
            cptDetails.setCreator(ListBaseOrgEntity.get(i).getCreator());
            ListCptDetails.add(cptDetails);

        }
        return ListCptDetails;
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<MachineEntity> findOne(@PathVariable Integer id) {
        return machineService.findByOrgId(id);
    }



}
