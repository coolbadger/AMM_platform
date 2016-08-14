package com.amm.socketserver;

import com.amm.socketserver.packetentity.AMMPacket;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liuminhang on 16/7/16.
 */
public class AMMEncoder implements ProtocolEncoder {
    private Logger logger = LoggerFactory.getLogger(AMMEncoder.class);

    public void encode(IoSession ioSession, Object o, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        AMMPacket ammPacket = (AMMPacket)o;
        logger.info("开始编码");
        //设置包头
        ammPacket.AMMHeaders = new byte[]{(byte)0xFE,(byte)0xA8,(byte)0x9A,(byte)0x88};
        //设置包尾
        ammPacket.AMMTails = new byte[]{(byte)0xA5,(byte)0xFF};
        //计算Data长度和总长度
        ammPacket.AMMDataLength = ammPacket.AMMDataString.getBytes("ASCII").length;
        ammPacket.AMMTotalLength = ammPacket.AMMHeaders.length +
                ammPacket.AMMTails.length + 16 + ammPacket.AMMDataLength;
        byte[] tempBytes;

        //头信息
        //总长度(1B)
        byte[] totalLengthbytes = {(byte)ammPacket.AMMTotalLength};
        tempBytes = spliceBytes(ammPacket.AMMHeaders,totalLengthbytes);
        //机器编号(2B)
        byte[] machineIdBytes = machineIdIntToBytes(Integer.parseInt(ammPacket.AMMMachineID));
        tempBytes = spliceBytes(tempBytes,machineIdBytes);
        //工号(12B)
        byte[] workerIdBytes = ammPacket.AMMWorkerID.getBytes("ASCII");
        if(workerIdBytes.length<12){
            int preLength = 12-workerIdBytes.length;
            byte[] preBytes = new byte[preLength];
            for(int j=0;j<preLength;j++){
                preBytes[j] = 0x00;
            }
            workerIdBytes = spliceBytes(preBytes,workerIdBytes);
        }
        tempBytes = spliceBytes(tempBytes,workerIdBytes);
        //DATA长度
        byte[] dataLengthbytes = {(byte)ammPacket.AMMDataLength};
        tempBytes = spliceBytes(tempBytes,dataLengthbytes);
        //DATA字符串
        tempBytes = spliceBytes(tempBytes,ammPacket.AMMDataString.getBytes("ASCII"));
        //尾字节
        tempBytes = spliceBytes(tempBytes,ammPacket.AMMTails);
        logger.info("编码完成");
        logger.info(bytesToHexString(tempBytes));
        System.out.println(bytesToHexString(tempBytes));

        protocolEncoderOutput.write(IoBuffer.wrap(tempBytes));//this is important
    }

    public void dispose(IoSession ioSession) throws Exception {

    }
    // 将两个byte[]拼接成一个byte[]
    public byte[] spliceBytes(byte[] startBytes, byte[] endBytes)
    {
        byte[] resultBytes = new byte[startBytes.length + endBytes.length];
        System.arraycopy(startBytes, 0, resultBytes, 0, startBytes.length);
        System.arraycopy(endBytes, 0, resultBytes, startBytes.length, endBytes.length);
        return resultBytes;
    }

    //将int转为byte[2] 1~65535 (机器ID)
    public byte[] machineIdIntToBytes(int a) {
        return new byte[] {
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }

    //将byte[]转换为ASCII,0x00除外
    public String bytesASCIIToString(byte[] src){
        int tRecvCount = src.length;
        String nRcvString;
        StringBuffer  tStringBuf=new StringBuffer ();
        for(int i=0;i<tRecvCount;i++) {
            if(src[i]!=0x00){
                tStringBuf.append((char)src[i]);
            }
        }
        nRcvString=tStringBuf.toString();
        return nRcvString;
    }
    //将byte[]转为Hex
    public String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }

            stringBuilder.append(hv.toUpperCase());
        }
        return stringBuilder.toString();
    }
}
