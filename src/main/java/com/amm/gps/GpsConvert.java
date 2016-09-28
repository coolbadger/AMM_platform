package com.amm.gps;

import com.amm.entity.GpsRecordEntity;
import com.amm.entity.client.GpsRecord;
import com.amm.service.GpsRecordService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Badger on 16/9/18.
 */
@Component
public class GpsConvert {
    @Autowired
    private GpsRecordService gpsRecordService;

    //服务器待转换坐标静态列表
    public Queue<GpsRecordEntity> gpsRecordEntityQueue = new LinkedList<GpsRecordEntity>();

    private WebRequest webRequest;
    private JsonArray resultJsonArray;

    private Date lastCheck = null;
    private int seconds = 5 * 60;
    String cordStr;
    Queue<GpsRecordEntity> fixingList;


    // TODO: 2016/9/28 启动时检查数据库中是否有未修正的坐标,然后收满99个未修正记录,或者待修正记录不为空间隔时间大于5分钟时.每组99个坐标,修正数据

    public void checkDatabase() {
        List<GpsRecordEntity> gpsRecordEntityList = gpsRecordService.findByLatFixedIsNullOrderbyGpsTimeAsc();
        for (GpsRecordEntity gpsRecordEntity : gpsRecordEntityList) {
            gpsRecordEntityQueue.offer(gpsRecordEntity);
        }
    }

    public void onAddGpsRecord(GpsRecordEntity gpsRecordEntity) {
        gpsRecordEntityQueue.offer(gpsRecordEntity);
    }

    private void fixGps() {
        //当未修正坐标满99个,待修正记录不为空且距离上次转换超过5分钟
        boolean checkNeed = gpsRecordEntityQueue.size() > 99;
        checkNeed = checkNeed || (gpsRecordEntityQueue.size() > 0 && new Date().getTime() - lastCheck.getTime() > seconds * 1000);

        if (checkNeed) {
            cordStr = "";
            fixingList = new LinkedList<GpsRecordEntity>();
            webRequest = new WebRequest();

            int maxCount = gpsRecordEntityQueue.size() > 99 ? 99 : gpsRecordEntityQueue.size();
            for (int i = 0; i < maxCount; i++) {
                cordStr += gpsRecordEntityQueue.peek().getLng() + "," + gpsRecordEntityQueue.peek().getLat() + ";";
                fixingList.add(gpsRecordEntityQueue.poll());
            }
        }
        //当修正队列不为空时,获取并保存gps修正数据
        if (fixingList.size() > 0) {
            resultJsonArray = webRequest.getResultList(cordStr);
            if (fixingList.size() == resultJsonArray.size()) {
                for (int i = 0; i < fixingList.size(); i++) {
                    JsonObject fixedObject = resultJsonArray.get(i).getAsJsonObject();
                    GpsRecordEntity fixingRecord = fixingList.poll();
                    fixingRecord.setLngFixed(fixedObject.get("x").getAsBigDecimal());
                    fixingRecord.setLatFixed(fixedObject.get("y").getAsBigDecimal());
                    gpsRecordService.updateGpsRecord(fixingRecord);
                }
            } else {
                for (GpsRecordEntity gpsRecordEntity : fixingList) {
                    cordStr += gpsRecordEntity.getLng() + "," + gpsRecordEntity.getLat() + ";";
                }
                fixGps();
            }
        }
    }
}
