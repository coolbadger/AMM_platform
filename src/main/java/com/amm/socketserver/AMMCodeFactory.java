package com.amm.socketserver;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * Created by liuminhang on 16/7/16.
 */
public class AMMCodeFactory implements ProtocolCodecFactory {
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        AMMEncoder ammEncoder = new AMMEncoder();
        return ammEncoder;
    }

    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        AMMDecoder ammDecoder = new AMMDecoder();
        return ammDecoder;
    }
}
