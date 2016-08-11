package com.amm.controller;

import com.amm.entity.GpsRecordEntity;
import com.amm.entity.RefMachTerminalEntity;
import com.amm.entity.client.GpsRecord;
import com.amm.entity.client.GpsRecordMachine;
import com.amm.service.GpsRecordService;
import com.amm.service.RefMachTerminalService;
import com.amm.utils.DateUtil;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by csw on 2016/8/2 11:12.
 * Explain:
 */

@RestController
@RequestMapping("api/gpsRecords")
public class GpsRecordController extends BaseController{

    @Autowired
    private GpsRecordService gpsRecordService;

    @Autowired
    private RefMachTerminalService refMachTerminalService;

    @RequestMapping(method = RequestMethod.GET)
    public List<GpsRecordMachine> findAllByTimeScope(@RequestParam(required = true) String startTime,
                                   @RequestParam(required = true) String endTime, @RequestParam(required = false) String machCode){

        Validate.notNull(startTime, "The startTime must not be null, find failure.");
        Validate.notNull(endTime, "The endTime must not be null, find failure.");

        Date startTimeDate = DateUtil.parseDate(startTime);
        Date endTimeDate = DateUtil.parseDate(endTime);
        List<GpsRecordEntity> gpsRecordEntityList = gpsRecordService.findGpsRecordByTimeScope(startTimeDate, endTimeDate);

        Map<Integer, List<GpsRecordEntity>> gpsMap = this.getGpsMap(gpsRecordEntityList);

        List<GpsRecordMachine> gpsRecordMachineList = new ArrayList<GpsRecordMachine>();

        for(Integer refId : gpsMap.keySet()) {
            List<GpsRecordEntity> gpsList = gpsMap.get(refId);
            Date gpsSTime = gpsList.get(0).getGpsTime();
            Date gpsETime = gpsList.get(gpsList.size() - 1).getGpsTime();

            GpsRecordMachine gpsRecordMachine = new GpsRecordMachine();
            gpsRecordMachine.setReMachTerminalId(refId);
            gpsRecordMachine.setWorkerId(gpsList.get(0).getWorkerId());
            gpsRecordMachine.setGpsStartTime(gpsSTime);
            gpsRecordMachine.setGpsEndTime(gpsETime);

            RefMachTerminalEntity refMachTerminalEntity = refMachTerminalService.findOne(refId);
            Validate.notNull(refMachTerminalEntity, "The refMachTerminalEntity must not be null, find failure.");

            gpsRecordMachine.setMachCode(refMachTerminalEntity.getMachCode());
            gpsRecordMachine.setMachName(refMachTerminalEntity.getMachName());

            //按machCode查找
            if(machCode != null && !"".equals(machCode)) {
                if(gpsRecordMachine.getMachCode().indexOf(machCode) >= 0 || gpsRecordMachine.getMachName().indexOf(machCode) >= 0) {
                    gpsRecordMachineList.add(gpsRecordMachine);
                }
            } else {
                gpsRecordMachineList.add(gpsRecordMachine);
            }

        }

        return gpsRecordMachineList;
    }


    @RequestMapping(value = "/refMachTerminal/{id}", method = RequestMethod.GET)
    public List<GpsRecordEntity> findByRefMachTerminalID(@PathVariable Integer id,
                                                         @RequestParam(required = true) String startTime,
                                                         @RequestParam(required = true) String endTime) {

        Validate.notNull(id, "The id must not be null, find failure.");
        Validate.notNull(startTime, "The startTime must not be null, find failure.");
        Validate.notNull(endTime, "The endTime must not be null, find failure.");

        Date startTimeDate = DateUtil.parseDate(startTime);
        Date endTimeDate = DateUtil.parseDate(endTime);

        return gpsRecordService.findByRefMachTerminalIDAndTimeScope(id, startTimeDate, endTimeDate);
    }

    //按时间排序
    private List<GpsRecordEntity> sortByTime(List<GpsRecordEntity> valueList) {

        Collections.sort(valueList, new Comparator<GpsRecordEntity>() {

            public int compare(GpsRecordEntity o1, GpsRecordEntity o2) {
                return o1.getGpsTime().compareTo(o2.getGpsTime());
            }
        });

        return valueList;
    }

    //按refMachTerminalId将gps记录分组
    private Map<Integer, List<GpsRecordEntity>> getGpsMap(List<GpsRecordEntity> gpsRecordEntityList) {
        Map<Integer, List<GpsRecordEntity>> resultMap = new HashMap<Integer, List<GpsRecordEntity>>();

        for(GpsRecordEntity gpsRecordEntity : gpsRecordEntityList) {
            if(resultMap.get(gpsRecordEntity.getRefMachTerminalId()) != null) {
                resultMap.get(gpsRecordEntity.getRefMachTerminalId()).add(gpsRecordEntity);
            } else {
                resultMap.put(gpsRecordEntity.getRefMachTerminalId(), new ArrayList<GpsRecordEntity>());
                resultMap.get(gpsRecordEntity.getRefMachTerminalId()).add(gpsRecordEntity);
            }
        }

        //对Map中的list按gps时间排序
        for(List<GpsRecordEntity> recordEntityList : resultMap.values()) {
            this.sortByTime(recordEntityList);
        }

        return resultMap;
    }


}
