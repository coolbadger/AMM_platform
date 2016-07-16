package com.amm.test;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * Created by liuminhang on 16/7/16.
 */
public class TestMinaServerIOHandler extends IoHandlerAdapter {
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
        System.out.println("Message Sent:" + message.toString());
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
        System.out.println("Message Received:" + message.toString());

    }
}
