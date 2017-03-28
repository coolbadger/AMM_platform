package com.amm.service.impl;

import com.amm.entity.GpsRecordEntity;
import com.amm.gps.GpsResultDetail;
import com.amm.gps.WebRequest;
import com.amm.repository.GpsRecordRepository;
import com.amm.service.CoordinateConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ThinkPad on 2017-03-09.
 */
@Component("CoordinateConvertService")
@Scope("prototype")
public class CoordinateConvertServiceImpl implements CoordinateConvertService {

    @Autowired
    GpsRecordRepository gpsRecordRepository;

    @PostConstruct
    public void convert() {

        List<GpsRecordEntity> list = gpsRecordRepository.getUnConvert();

        GpsRecordEntity gpsRecordEntity;
        StringBuffer buffer = new StringBuffer();
        int j = 0;

        for (int i = 0; i<list.size();i++){
            gpsRecordEntity = list.get(i);
            if (gpsRecordEntity.getLng()==null||gpsRecordEntity.getLat()==null
                    ||"".equals(gpsRecordEntity.getLng())||"".equals(gpsRecordEntity.getLat())
                    ||"0".equals(gpsRecordEntity.getLng())||"0".equals(gpsRecordEntity.getLat())){
                gpsRecordEntity.setFlag("2");//Gps数据不合法
                gpsRecordRepository.saveAndFlush(gpsRecordEntity);
                list.remove(i);//将不合法数据踢出list
            }
        }

       while (list.size()>0) {

           //迭代器清空list
           Iterator<GpsRecordEntity> it = list.iterator();
           while (it.hasNext()){
               String flag = it.next().getFlag();
               if ("1".equals(flag)||"2".equals(flag)){
                   it.remove();
               }
           }

        while (j<90){

            try{
                buffer.append(list.get(j).getLng());
                buffer.append(",");
                buffer.append(list.get(j).getLat());
                buffer.append(";");
                j++;
            }catch (Exception e){
                e.printStackTrace();
                break;
            }

        }
        j=0;

        String newCoordStr = buffer.deleteCharAt(buffer.length()-1).toString();//百度api允许请求格式为{"xxxx,xxxx;xxxx,xxxx"}
        List<GpsResultDetail> gpsResultDetails = WebRequest.getGpsFixed(newCoordStr);//请求百度api批量转换坐标

        //逐条更新结果
        for (int i = 0;i<gpsResultDetails.size();i++){
            list.get(i).setLngFixed(gpsResultDetails.get(i).getLngFixed());
            list.get(i).setLatFixed(gpsResultDetails.get(i).getLatFixed());
            list.get(i).setFlag("1");
            gpsRecordRepository.saveAndFlush(list.get(i));//转换完成 更新数据
        }


        buffer = buffer.delete(0,buffer.length());
    }

    }

}
