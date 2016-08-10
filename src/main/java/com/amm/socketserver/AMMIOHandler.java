package com.amm.socketserver;

import com.amm.entity.GpsRecordEntity;
import com.amm.entity.client.GpsRecord;
import com.amm.socketserver.packetentity.AMMPacket;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;

/**
 * Created by liuminhang on 16/7/16.
 */
public class AMMIOHandler extends IoHandlerAdapter {
    private Logger logger = LoggerFactory.getLogger(AMMIOHandler.class);

    public AMMIOHandler() {
        super();
        logger.info("AMMSocket: IOHandler Initialized");
        System.out.println("AMMIOHandler 初始化!");
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
        logger.info("AMMSocket: Session Created");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        logger.info("AMMSocket: Session Opened");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        logger.info("AMMSocket: Session Closed");

    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
        logger.info("AMMSocket: Session Idle");

    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
        logger.info("AMMSocket: Exception Caught");
        cause.printStackTrace();

    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
        logger.info("AMMSocket: Message Received!");
        AMMPacket ammPacket = (AMMPacket) message;
        logger.info("机器编号:" + ammPacket.AMMMachineID
                + ";工人编号:" + ammPacket.AMMWorkerID
                + ";DATA字符串:" + ammPacket.AMMDataString);

        String[] parseResult = ammPacket.AMMDataString.split("\\|",-1);
        if(parseResult[0].equalsIgnoreCase("logreq")){
            String resDataString;
            if(ammPacket.AMMWorkerID.equals("111")){
                resDataString = "logrep|1|5";
            }
            else {
                resDataString = "logrep|0|5";
            }
            ammPacket.AMMDataString = resDataString;
            session.write(ammPacket);
        }
        if(parseResult[0].equalsIgnoreCase("locreq")){
            //locreq|补传|经度|纬度|高度|速度|精度|时间|传感器1|传感器2
            try {
                GpsRecordEntity gpsRecordEntity = new GpsRecordEntity();

//                gpsRecordEntity.
                gpsRecordEntity.setLng(new BigDecimal(parseResult[2]).setScale(6,BigDecimal.ROUND_HALF_UP));
                gpsRecordEntity.setLat(new BigDecimal(parseResult[3]).setScale(6,BigDecimal.ROUND_HALF_UP));
//                gpsRecordEntity.setAlt(Integer.parseInt(parseResult[4]));
//                gpsRecordEntity.setSpeed(Integer.parseInt(parseResult[5]));
//                gpsRecordEntity.setAccuracy(Integer.parseInt(parseResult[6]));
//                gpsRecordEntity.setGpsTime();
                logger.info("返回位置上报成功信息");
                String resDataString = "locrep|1|5";
                ammPacket.AMMDataString = resDataString;
                session.write(ammPacket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            //返回位置上报成功信息
            logger.info("返回位置上报失败信息");
            String resDataString = "locrep|0|5";
            ammPacket.AMMDataString = resDataString;
            session.write(ammPacket);
        }


    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
        logger.info("AMMSocket: Message Send:" + message.toString() );

    }

    @Override
    public void inputClosed(IoSession session) throws Exception {
        super.inputClosed(session);
        logger.debug("AMMSocket: Input Closed");

    }
}
