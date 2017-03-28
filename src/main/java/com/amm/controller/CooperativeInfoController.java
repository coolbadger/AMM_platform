package com.amm.controller;

import com.amm.entity.BaseOrgEntity;
import com.amm.entity.GpsRecordEntity;
import com.amm.entity.MachineEntity;
import com.amm.entity.RefMachTerminalEntity;
import com.amm.entity.client.CooperativeInfo;
import com.amm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 杨思名 on 2017/2/8.
 */
@RestController
@RequestMapping("api/CooperativeInfo")
public class CooperativeInfoController extends BaseController {
    @Autowired
    private MachTerminalService machTerminalService;
    @Autowired
    private MachineService machineService;
    @Autowired
    private BaseOrgService baseOrgService;
    @Autowired
    private RefMachTerminalService refMachTerminalService;
    @Autowired
    private GpsRecordService gpsRecordService;
    @RequestMapping(method = RequestMethod.GET)
    public  List<CooperativeInfo> getAllByActive(@RequestParam(required = false, defaultValue = "true") Boolean active) {
        List<MachineEntity> machineList = machineService.findAllByActive(active);//查询所有车辆信息
        List<BaseOrgEntity> baseOrgList = baseOrgService.findAllBaseOrgByActive(active);//查询所有合作社信息
        List<RefMachTerminalEntity> listRef=refMachTerminalService.findAll();//拿到农机以及终端信息
        List<GpsRecordEntity> ListGps=gpsRecordService.getFirst();

        Map<Integer, List<GpsRecordEntity>> gpsMap = this.getGpsMap(ListGps);//拿到GPS所有信息

        List<CooperativeInfo> ListCooperativeInfo = new ArrayList<CooperativeInfo>();

        for(int i=0;i<listRef.size();i++){
            CooperativeInfo cooperativeInfo=new CooperativeInfo();
            Integer refId=listRef.get(i).getId();
            try {
                List<GpsRecordEntity> times = gpsMap.get(refId);
                if (times.size() > 0) {
                    Date localTime = times.get(times.size()-1).getLocalTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    cooperativeInfo.setSensor1(times.get(0).getSensor1());
                    cooperativeInfo.setLocalTime(sdf.format(localTime));
                    //cooperativeInfo.setCurrentPosition("www");
                    cooperativeInfo.setFirstLatFixed(times.get(0).getLatFixed());
                    cooperativeInfo.setFirstLngFixed(times.get(0).getLngFixed());

                }
            }catch (NullPointerException e){
                System.out.print("空指针错误");
            }
            Integer MachId=listRef.get(i).getMachId();
            cooperativeInfo.setId(listRef.get(i).getId());
            cooperativeInfo.setMachCode(listRef.get(i).getMachCode());
            cooperativeInfo.setMachName(listRef.get(i).getMachName());
            cooperativeInfo.setTerminalCode(listRef.get(i).getTerminalCode());
            cooperativeInfo.setTerminalName(listRef.get(i).getTerminalName());
            cooperativeInfo.setWorkingType(listRef.get(i).getWorkingType());

            for(int a=0;a<machineList.size();a++){
                Integer machineId=machineList.get(a).getId();
                if(machineId==MachId){
                   Integer OrgId=machineList.get(a).getOrgId();
                    for(int b=0;b<baseOrgList.size();b++){
                        Integer Id=baseOrgList.get(b).getId();
                        if(OrgId==Id){
                            cooperativeInfo.setOrgName(baseOrgList.get(b).getOrgName());//拿到合作社名称
                            cooperativeInfo.setOrgAddress(baseOrgList.get(b).getOrgAddress());//拿到地理位置

                        }

                    }
                }
            }
            ListCooperativeInfo.add(cooperativeInfo);
        }


        return ListCooperativeInfo;
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

    /**
     * Map对象转化成 JavaBean对象
     *
     * @param javaBean JavaBean实例对象
     * @param map Map对象
     * @return
     * @author jqlin
     */
    @SuppressWarnings({ "rawtypes","unchecked", "hiding" })
    public static <T> T map2Java(T javaBean, Map map) {
        try {
            // 获取javaBean属性
            BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());
            // 创建 JavaBean 对象
            Object obj = javaBean.getClass().newInstance();

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if (propertyDescriptors != null && propertyDescriptors.length > 0) {
                String propertyName = null; // javaBean属性名
                Object propertyValue = null; // javaBean属性值
                for (PropertyDescriptor pd : propertyDescriptors) {
                    propertyName = pd.getName();
                    if (map.containsKey(propertyName)) {
                        propertyValue = map.get(propertyName);
                        pd.getWriteMethod().invoke(obj, new Object[] { propertyValue });
                    }
                }
                return (T) obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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
