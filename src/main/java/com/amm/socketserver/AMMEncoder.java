package com.amm.socketserver;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * Created by liuminhang on 16/7/16.
 */
public class AMMEncoder implements ProtocolEncoder {

    public void encode(IoSession ioSession, Object o, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {

    }

    public void dispose(IoSession ioSession) throws Exception {

    }
}
