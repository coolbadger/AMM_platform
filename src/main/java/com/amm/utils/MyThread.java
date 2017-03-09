package com.amm.utils;

import com.amm.entity.client.GpsRecordSave;

/**
 * Created by sw on 2017/3/8 0008.
 */
public class MyThread implements Runnable {
    public  Object o;
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
        GpsRecordSave gpsRecordSave = new GpsRecordSave();
        gpsRecordSave.setSensor1("设置值");
        MyThread myThread=new MyThread(gpsRecordSave);
    }
}
