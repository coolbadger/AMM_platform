package com.amm.utils;

import com.amm.entity.client.GpsRecordSave;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sw on 2017/3/8 0008.
 * 线程池接口
 */
public class MyThread implements Runnable {
    public  Object o;
    //初始化单个线程
    public MyThread(Object object){
        o=object;
        //初始化时默认加载到池
        ThreadPool.getResultSubmit(this);
    }
    public void run() {
            synchronized (o) {
                System.out.println("线程名:"+Thread.currentThread().getName());
            }
    }

    public static void main(String[] args) {
        List<Object> list=new ArrayList<Object>();

        GpsRecordSave gpsRecordSave = new GpsRecordSave();
        gpsRecordSave.setSensor1("设置值1");
        list.add(gpsRecordSave);

        GpsRecordSave gpsRecordSave1 = new GpsRecordSave();
        gpsRecordSave1.setSensor1("设置值2");
        list.add(gpsRecordSave1);

        for (int i=0;i<list.size();i++){
            new MyThread(list.get(i));
        }
//       MyThread myThread=new MyThread(gpsRecordSave);

    }

}