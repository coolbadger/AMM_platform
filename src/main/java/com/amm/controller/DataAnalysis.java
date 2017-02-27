package com.amm.controller;

import com.amm.entity.BaseOrgEntity;
import com.amm.entity.WorkerEntity;
import com.amm.entity.client.GpsData;
import com.amm.gps.GpsConvert;
import com.amm.service.BaseOrgService;
import com.amm.service.GpsRecordService;
import com.amm.service.RefMachTerminalService;
import com.amm.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 杨思名 on 2017/2/21.
 */
@RestController
@RequestMapping("api/DataAnalysis")
public class DataAnalysis extends BaseController{
    GpsConvert gpsConvert;
    @Autowired
    private BaseOrgService baseOrgService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private GpsRecordService gpsRecordService;

    @Autowired
    private RefMachTerminalService refMachTerminalService;



    @RequestMapping(value = "/gpsDataAnalysis", method = RequestMethod.GET)
    public List<GpsData> getAllGpsData() {
        List<BaseOrgEntity> ListBaseOrg=baseOrgService.findAllBaseOrg();
        List<WorkerEntity> ListWorker=workerService.findAllByActive(true);
        List<GpsData> GpsDataList = new ArrayList<GpsData>();
            for(int i=0;i<ListBaseOrg.size();i++){
                GpsData gpsData=new GpsData();
                gpsData.setOrgAddress(ListBaseOrg.get(i).getOrgAddress());
                gpsData.setOrgName(ListBaseOrg.get(i).getOrgName());
                gpsData.setOrgCode(ListBaseOrg.get(i).getOrgCode());
                gpsData.setOrgContact(ListBaseOrg.get(i).getOrgContact());
                GpsDataList.add(gpsData);
                    for(int a=0;a<ListWorker.size();a++){
                        if(ListWorker.get(a).getOrgId()==ListBaseOrg.get(i).getId()){
                            gpsData.setUserName(ListWorker.get(a).getUserName());
                        }else{
                            gpsData.setUserName("暂无合作社");
                        }
                    }
            }
        return  GpsDataList;
    }

}
