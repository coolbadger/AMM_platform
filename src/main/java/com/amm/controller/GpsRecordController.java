package com.amm.controller;

import com.amm.entity.GpsRecordEntity;
import com.amm.entity.RefMachTerminalEntity;
import com.amm.entity.client.GpsData;
import com.amm.entity.client.GpsRecordMachine;
import com.amm.gps.GpsConvert;
import com.amm.service.GpsRecordService;
import com.amm.service.RefMachTerminalService;
import com.amm.utils.DateUtil;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

/**
 * Created by csw on 2016/8/2 11:12.
 * Explain:
 */

@RestController
@RequestMapping("api/gpsRecords")
public class GpsRecordController extends BaseController{
    @Autowired
    GpsConvert gpsConvert;

    @Autowired
    private GpsRecordService gpsRecordService;

    @Autowired
    private RefMachTerminalService refMachTerminalService;

    /**
     * 拆分集合
     * @param <T>
     * @param resList  要拆分的集合
     * @param count	每个集合的元素个数
     * @return  返回拆分后的各个集合
     */
    public static  <T> List<List<T>> split(List<T> resList,int count){

        if(resList==null ||count<1)
            return  null ;
        List<List<T>> ret=new ArrayList<List<T>>();
        int size=resList.size();
        if(size<=count){ //数据量不足count指定的大小
            ret.add(resList);
        }else{
            int pre=size/count;
            int last=size%count;
            //前面pre个集合，每个大小都是count个元素
            for(int i=0;i<pre;i++){
                List<T> itemList=new ArrayList<T>();
                for(int j=0;j<count;j++){
                    itemList.add(resList.get(i*count+j));
                }
                ret.add(itemList);
            }
            //last的进行处理
            if(last>0){
                List<T> itemList=new ArrayList<T>();
                for(int i=0;i<last;i++){
                    itemList.add(resList.get(pre*count+i));
                }
                ret.add(itemList);
            }
        }
        return ret;

    }

    /*
    url: api/gpsRecords/gpsData    get
     */
    @RequestMapping(value = "/gpsDataAnalysis", method = RequestMethod.GET)
    public List<GpsData> getAllGpsData() {
       List<GpsRecordEntity> gpsRecordEntity=gpsRecordService.findAllGpsRecord();
        Map<Integer, List<GpsRecordEntity>> gpsMap = this.getGpsMap(gpsRecordEntity);
        List<GpsData> GpsDataList = new ArrayList<GpsData>();

        for(Integer refId : gpsMap.keySet()) {
            List<GpsRecordEntity> gpsList = gpsMap.get(refId);
            GpsRecordMachine gpsRecordMachine = new GpsRecordMachine();
            gpsRecordMachine.setReMachTerminalId(refId);
            RefMachTerminalEntity refMachTerminalEntity = refMachTerminalService.findOne(refId);
            Validate.notNull(refMachTerminalEntity, "The refMachTerminalEntity must not be null, find failure.");

            for(Integer i=0;i<gpsList.size();i++){
                    GpsData gpsData=new GpsData();
                    gpsData.setGpsTime(gpsList.get(i).getGpsTime());
                    gpsData.setLocalTime(gpsList.get(i).getLocalTime());
                    gpsData.setLatFixed(gpsList.get(i).getLatFixed());
                    gpsData.setLngFixed(gpsList.get(i).getLngFixed());
                    gpsData.setSensor1(gpsList.get(i).getSensor1());
                    gpsData.setReMachTerminalId(refId);
                    gpsData.setMachCode(refMachTerminalEntity.getMachCode());
                    gpsData.setMachName(refMachTerminalEntity.getMachName());
                    GpsDataList.add(gpsData);
                }
        }

        return GpsDataList;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<GpsRecordMachine> findAllByTimeScope(@RequestParam(required = true) String startTime,
                                   @RequestParam(required = true ) String endTime, @RequestParam(required = false) String machCode){

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
                                                         @RequestParam(required = true) String endTime) throws ParseException {

        Validate.notNull(id, "The id must not be null, find failure.");
        Validate.notNull(startTime, "The startTime must not be null, find failure.");
        Validate.notNull(endTime, "The endTime must not be null, find failure.");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTimeDate=sdf.parse(startTime);
        //Date startTimeDate = DateUtil.parseDate(startTime);
        System.out.println("startTimeDate="+startTimeDate);
        Date endTimeDate=sdf.parse(endTime);
        //Date endTimeDate = DateUtil.parseDate(endTime);
        List<GpsRecordEntity> listGpsRecordEntity= gpsRecordService.findByRefMachTerminalIDAndTimeScope(id, startTimeDate, endTimeDate);

        //listGpsRecordEntity.subList(0,1000);

        return listGpsRecordEntity;
    }
    @RequestMapping(value = "/convert",method = RequestMethod.GET)
    public String convertGpsRecord(){
        return "OK";
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
        /*Map<Integer, List<GpsRecordEntity>> temp = new HashMap<Integer, List<GpsRecordEntity>>();
        List<GpsRecordEntity> tempList=new ArrayList<GpsRecordEntity>();*/
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
