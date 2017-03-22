package com.amm.service.impl;

import com.amm.entity.GpsRecordEntity;
import com.amm.gps.GpsResultDetail;
import com.amm.gps.WebRequest;
import com.amm.repository.GpsRecordRepository;
import com.amm.service.RecMesService;
import com.amm.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2017-03-17.
 */
@Component("RecMesService")
@Scope("prototype")
public class RecMesServiceImpl implements RecMesService{

    @Autowired
    GpsRecordRepository gpsRecordRepository;

    private final static String QUEUE_NAME = "send_queue";

    public void recMessage() throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);

        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列，手动返回完成
        channel.basicConsume(QUEUE_NAME, false, consumer);

        ByteArrayInputStream bi;
        ObjectInputStream oi;

        List<GpsRecordEntity> gpsRecordEntities = new ArrayList<GpsRecordEntity>();
        StringBuffer buffer = new StringBuffer();

        System.out.println("程序开始");

        // 获取消息
        int count =0;
        while (count<90) {

            System.out.println("==================");
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();

            bi = new ByteArrayInputStream(delivery.getBody());
            oi = new ObjectInputStream(bi);
            GpsRecordEntity gpsRecordEntity = (GpsRecordEntity) oi.readObject();
            gpsRecordEntities.add(gpsRecordEntity);//待请求数据
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            count++;
        }
        channel.close();
        connection.close();
        // 返回确认状态
        for (int i = 0;i<gpsRecordEntities.size();i++){
            buffer.append(gpsRecordEntities.get(i).getLng());
            buffer.append(",");
            buffer.append(gpsRecordEntities.get(i).getLat());
            buffer.append(";");
        }
        String newCoordStr = buffer.deleteCharAt(buffer.length()-1).toString();
        List<GpsResultDetail> gpsResultDetails = WebRequest.getGpsFixed(newCoordStr);
        //如果gpsResultDetails为空，应该把整批数据设为异常数据
        System.out.println("90组数据已转换");
        for (int i = 0;i<gpsResultDetails.size();i++){
            gpsRecordEntities.get(i).setLatFixed(gpsResultDetails.get(i).getLatFixed());
            gpsRecordEntities.get(i).setLngFixed(gpsResultDetails.get(i).getLngFixed());
            gpsRecordEntities.get(i).setFlag("1");
            gpsRecordRepository.saveAndFlush(gpsRecordEntities.get(i));
            System.out.println("数据更新成功"+gpsRecordEntities.get(i).getLatFixed()+","+gpsRecordEntities.get(i).getLngFixed());
        }

        gpsRecordEntities.clear();
        buffer = buffer.delete(0,buffer.length());
    }
}
