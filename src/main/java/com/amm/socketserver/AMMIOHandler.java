package com.amm.socketserver;

import com.amm.entity.GpsRecordEntity;
import com.amm.entity.WorkerEntity;
import com.amm.entity.client.GpsRecord;
import com.amm.entity.client.GpsRecordSave;
import com.amm.gps.GpsConvert;
import com.amm.gps.WebRequest;
import com.amm.service.AMMClientPacketService;
import com.amm.service.GpsRecordService;
import com.amm.service.WorkerService;
import com.amm.socketserver.packetentity.AMMPacket;
import org.apache.commons.lang3.Validate;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liuminhang on 16/7/16.
 */
public class AMMIOHandler extends IoHandlerAdapter {
    private Logger logger = LoggerFactory.getLogger(AMMIOHandler.class);
    @Autowired
    private AMMClientPacketService ammClientPacketService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");

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
        logger.error(cause.getMessage());

    }
    //按|分割
        /*
        功能
        客户端发送                       备注
        服务器返回                       备注
        登录
        logreq|时间|密码                 时间格式为YYMMDDHHmmss,北京时间，下同　
        logrep|时间|结果|时间间隔           结果为0则登录失败，1则登录成功，时间间隔为秒，返回的时间为收到对应请求的时间，下同
        上传农机信息
        locreq|时间|补传|经度|纬度|高度|速度|精度|时间|传感器1|传感器2    时间为GPS时间;传感器状态可以有多个，如果有记录未传完情况下保存记录就记为1，否则为0
        locrep|时间|结果|时间间隔           结果为0则上传失败，1则上传成功;时间间隔单位为秒
        errors|时间|原因                  返回其他错误信息

         */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
        logger.info("AMMSocket: Message Received!");
        AMMPacket ammPacket = (AMMPacket) message;
        String timeInerval = "5";

        logger.info("机器编号:" + ammPacket.AMMMachineID
                + ";工人编号:" + ammPacket.AMMWorkerID
                + ";DATA字符串:" + ammPacket.AMMDataString);

        boolean noError = true;
        String[] parseResult = ammPacket.AMMDataString.split("\\|",-1);
        //获取消息类型和时间
        String msgTypeStr = null;
        String msgTimeStr = null;
        Date msgTime = null;
        try {
            msgTypeStr = parseResult[0];
            msgTimeStr = parseResult[1];
            msgTime = sdf.parse(msgTimeStr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("解析消息类型及时间时发生错误");
            logger.error(e.getMessage());
            noError = false;
            ammPacket.AMMDataString = "errors|解析消息类型及时间时发生错误:" + msgTypeStr ;
            session.write(ammPacket);
        }
        if(noError){
            String resDataString = null;
            if(msgTypeStr.equalsIgnoreCase("logreq")){
                //登录请求
                boolean logreqSuccess = false;
                if(ammPacket.AMMWorkerID!=null){
                    try {
                        String workerPassword = parseResult[2];
                        WorkerEntity workerEntity = ammClientPacketService.isLogin(ammPacket.AMMWorkerID,workerPassword,ammPacket.AMMMachineID);
                        if (workerEntity !=null){
                            //登录成功
                            logreqSuccess = true;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error(e.getMessage());
                        //登录失败
                    }
                }
                if(logreqSuccess){
                    logger.info("登陆成功!");
                    resDataString = "logrep|" + msgTimeStr + "|1|" + timeInerval;
                }
                else {
                    logger.info("登陆失败!");
                    resDataString = "logrep|" + msgTimeStr + "|0|" + timeInerval;
                }
                ammPacket.AMMDataString = resDataString;
                session.write(ammPacket);
            }
            else if(msgTypeStr.equalsIgnoreCase("locreq")){
                boolean locreqSucess = false;
                try {
                    //locreq|时间|补传|经度|纬度|高度|速度|精度|传感器1|传感器2
                    GpsRecordSave gpsRecordSave = new GpsRecordSave();
                    gpsRecordSave.setGpsTime(msgTime);
                    gpsRecordSave.setUserName(ammPacket.AMMWorkerID);
                    gpsRecordSave.setTerminalCode(ammPacket.AMMMachineID);
                    if(parseResult[3]!=null&&parseResult[3].length()>0) {
                        gpsRecordSave.setLng(new BigDecimal(parseResult[3]).setScale(6,BigDecimal.ROUND_HALF_UP));
                    }
                    if(parseResult[4]!=null&&parseResult[4].length()>0) {
                        gpsRecordSave.setLat(new BigDecimal(parseResult[4]).setScale(6,BigDecimal.ROUND_HALF_UP));
                    }



                    if(parseResult[5]!=null&&parseResult[5].length()>0){
                        gpsRecordSave.setAlt(new BigDecimal(parseResult[5]).setScale(2,BigDecimal.ROUND_HALF_UP));
                    }
                    if(parseResult[6]!=null&&parseResult[6].length()>0){
                        gpsRecordSave.setSpeed(new BigDecimal(parseResult[6]).setScale(2,BigDecimal.ROUND_HALF_UP));
                    }
                    if(parseResult[7]!=null&&parseResult[7].length()>0){
                        gpsRecordSave.setAccuracy(new BigDecimal(parseResult[7]).setScale(2,BigDecimal.ROUND_HALF_UP));
                    }
                    if(parseResult.length>8){gpsRecordSave.setSensor1(parseResult[8]);}
                    if(parseResult.length>9){gpsRecordSave.setSensor2(parseResult[9]);}
                    if(parseResult.length>10){gpsRecordSave.setSensor3(parseResult[10]);}
                    if(parseResult.length>11){gpsRecordSave.setSensor4(parseResult[11]);}
                    if(parseResult.length>12){gpsRecordSave.setSensorExtra(parseResult[12]);}

                    GpsRecordEntity gpsRecordEntity = ammClientPacketService.save(gpsRecordSave);
                    if(gpsRecordEntity!=null){
                        //位置信息记录成功
                        locreqSucess = true;
                        // TODO: 2016/9/28 监听gps坐标保存,在新线程查询api并保存修正坐标//                if (gpsRecordSave.getLng() != null && gpsRecordSave.getLat() != null) {
//                    Double[] fixedGps = WebRequest.getGpsFixed(gpsRecordSave.getLng().doubleValue(), gpsRecordSave.getLat().doubleValue());
//                    gpsRecordSave.setLngFixed(new BigDecimal(fixedGps[0]));
//                    gpsRecordSave.setLatFixed(new BigDecimal(fixedGps[1]));
//                }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                    locreqSucess = false;
                }
                if(locreqSucess){
                    //位置信息记录成功
                    logger.info("位置信息记录成功");
                    resDataString = "locrep|" + msgTimeStr + "|1|" + timeInerval;
                }
                else {
                    logger.info("位置信息记录失败");
                    resDataString = "locrep|" + msgTimeStr + "|0|" + timeInerval;
                }
                ammPacket.AMMDataString = resDataString;
                session.write(ammPacket);
            }
            else {
                logger.error("未知请求类型:" + msgTypeStr);
                ammPacket.AMMDataString = "errors|未知请求类型:" + msgTypeStr ;
                session.write(ammPacket);
            }
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
