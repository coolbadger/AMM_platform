package com.amm.socketserver;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineDecoder;
import org.apache.mina.filter.codec.textline.TextLineEncoder;

import java.nio.charset.Charset;

/**
 * Created by liuminhang on 16/7/16.
 */
public class AMMCodeFactory implements ProtocolCodecFactory {
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        TextLineEncoder textLineEncoder = new TextLineEncoder(Charset.forName("US-ASCII"));
        return textLineEncoder;
    }

    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        AMMDecoder ammDecoder = new AMMDecoder();
        return ammDecoder;
    }
}
