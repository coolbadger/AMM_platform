package com.amm.queue;

import com.amm.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * Created by sw on 2017/3/10 0010.
 *  发送定位数据
 */
public class SendMes {
    private final static String QUEUE_NAME = "send_queue";

    public static void sendMessageQueue() throws IOException, InterruptedException {
        // 获取到连接以及mq通道
        Connection connection = null;
        try {
            connection = ConnectionUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        for (int i = 0; i < 10; i++) {
            // 消息内容
            String message = "" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

            Thread.sleep(i * 10);
        }

        channel.close();
        connection.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        sendMessageQueue();
    }
}
