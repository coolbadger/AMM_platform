package com.amm.queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author  sw  2017-3-13 11:08:00
 * 队列入口
 * 加载配置文件
 */
public class SpringQueueMain {
    public static void sendMessage(String s) {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
                "classpath:spring/rabbitmq-context.xml");
        //RabbitMQ模板
        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
        //发送消息
        template.convertAndSend(s);
        // 休眠1秒
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ctx.destroy(); //容器销毁
    }

    public static void main(final String... args) throws Exception {
        sendMessage("...这是加入队列的消息?");

    }

}
