package com.amm.utils;

import com.amm.entity.client.GpsRecordSave;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.SimpleFormatter;

/**
 * Created by sw on 2017/3/6 0006.
 * 新开线程
 * 1.发送定位请求
 * 2.接受定位请求
 */

public class ThreadPool {
    private static ThreadPool instance;

    private static ExecutorService es= Executors.newCachedThreadPool();
    private static long starTime=System.currentTimeMillis();
    private static List<Future> futures=new ArrayList<Future>();//线程执行结果集

    private ThreadPool(){
    }

    public static synchronized ThreadPool getInstance(){
        if(instance==null){
            new ThreadPool();
        }
        return instance;
    }

    //获取缓存线程池
    public static ExecutorService getCachedThreadPool(){
        return es;
    }
    //添加单个线程到线程池
    public static void getResultSubmit(Thread myThread){
        //将线程放入池，获取线程执行结果
        Future future=es.submit(myThread);
        futures.add(future);
        try {
         //   myThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(futures.size()>1){
            getThreadPoolMes();
        }
    }

    public static void getThreadPoolMes(){
        System.out.println("线程开始时间:"+getTime(starTime));
        try {
            for (int i=0;i<futures.size();i++){
                if(futures.get(i).get()==null){
                    System.out.println("线程"+(i+1)+"执行完成");
                }
            }
            //线程池不再接收任何新任务，但此时线程池并不会立刻退出，直到添加到线程池中的任务都已经处理完成
            es.shutdown();
            System.out.println("所剩线程:"+Thread.currentThread().getName());

        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }

        long endTime;
        while(true){
            if(es.isTerminated()){
                endTime=System.currentTimeMillis();
                System.out.println("池中所有线程结束.结束时间:"+getTime(endTime));
                break;
            }
        }
        System.out.println("线程启用时间:"+getTimeFromLong(endTime-starTime));
    }

    /**
     * 格式化时间|yyyy/MM/dd/-HH:mm:ss
     * @return
     */
    public static String getTime(long currentTime){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy/MM/dd/-HH:mm:ss:SSS");
        Date data=new Date(currentTime);
        return formatter.format(data);
    }

    /**
     * 将时间long值转换具体时分秒精确毫秒
     * @param diff
     * @return
     */
    public static String getTimeFromLong(long diff) {
        final String HOURS = "h";
        final String MINUTES = "min";
        final String SECONDS = "sec";
        final String Hm = "ms";

        final long MS_IN_A_DAY = 1000 * 60 * 60 * 24;
        final long MS_IN_AN_HOUR = 1000 * 60 * 60;
        final long MS_IN_A_MINUTE = 1000 * 60;
        final long MS_IN_A_SECOND = 1000;
        final long MS_IN_A_HmSECOND = 10000;//毫秒
        //Date currentTime = new Date();
        //long numDays = diff / MS_IN_A_DAY;
        //diff = diff % MS_IN_A_DAY;
        long numHours = diff / MS_IN_AN_HOUR;
        diff = diff % MS_IN_AN_HOUR;
        long numMinutes = diff / MS_IN_A_MINUTE;
        diff = diff % MS_IN_A_MINUTE;
        long numSeconds = diff / MS_IN_A_SECOND;
        diff = diff % MS_IN_A_SECOND;
        long numHm=diff/MS_IN_A_HmSECOND;
        diff = diff % MS_IN_A_HmSECOND;

        long numMilliseconds = diff;

        StringBuffer buf = new StringBuffer();
        if (numHours > 0) {
            buf.append(numHours + " " + HOURS + ", ");
        }

        if (numMinutes > 0) {
            buf.append(numMinutes + " " + MINUTES+",");
        }

        if(numSeconds>0){
            buf.append(numSeconds + " " + SECONDS+",");
        }

        if(numHm>0){
            buf.append(numHm + " " + Hm);
        }

        String result = buf.toString();

        if (0<numMilliseconds) {
            result = numMilliseconds+" "+Hm;
        } else{
            result = " < 0 ms";
        }

        return result;
    }
}
