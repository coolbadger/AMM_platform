package com.amm.utils;

import com.amm.entity.GpsRecordEntity;
import com.amm.entity.client.GpsRecordSave;
import com.amm.queue.SendMes;
import com.amm.service.AMMClientPacketService;
import com.amm.socketserver.packetentity.AMMPacket;
import com.amm.variables.GpsRecordVariables;
import org.apache.mina.core.session.IoSession;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
public class MessageHandlerThread implements Runnable{
    private SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
    private AMMPacket aMMPacket;
    private IoSession session;
    private AMMClientPacketService ammClientPacketService;
    private  String[] parseResult;
    public MessageHandlerThread(IoSession session,AMMPacket aMMPacket, AMMClientPacketService ammClientPacketService, String[] parseResult){
        this.aMMPacket = aMMPacket;
        this.session = session;
        this.ammClientPacketService = ammClientPacketService;
        this.parseResult = parseResult;
    }
    public void run() {
        String timeInerval = "5";

        boolean noError = true;
        //String[] parseResult = aMMPacket.AMMDataString.split("\\|",-1);
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
        }

        try {
            //locreq|时间|补传|经度|纬度|高度|速度|精度|传感器1|传感器2
            GpsRecordSave gpsRecordSave = new GpsRecordSave();
            gpsRecordSave.setGpsTime(msgTime);
            gpsRecordSave.setUserName(aMMPacket.AMMWorkerID);
            gpsRecordSave.setTerminalCode(aMMPacket.AMMMachineID);
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
                System.out.println("记录数据成功====================");
                try{
                    SendMes.sendMessageQueue(gpsRecordEntity);//存入队列
                            /*if (GpsRecordVariables.getCounts()>=90){//90
                                new MyThread(recMesService);//取出队列
                                GpsRecordVariables.setCounts(0);//队列中数据置为0
                                System.out.println("取出队列成功-----------------");
                            }*/
                    GpsRecordVariables.setCounts(GpsRecordVariables.getCounts()+1);//队列数+1
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("存入队列成功*********************");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
