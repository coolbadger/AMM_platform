package com.amm.test;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

/**
 * Created by liuminhang on 16/7/16.
 */
public class TestMinaClient {
    int CONNECT_TIMEOUT = 5000;
    NioSocketConnector connector;
    ConnectFuture future;
    IoSession session;

    public static void main(String[] args){
        System.out.println("Test Start!");
        TestMinaClient minaClient = new TestMinaClient();
        minaClient.sendMessage("AAAAAAA");

    }
    TestMinaClient(){
        connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(CONNECT_TIMEOUT);
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory()));
        connector.setHandler(new TestMinaClientIOHandler());
        future = connector.connect(new InetSocketAddress("127.0.0.1", 8088));
    }

    public void sendMessage(Object message){


        future.awaitUninterruptibly();
        session = future.getSession();
        session.write(message);
        session.closeOnFlush();
        if(future.isConnected()){
            connector.dispose();
        }

    }
}
