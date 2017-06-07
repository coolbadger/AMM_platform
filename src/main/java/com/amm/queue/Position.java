package com.amm.queue;

/**
 * 队列监听类
 * @author sw
 *
 */
public class Position {
    //具体执行业务的方法
    public void listen(String gps) {
        System.out.println("message:" + gps);
    }
}