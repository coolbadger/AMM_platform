package com.amm.socketserver;

import com.amm.socketserver.packetentity.AMMPacket;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by liuminhang on 16/7/16.
 */
public class AMMDecoder extends CumulativeProtocolDecoder {
    private Logger logger = LoggerFactory.getLogger(AMMDecoder.class);

    protected boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {

        AMMPacket ammPacket = new AMMPacket();
        if(ioBuffer.remaining()>ammPacket.AMMHeaders.length){
            logger.info("开始解码");
            //判断包头是否正确
            byte[] headerBytes = new byte[ammPacket.AMMHeaders.length];
            ioBuffer.get(headerBytes);
            if(Arrays.equals(headerBytes,ammPacket.AMMHeaders)){
                //获取包总长
                byte packtTotalLengthByte = ioBuffer.get();
                ammPacket.AMMTotalLength = (int)packtTotalLengthByte;
                //机器ID
                byte[] machineIdBytes = new byte[2];
                ioBuffer.get(machineIdBytes);
                ammPacket.AMMMachineID = String.valueOf(machineIdBytesToInt(machineIdBytes));
                //工号
                byte[] workerIdBytes = new byte[12];
                ioBuffer.get(machineIdBytes);
                ammPacket.AMMWorkerID = bytesASCIIToString(workerIdBytes);
                //data段长度
                byte dataLengthByte = ioBuffer.get();
                ammPacket.AMMDataLength = (int)dataLengthByte;
                //data段内容
                byte[] dataStringBytes =  new byte[ammPacket.AMMDataLength];
                ioBuffer.get(dataStringBytes);
                ammPacket.AMMDataString = bytesASCIIToString(dataStringBytes);
                //包尾
                byte[] packetTailBytes = new byte[ammPacket.AMMTails.length];
                ioBuffer.get(packetTailBytes);
                if(Arrays.equals(packetTailBytes,ammPacket.AMMTails)){
                    logger.info("解码成功");
                    protocolDecoderOutput.write(ammPacket);
                    return  true;
                }
                else {
                    logger.info("包尾错误");
                    return false;
                }
            }
            else{
                logger.info("包头错误");
                return false;
            }
        }
        else{
            return  false;
        }

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
    //将byte[]转换为ASCII
    public String bytesASCIIToString(byte[] src){
        int tRecvCount = src.length;
        String nRcvString;
        StringBuffer  tStringBuf=new StringBuffer ();
        char[] tChars=new char[tRecvCount];

        for(int i=0;i<tRecvCount;i++){
            tChars[i]=(char)src[i];
        }

        tStringBuf.append(tChars);
        nRcvString=tStringBuf.toString();
        return nRcvString;

    }
    //将byte[2]转为int 1~65536 ()
    public  int machineIdBytesToInt(byte[] b) {
        return  (b[0] & 0xFF) << 8 |
                (b[1] & 0xFF);
    }
}
