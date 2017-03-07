package com.amm.utils;

import com.amm.entity.client.GpsRecordSave;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private static long longtime;
    private static ExecutorService es= Executors.newCachedThreadPool();
    private static long starTime=System.currentTimeMillis();
    //获取缓存线程池
    public static ExecutorService getCachedThreadPool(){
        return es;
    }
    //添加线程到线程池
    public static void getResultSubmit(Runnable r){
        System.out.println("线程开始时间:"+getTime(System.currentTimeMillis()));

        //获取线程执行结果
        Future future=es.submit(r);
        try {
            if(future.get()==null){
                System.out.println("执行完成");
                //线程池不再接收任何新任务，但此时线程池并不会立刻退出，直到添加到线程池中的任务都已经处理完成
                es.shutdown();
            }
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
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy/MM/dd/-HH:mm:ss");
        Date data=new Date(currentTime);
        return formatter.format(data);
    }

    /**
     * 将long值转换具体时分秒
     * @param diff
     * @return
     */
    public static String getTimeFromLong(long diff) {
        final String HOURS = "h";
        final String MINUTES = "min";
        final String SECONDS = "sec";

        final long MS_IN_A_DAY = 1000 * 60 * 60 * 24;
        final long MS_IN_AN_HOUR = 1000 * 60 * 60;
        final long MS_IN_A_MINUTE = 1000 * 60;
        final long MS_IN_A_SECOND = 1000;
        //Date currentTime = new Date();
        //long numDays = diff / MS_IN_A_DAY;
        //diff = diff % MS_IN_A_DAY;
        long numHours = diff / MS_IN_AN_HOUR;
        diff = diff % MS_IN_AN_HOUR;
        long numMinutes = diff / MS_IN_A_MINUTE;
        diff = diff % MS_IN_A_MINUTE;
        long numSeconds = diff / MS_IN_A_SECOND;
        diff = diff % MS_IN_A_SECOND;
        long numMilliseconds = diff;

        StringBuffer buf = new StringBuffer();
        if (numHours > 0) {
            buf.append(numHours + " " + HOURS + ", ");
        }

        if (numMinutes > 0) {
            buf.append(numMinutes + " " + MINUTES);
        }

        if(numSeconds>0){
            buf.append(numSeconds + " " + SECONDS);
        }

        String result = buf.toString();

        if (0<numMilliseconds&&numMilliseconds < 1) {
            result = "< 1 s";
        } else{
            result = " 0 s";
        }

        return result;
    }
}
