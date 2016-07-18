package com.amm.socketserver;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;

/**
 * Created by liuminhang on 16/7/16.
 */
public class AMMDecoder implements ProtocolDecoder {
    private Logger logger = LoggerFactory.getLogger(AMMDecoder.class);

    public void decode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        logger.info("开始解码");
        byte[] inBytes = new byte[ioBuffer.remaining()];
        ioBuffer.get(inBytes);
        String hexString = bytesToHexString(inBytes);
        logger.info("解码结果:\n" + hexString);

        try{
            FileOutputStream os = new FileOutputStream(hexString);
            byte[] b = inBytes;
            os.write(b);
            os.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {

    }

    public void dispose(IoSession ioSession) throws Exception {

    }
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
}
