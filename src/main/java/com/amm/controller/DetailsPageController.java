package com.amm.controller;

import com.amm.entity.*;
import com.amm.entity.client.DetailsPage;
import com.amm.service.*;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by 杨思名 on 2017/2/24.
 */
@RestController
@RequestMapping("api/DetailsPage")
public class DetailsPageController extends BaseController {
    @Autowired
    private BaseOrgService baseOrgService;
    @Autowired
    private WorkerService workerService;
    @Autowired
    private GpsRecordService gpsRecordService;
    @Autowired
    private RefMachTerminalService refMachTerminalService;
    @Autowired
    private MachineService machineService;

    @RequestMapping(method = RequestMethod.GET)
    public List<DetailsPage> getAllByActive(@RequestParam(required = false, defaultValue = "true") Boolean active) {

        logger.info(String.format("Receive find all baseOrg (by active is true)."));

        List<BaseOrgEntity> baseOrgEntityList = baseOrgService.findAllBaseOrgByActive(active);//查询出所有合作社


        //查询GPSRecords
        List<RefMachTerminalEntity> refMachTerminalEntityList = refMachTerminalService.findAll();//拿到农机以及终端信息
        List<MachineEntity> machineEntityList = machineService.findAllByActive(active);//查询出所有农机

        Map<Integer, MachineEntity> machineEntityMap = new HashMap<Integer, MachineEntity>();
        Map<Integer, RefMachTerminalEntity> refMachTerminalEntityMap = new HashMap<Integer, RefMachTerminalEntity>();
        Map<Integer, BaseOrgEntity> baseOrgEntityMap = new HashMap<Integer, BaseOrgEntity>();

        //初始化结果集
        Map<Integer, DetailsPage> detailsPageMap = new HashMap<Integer, DetailsPage>();
        //生成结果对象，填入数据；生成图以供查询

        for (BaseOrgEntity entity : baseOrgEntityList) {
            baseOrgEntityMap.put(entity.getId(), entity);
            DetailsPage detailsPage = new DetailsPage();
            detailsPage.setId(entity.getId());
            detailsPage.setOrgCode(entity.getOrgCode());
            detailsPage.setOrgName(entity.getOrgName());
            detailsPage.setOrgAddress(entity.getOrgAddress());
            detailsPage.setMachSize(0);
            detailsPageMap.put(detailsPage.getId(), detailsPage);

        }
        //计算农机数量
        for (MachineEntity entity : machineEntityList) {
            machineEntityMap.put(entity.getId(), entity);
            Integer orgId = entity.getOrgId();
            DetailsPage detailsPage = detailsPageMap.get(orgId);
            if (detailsPage != null) {
                detailsPage.setMachSize(detailsPage.getMachSize() + 1);
            }
        }


        BaseOrgEntity baseOrgEntity;
        DetailsPage detailsPage;
        for (RefMachTerminalEntity entity : refMachTerminalEntityList) {
            refMachTerminalEntityMap.put(entity.getId(), entity);
            //检查是否存在相关实体，如果存在，则记录对应数据
            if (entity.getMachId() != null && machineEntityMap.get(entity.getMachId()) != null && baseOrgEntityMap.get(machineEntityMap.get(entity.getMachId()).getOrgId()) != null) {
                baseOrgEntity = baseOrgEntityMap.get(machineEntityMap.get(entity.getMachId()).getOrgId());
                detailsPage = detailsPageMap.get(baseOrgEntity.getId());

                detailsPage.setAllDrivingArea(entity.getDrivingArea());
                detailsPage.setAllWorkArea(entity.getWorkArea());
                detailsPage.setAllWorkTime(entity.getWorkTime());

                detailsPageMap.put(detailsPage.getId(), detailsPage);
            }
        }

        //将结果遍历转换List类型输出
        List<DetailsPage> resultList = new ArrayList<DetailsPage>();

        Iterator iterator = detailsPageMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            resultList.add((DetailsPage) entry.getValue());

        }

        return resultList;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<RefMachTerminalEntity> findOne(@PathVariable Integer id) {
        Validate.notNull(id, "The id must not be null, find failure.");
        BaseOrgEntity BaseOrgEntity = baseOrgService.findOne(id);
        List<WorkerEntity> listWorker = workerService.findAllByActive(true);//查询所有司机
        List<GpsRecordEntity> ListGps = gpsRecordService.getFirst();//分类查询
        List<RefMachTerminalEntity> listRef = refMachTerminalService.findAll();//拿到农机以及终端信息
        List<RefMachTerminalEntity> temp = new ArrayList<RefMachTerminalEntity>();
        Integer baseOrgId = BaseOrgEntity.getId();
        for (int i = 0; i < listWorker.size(); i++) {
            Integer OrgId = listWorker.get(i).getOrgId();//拿到外键
            if (OrgId == baseOrgId) {
                Integer WorkerId = listWorker.get(i).getId();
                for (int c = 0; c < ListGps.size(); c++) {
                    Integer gpsWorkerId = ListGps.get(c).getWorkerId();
                    if (gpsWorkerId == WorkerId) {
                        Integer refMachTerminalId = ListGps.get(c).getRefMachTerminalId();
                        for (int d = 0; d < listRef.size(); d++) {
                            Integer RefMachTerminaId = listRef.get(d).getId();
                            if (refMachTerminalId == RefMachTerminaId) {
                                RefMachTerminalEntity refMachTerminalEntity = new RefMachTerminalEntity();
                                refMachTerminalEntity.setId(listRef.get(d).getId());
                                refMachTerminalEntity.setCallNo(listRef.get(d).getCallNo());
                                refMachTerminalEntity.setMachState(listRef.get(d).getMachState());
                                refMachTerminalEntity.setCallNo(listRef.get(d).getCallNo());
                                refMachTerminalEntity.setMachCode(listRef.get(d).getMachCode());
                                refMachTerminalEntity.setMachNotes(listRef.get(d).getMachNotes());
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
