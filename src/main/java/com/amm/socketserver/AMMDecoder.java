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
        //设置包头
        ammPacket.AMMHeaders = new byte[]{(byte)0x88,(byte)0x9A,(byte)0xA8,(byte)0xFE};
        //设置包尾
        ammPacket.AMMTails = new byte[]{(byte)0xA5,(byte)0xFF};
        if(ioBuffer.remaining()>ammPacket.AMMHeaders.length + 1){
            logger.info("开始解码");
            //标记,用于检测可能发生断包时reset
            ioBuffer.mark();
            //判断包头是否正确
            byte[] headerBytes = new byte[ammPacket.AMMHeaders.length];
            ioBuffer.get(headerBytes);
            logger.info("包头:" + bytesToHexString(headerBytes));
            if(Arrays.equals(headerBytes,ammPacket.AMMHeaders)){
                //获取包总长
                byte packtTotalLengthByte = ioBuffer.get();
                ammPacket.AMMTotalLength = (int)packtTotalLengthByte;
                logger.info("包总长:" + byteToHexString(packtTotalLengthByte) + "  " + ammPacket.AMMTotalLength);
                //剩余字节数不足(小于总长-包头字节数-总长字节数)
                if(ioBuffer.remaining()<ammPacket.AMMTotalLength - ammPacket.AMMHeaders.length -1){
                    logger.info("剩余字节数不足,等待后续包");
                    ioBuffer.reset();
                    return false;
                }
                //机器ID
                byte[] machineIdBytes = new byte[2];
                ioBuffer.get(machineIdBytes);
                ammPacket.AMMMachineID = String.valueOf(machineIdBytesToInt(machineIdBytes));
                logger.info("机器ID:" + bytesToHexString(machineIdBytes) + "  " + ammPacket.AMMMachineID);

                //工号
                byte[] workerIdBytes = new byte[12];
                ioBuffer.get(workerIdBytes);
                ammPacket.AMMWorkerID = bytesASCIIToString(workerIdBytes);
                logger.info("工人ID:" + bytesToHexString(workerIdBytes) + "  " + ammPacket.AMMWorkerID);

                //data段长度
                byte dataLengthByte = ioBuffer.get();
                ammPacket.AMMDataLength = (int)dataLengthByte;
                logger.info("data段长度:" + byteToHexString(dataLengthByte) + "  " + ammPacket.AMMDataLength);

                //data段内容
                byte[] dataStringBytes =  new byte[ammPacket.AMMDataLength];
                ioBuffer.get(dataStringBytes);
                ammPacket.AMMDataString = bytesASCIIToString(dataStringBytes);
                logger.info("data段内容:" + bytesToHexString(dataStringBytes) + "  " + ammPacket.AMMDataString);

                //包尾
                byte[] packetTailBytes = new byte[2];
                ioBuffer.get(packetTailBytes);
                logger.info("包尾:" + bytesToHexString(packetTailBytes));

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

    public String byteToHexString(byte src){
        byte[] bytes = {src};
        return bytesToHexString(bytes);
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
    public String byteASCIIToString(byte src){
        byte[] bytes = {src};
        return bytesASCIIToString(bytes);
    }
    //将byte[2]转为int 1~65536 ()
    public  int machineIdBytesToInt(byte[] b) {
        return  (b[0] & 0xFF) << 8 |
                (b[1] & 0xFF);
    }
}
