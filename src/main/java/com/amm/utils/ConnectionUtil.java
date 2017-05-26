package com.amm.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by sw on 2017/3/10 0010.
 */
public class ConnectionUtil {
    //定义连接工厂
    private static ConnectionFactory factory=new ConnectionFactory();
    // 通过工程获取连接
    private ConnectionUtil(){
    }

     public static Connection getConnection() throws Exception{
        //设置服务地址(服务转到16上 但mq位置不变)
        factory.setHost("10.21.1.11");
        //端口
        factory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("/AMM_platform");
        factory.setUsername("sw");
        factory.setPassword("123456");
        Connection connection=factory.newConnection();
        return connection;
    }
}
