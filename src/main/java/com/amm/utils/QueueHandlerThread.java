package com.amm.utils;

import com.amm.service.RecMesService;
import com.amm.service.impl.RecMesServiceImpl;
import com.amm.variables.GpsRecordVariables;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/5/23 0023.
 */
public class QueueHandlerThread implements  Runnable{


    RecMesService recMesService;
    private static int threadCount;

    public QueueHandlerThread(RecMesService recMesService){
        this.recMesService = recMesService;
    }

    public void run() {
        while(true){
            try {
                System.out.println("==========================="+threadCount);
                Thread.sleep(1000);
                threadCount++;
                //队列中达到90条数据或循环5次时调用一次百度API
                if (GpsRecordVariables.getCounts()>=90||threadCount==10){//90
                    System.out.println("=========================== new MyThread( recMesService)，GpsRecordVariables.getCounts():" +GpsRecordVariables.getCounts());
                    new MyThread( recMesService);//取出队列
                    GpsRecordVariables.setCounts(0);//队列中数据置为0
                    System.out.println("=========================== 取出队列成功");

                    threadCount=0;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String args[]){
        System.out.println("============================================");
        QueueHandlerThread test = new QueueHandlerThread(new RecMesServiceImpl());
        Thread thread = new Thread(test);
        thread.start();
    }
}

