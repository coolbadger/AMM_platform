package com.amm.socketserver;

import com.amm.socketserver.packetentity.AMMPacket;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by liuminhang on 16/7/31.
 */
public class TestClient {
    int CONNECT_TIMEOUT = 5000;
    NioSocketConnector connector;
    ConnectFuture future;
    IoSession session;



    public static void main(String[] args) throws IOException,InterruptedException {
        TestClient testClient = new TestClient();
        testClient.sendMessage();
    }

    public void sendMessage(){

        connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(CONNECT_TIMEOUT);
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new AMMCodeFactory()));
        connector.setHandler(new AMMIOHandler());
        future = connector.connect(new InetSocketAddress("127.0.0.1",8088));
//        future = connector.connect(new InetSocketAddress("192.168.1.200",8088));
        future.awaitUninterruptibly();
        session = future.getSession();

        AMMPacket ammPacket = new AMMPacket();
        System.out.println(ammPacket.AMMHeaders.length);
        ammPacket.AMMMachineID = "1";
        ammPacket.AMMWorkerID = "789012";
        ammPacket.AMMDataString = "locreq|admin";

        session.write(ammPacket);
        session.closeOnFlush();
        if(future.isConnected()){
            connector.dispose();
        }

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
}
