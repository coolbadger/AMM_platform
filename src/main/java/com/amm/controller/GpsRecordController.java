package com.amm.controller;

import com.amm.entity.*;
import com.amm.entity.client.GpsData;
import com.amm.entity.client.GpsRecordMachine;
import com.amm.entity.client.Maintainrecord;
import com.amm.gps.GpsConvert;
import com.amm.service.BaseOrgService;
import com.amm.service.GpsRecordService;
import com.amm.service.RefMachTerminalService;
import com.amm.service.WorkerService;
import com.amm.utils.DateUtil;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private BaseOrgService baseOrgService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private GpsRecordService gpsRecordService;

    @Autowired
    private RefMachTerminalService refMachTerminalService;



    @RequestMapping(value = "/getFinishingData",method = RequestMethod.GET)
    public List<GpsData> getFinishingData(){


            List<BaseOrgEntity> ListBaseOrgEntity=baseOrgService.findAllBaseOrg();
            List<WorkerEntity> ListWorkerEntity = workerService.findAllByActive(true);

            List<GpsRecordEntity> gpsRecordEntity=gpsRecordService.getFinishingData();
            Map<Integer, List<GpsRecordEntity>> gpsMap = this.getGpsMap(gpsRecordEntity);
            List<GpsData> GpsDataList = new ArrayList<GpsData>();


            for(Integer refId : gpsMap.keySet()) {
                List<GpsRecordEntity> gpsList = gpsMap.get(refId);
                GpsRecordMachine gpsRecordMachine = new GpsRecordMachine();
                gpsRecordMachine.setReMachTerminalId(refId);
                RefMachTerminalEntity refMachTerminalEntity = refMachTerminalService.findOne(refId);
                Validate.notNull(refMachTerminalEntity, "The refMachTerminalEntity must not be null, find failure.");

                for(Integer c=0;c<gpsList.size();c++){
                    GpsData gpsData=new GpsData();
                    gpsData.setGpsTime(gpsList.get(c).getGpsTime());
                    gpsData.setLocalTime(gpsList.get(c).getLocalTime());
                    gpsData.setLatFixed(gpsList.get(c).getLatFixed());
                    gpsData.setLngFixed(gpsList.get(c).getLngFixed());
                    gpsData.setSensor1(gpsList.get(c).getSensor1());
                    gpsData.setReMachTerminalId(refId);
                    gpsData.setMachCode(refMachTerminalEntity.getMachCode());
                    gpsData.setMachName(refMachTerminalEntity.getMachName());
                    gpsData.setState(gpsList.get(c).getState());
                    GpsDataList.add(gpsData);

                }
            }

            return GpsDataList;
  }


    /*修改*/
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public RefMachTerminalEntity update(@PathVariable Integer id,@RequestBody RefMachTerminalEntity refMachTerminalEntity) throws Exception{
        Validate.notNull(id, "The id of orgUser must not be null, update failure.");
        Validate.notNull(refMachTerminalEntity, "The orgUser object must not be null, update failure.");
        RefMachTerminalEntity refMachTerminal=refMachTerminalService.findById(id);
        gpsRecordService.updateState("1");
        refMachTerminalEntity.setMachState(refMachTerminal.getMachState());
        refMachTerminalEntity.setId(id);
        refMachTerminalEntity.setCallNo(refMachTerminal.getCallNo());
        refMachTerminalEntity.setMachCode(refMachTerminal.getMachCode());
        refMachTerminalEntity.setMachId(refMachTerminal.getMachId());
        refMachTerminalEntity.setMachName(refMachTerminal.getMachName());
        refMachTerminalEntity.setTerminalName(refMachTerminal.getTerminalName());
        refMachTerminalEntity.setTerminalCode(refMachTerminal.getTerminalCode());
        refMachTerminalEntity.setTerminalState(refMachTerminal.getTerminalState());
        refMachTerminalEntity.setWorkingType(refMachTerminal.getWorkingType());
            if(refMachTerminal.getDrivingArea()!=null&&refMachTerminal.getWorkArea()!=null&&refMachTerminal.getWorkTime()!=null&&!refMachTerminal.getDrivingArea().equals("")&&!refMachTerminal.getWorkArea().equals("")&&!refMachTerminal.getWorkTime().equals("")){
                refMachTerminalEntity.setDrivingArea(Double.toString(Double.parseDouble(refMachTerminal.getDrivingArea())+Double.parseDouble(refMachTerminalEntity.getDrivingArea())));
                refMachTerminalEntity.setWorkArea(Double.toString(Double.parseDouble(refMachTerminal.getWorkArea())+Double.parseDouble(refMachTerminalEntity.getWorkArea())));
                refMachTerminalEntity.setWorkTime(Double.toString(Double.parseDouble(refMachTerminal.getWorkTime())+Double.parseDouble(refMachTerminalEntity.getWorkTime())));

            }

        RefMachTerminalEntity updated = refMachTerminalService.updateRefMachTerminal(refMachTerminalEntity);

        return updated;
    }







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

        List<BaseOrgEntity> ListBaseOrgEntity=baseOrgService.findAllBaseOrg();
        List<WorkerEntity> ListWorkerEntity = workerService.findAllByActive(true);

        List<GpsRecordEntity> gpsRecordEntity=gpsRecordService.findAllGpsRecord();
        Map<Integer, List<GpsRecordEntity>> gpsMap = this.getGpsMap(gpsRecordEntity);
        List<GpsData> GpsDataList = new ArrayList<GpsData>();


        for(Integer refId : gpsMap.keySet()) {
            List<GpsRecordEntity> gpsList = gpsMap.get(refId);
            GpsRecordMachine gpsRecordMachine = new GpsRecordMachine();
            gpsRecordMachine.setReMachTerminalId(refId);
            RefMachTerminalEntity refMachTerminalEntity = refMachTerminalService.findOne(refId);
            Validate.notNull(refMachTerminalEntity, "The refMachTerminalEntity must not be null, find failure.");

            for(Integer c=0;c<gpsList.size();c++){
                GpsData gpsData=new GpsData();
                gpsData.setGpsTime(gpsList.get(c).getGpsTime());
                gpsData.setLocalTime(gpsList.get(c).getLocalTime());
                gpsData.setLatFixed(gpsList.get(c).getLatFixed());
                gpsData.setLngFixed(gpsList.get(c).getLngFixed());
                gpsData.setSensor1(gpsList.get(c).getSensor1());
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
