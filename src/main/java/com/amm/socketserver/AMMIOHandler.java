package com.amm.socketserver;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liuminhang on 16/7/16.
 */
public class AMMIOHandler extends IoHandlerAdapter {
    private Logger logger = LoggerFactory.getLogger(AMMIOHandler.class);

    public AMMIOHandler() {
        super();
        logger.debug("AMMSocket: IOHandler Initialized");
        System.out.println("AMMIOHandler 初始化!");
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
        logger.debug("AMMSocket: Session Created");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        logger.debug("AMMSocket: Session Opened");

    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        logger.debug("AMMSocket: Session Closed");

    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
        logger.debug("AMMSocket: Session Idle");

    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
        logger.debug("AMMSocket: Exception Caught");
        cause.printStackTrace();

    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
        logger.debug("AMMSocket: Message Received");
        session.write(message);

    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
        logger.debug("AMMSocket: Message Send");

    }

    @Override
    public void inputClosed(IoSession session) throws Exception {
        super.inputClosed(session);
        logger.debug("AMMSocket: Input Closed");

    }
}
