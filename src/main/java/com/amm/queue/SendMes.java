package com.amm.queue;

import com.amm.entity.GpsRecordEntity;
import com.amm.entity.client.GpsRecordSave;
import com.amm.utils.ConnectionUtil;
import com.amm.utils.MyThread;
import com.amm.variables.GpsRecordVariables;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;

/**
 * Created by sw on 2017/3/10 0010.
 *  发送定位数据
 */
public class SendMes {

    private final static String QUEUE_NAME = "send_queue";

    public static void sendMessageQueue(GpsRecordEntity gpsRecordEntity) throws IOException, InterruptedException {
        // 获取到连接以及mq通道
        Connection connection = null;
        try {
            connection = ConnectionUtil.getConnection();
            System.out.println("队列链接成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        System.out.println("流开始");
        byte[] bytes=null;
        ByteArrayOutputStream bo=new ByteArrayOutputStream();
        ObjectOutputStream oo=new ObjectOutputStream(bo);
        oo.writeObject(gpsRecordEntity);
        bytes = bo.toByteArray();
        System.out.println("流结束");
        bo.close();
        oo.close();

//        for (int i = 0; i < 100; i++) {
            // 消息内容
//            String message = "" + i;
        channel.basicPublish("", QUEUE_NAME, null, bytes);

//            System.out.println(" [x] Sent '" + message + "'");

//            Thread.sleep(0);
//        }

        channel.close();
        connection.close();
    }

    //测试发送信息
    public static void main(String[] args) throws IOException, InterruptedException {

        GpsRecordEntity gpsRecordEntity = new GpsRecordEntity();

        for (int i = 0;i<720;i++){
            gpsRecordEntity.setLng(new BigDecimal(117.592233).setScale(6,BigDecimal.ROUND_DOWN));

            gpsRecordEntity.setLat(new BigDecimal(34.036718).setScale(6,BigDecimal.ROUND_DOWN));
            sendMessageQueue(gpsRecordEntity);
            if (GpsRecordVariables.getCounts()>=90){
               // new MyThread();//每存90条，取一次信息
               // GpsRecordVariables.setCounts(0);
            }
            GpsRecordVariables.setCounts(GpsRecordVariables.getCounts()+1);
        }
        System.out.println("over_____________");


    }

    //    public static void sendMessageQueue() throws IOException, InterruptedException {
//        // 获取到连接以及mq通道
//        Connection connection = null;
//        try {
//            connection = ConnectionUtil.getConnection();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Channel channel = connection.createChannel();
//
//        // 声明队列
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//
//        for (int i = 0; i < 2; i++) {
//            // 消息内容
//            String message = "" + i;
//            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
//            System.out.println(" [x] Sent '" + message + "'");
//
//            Thread.sleep(1000);
//        }
//
//        channel.close();
//        connection.close();
//    }
}
