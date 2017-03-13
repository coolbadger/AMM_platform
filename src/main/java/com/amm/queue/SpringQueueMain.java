package com.amm.queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author  sw  2017-3-13 11:08:00
 * �������
 * ���������ļ�
 */
public class SpringQueueMain {
    public static void sendMessage(String s) {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
                "classpath:spring/rabbitmq-context.xml");
        //RabbitMQģ��
        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
        //������Ϣ
        template.convertAndSend(s);
        // ����1��
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ctx.destroy(); //��������
    }

    public static void main(final String... args) throws Exception {
        sendMessage("...���Ǽ�����е���Ϣ?");

    }

}
