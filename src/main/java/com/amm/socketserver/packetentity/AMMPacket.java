package com.amm.socketserver.packetentity;

/**
 * Created by liuminhang on 16/7/27.
 */
public class AMMPacket {


    //包头(4)
    public byte[] AMMHeaders = {(byte)0x88,(byte)0x9A,(byte)0xA8,(byte)0xFE};
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
    public byte[] AMMTails = {(byte)0xA5,(byte)0xFF};

    public AMMPacket(){
    }
}
