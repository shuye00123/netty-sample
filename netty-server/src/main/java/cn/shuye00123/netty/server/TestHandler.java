package cn.shuye00123.netty.server;

import cn.shuye00123.netty.server.protobuf.TestProto;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by shuye on 2017/9/15.
 */
public class TestHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TestProto.Sample sample = (TestProto.Sample) msg;
        System.out.println(sample.getErrcode());
        ctx.writeAndFlush(newResp(1));
    }

    private TestProto.Sample newResp(int code) {
        TestProto.Sample.Builder builder = TestProto.Sample.newBuilder();
        builder.setErrcode(code);
        return builder.build();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
