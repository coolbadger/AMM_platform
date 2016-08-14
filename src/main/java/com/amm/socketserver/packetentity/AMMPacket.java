package com.amm.socketserver.packetentity;

/**
 * Created by liuminhang on 16/7/27.
 */
public class AMMPacket {


    //包头(4);
    public byte[] AMMHeaders;
    // 数据包总长(1)
    public  int 	AMMTotalLength;
    // 机器编号(2)
    public String 	AMMMachineID;
    // 工号(12)
    public String 	AMMWorkerID;
    // DATA流长度(1)
    public int 	AMMDataLength;
    // XML数据
    public String AMMDataString;
    //包尾(2B)
    public byte[] AMMTails;

    public AMMPacket(){
    }
}
