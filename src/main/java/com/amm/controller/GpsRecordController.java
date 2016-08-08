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
    public List<GpsRecordMachine> findAllByTimeScope(@RequestParam String startTime,
                                   @RequestParam String endTime, @RequestParam(required = false) String machCode){

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

            gpsRecordMachine.setMachCode(refMachTerminalEntity.getMachCode());
            gpsRecordMachine.setMachName(refMachTerminalEntity.getMachName());

            gpsRecordMachineList.add(gpsRecordMachine);
        }

        //按machCode查找
        List<GpsRecordMachine> gpsRecordMachineListNew = new ArrayList<GpsRecordMachine>();
        if(machCode != null && !"".equals(machCode)) {
            for(GpsRecordMachine gpsRecordMachine : gpsRecordMachineList) {
                if(machCode.equals(gpsRecordMachine.getMachCode())) {
                    gpsRecordMachineListNew.add(gpsRecordMachine);
                }
            }
        } else {
            gpsRecordMachineListNew = gpsRecordMachineList;
        }

        return gpsRecordMachineListNew;
    }


    @RequestMapping(value = "/refMachTerminal/{id}", method = RequestMethod.GET)
    public List<GpsRecordEntity> findByRefMachTerminalID(@PathVariable Integer id) {

        Validate.notNull(id, "The id must not be null, find failure.");

        return gpsRecordService.findByRefMachTerminalID(id);
    }

    //按时间排序
    private List<GpsRecordEntity> sortByTime(List<GpsRecordEntity> valueList) {

        Collections.sort(valueList, new Comparator<GpsRecordEntity>() {
            @Override
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
            }
        }

        //对Map中的list按gps时间排序
        for(List<GpsRecordEntity> recordEntityList : resultMap.values()) {
            this.sortByTime(recordEntityList);
        }

        return resultMap;
    }


}
