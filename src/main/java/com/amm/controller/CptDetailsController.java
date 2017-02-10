package com.amm.controller;

import com.amm.entity.BaseOrgEntity;
import com.amm.entity.MachineEntity;
import com.amm.entity.client.CptDetails;
import com.amm.service.BaseOrgService;
import com.amm.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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


}
