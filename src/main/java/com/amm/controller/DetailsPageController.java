package com.amm.controller;

import com.amm.entity.*;
import com.amm.entity.client.DetailsPage;
import com.amm.service.BaseOrgService;
import com.amm.service.GpsRecordService;
import com.amm.service.RefMachTerminalService;
import com.amm.service.WorkerService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 杨思名 on 2017/2/24.
 */
@RestController
@RequestMapping("api/DetailsPage")
public class DetailsPageController extends BaseController{
    @Autowired
    private BaseOrgService baseOrgService;
    @Autowired
    private WorkerService workerService;
    @Autowired
    private GpsRecordService gpsRecordService;
    @Autowired
    private RefMachTerminalService refMachTerminalService;

    @RequestMapping(method = RequestMethod.GET)
    public List<DetailsPage> getAllByActive(@RequestParam(required = false, defaultValue = "true") Boolean active) {

        logger.info(String.format("Receive find all baseOrg (by active is true)."));

        List<BaseOrgEntity> baseOrgEntityList = baseOrgService.findAllBaseOrgByActive(active);//查询出所有合作社
        List<WorkerEntity> listWorker=workerService.findAllByActive(active);//查询所有司机
        List<GpsRecordEntity> ListGps=gpsRecordService.getFirst();//分类查询
        List<RefMachTerminalEntity> listRef=refMachTerminalService.findAll();//拿到农机以及终端信息
        List<DetailsPage> temp=new ArrayList<DetailsPage>();
            for(int i=0;i<baseOrgEntityList.size();i++){
                Integer tempMachSize=0;
                float tempDrivingArea= 0;
                float tempWorkArea=0;
                float tempWorkTime=0;
                DetailsPage detailsPage=new DetailsPage();
               Integer baseOrgId=baseOrgEntityList.get(i).getId();
                //合作社信息
                detailsPage.setId(baseOrgEntityList.get(i).getId());
                detailsPage.setOrgAddress(baseOrgEntityList.get(i).getOrgAddress());
                detailsPage.setOrgCode(baseOrgEntityList.get(i).getOrgCode());
                detailsPage.setOrgName(baseOrgEntityList.get(i).getOrgName());
                for(int a=0;a<listWorker.size();a++){
                    Integer OrgId=listWorker.get(a).getOrgId();//拿到外键
                    if(OrgId ==  baseOrgId){
                        Integer WorkerId=listWorker.get(a).getId();
                        for(int c=0;c<ListGps.size();c++){
                            Integer gpsWorkerId=ListGps.get(c).getWorkerId();
                            if(gpsWorkerId == WorkerId){
                                Integer refMachTerminalId=ListGps.get(c).getRefMachTerminalId();
                                for(int d=0;d<listRef.size();d++){
                                   Integer RefMachTerminaId=listRef.get(d).getId();
                                    if(refMachTerminalId == RefMachTerminaId){
                                        //农机
                                        tempMachSize +=1;
                                        tempWorkTime+= Float.parseFloat(listRef.get(d).getWorkTime());
                                        tempWorkArea+= Float.parseFloat(listRef.get(d).getWorkArea());
                                        tempDrivingArea+= Float.parseFloat(listRef.get(d).getDrivingArea());
                                    }
                                }
                            }
                        }

                    }
                }
                //农机信息
                detailsPage.setMachSize(tempMachSize);
                detailsPage.setAllWorkTime(Float.toString(tempWorkTime));
                detailsPage.setAllDrivingArea(Float.toString(tempDrivingArea));
                detailsPage.setAllWorkArea(Float.toString(tempWorkArea));
                temp.add(detailsPage);
            }
        return temp;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<RefMachTerminalEntity> findOne(@PathVariable Integer id) {
        Validate.notNull(id, "The id must not be null, find failure.");
        BaseOrgEntity BaseOrgEntity=baseOrgService.findOne(id);
        List<WorkerEntity> listWorker=workerService.findAllByActive(true);//查询所有司机
        List<GpsRecordEntity> ListGps=gpsRecordService.getFirst();//分类查询
        List<RefMachTerminalEntity> listRef=refMachTerminalService.findAll();//拿到农机以及终端信息
        List<RefMachTerminalEntity> temp= new ArrayList<RefMachTerminalEntity>();
        Integer baseOrgId = BaseOrgEntity.getId();
        for(int i=0;i<listWorker.size();i++){
            Integer OrgId=listWorker.get(i).getOrgId();//拿到外键
            if(OrgId ==  baseOrgId){
                Integer WorkerId=listWorker.get(i).getId();
                for(int c=0;c<ListGps.size();c++){
                    Integer gpsWorkerId=ListGps.get(c).getWorkerId();
                    if(gpsWorkerId == WorkerId){
                        Integer refMachTerminalId=ListGps.get(c).getRefMachTerminalId();
                        for(int d=0;d<listRef.size();d++){
                            Integer RefMachTerminaId=listRef.get(d).getId();
                            if(refMachTerminalId == RefMachTerminaId){
                                RefMachTerminalEntity  refMachTerminalEntity=new RefMachTerminalEntity();
                                refMachTerminalEntity.setId(listRef.get(d).getId());
                                refMachTerminalEntity.setCallNo(listRef.get(d).getCallNo());
                                refMachTerminalEntity.setMachState(listRef.get(d).getMachState());
                                refMachTerminalEntity.setCallNo(listRef.get(d).getCallNo());
                                refMachTerminalEntity.setMachCode(listRef.get(d).getMachCode());
                                refMachTerminalEntity.setWorkArea(listRef.get(d).getWorkArea());
                                refMachTerminalEntity.setWorkTime(listRef.get(d).getWorkTime());
                                refMachTerminalEntity.setDrivingArea(listRef.get(d).getDrivingArea());
                                refMachTerminalEntity.setMachName(listRef.get(d).getMachName());
                                refMachTerminalEntity.setTerminalCode(listRef.get(d).getTerminalCode());
                                refMachTerminalEntity.setTerminalName(listRef.get(d).getTerminalName());
                                refMachTerminalEntity.setWorkingType(listRef.get(d).getWorkingType());
                                temp.add(refMachTerminalEntity);
                            }
                        }
                    }
                }

            }
        }
        return temp;
    }


}
