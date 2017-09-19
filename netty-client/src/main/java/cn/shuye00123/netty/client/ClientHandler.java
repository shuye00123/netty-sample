package cn.shuye00123.netty.client;

import cn.shuye00123.netty.client.protobuf.TestProto;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by shuye on 2017/9/19.
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    public void channelActive(ChannelHandlerContext ctx) {
        for (int i = 0; i < 5; i++) {
            ctx.write(newReq(i));
        }
        ctx.flush();
    }

    private TestProto.Sample newReq(int code) {
        TestProto.Sample.Builder builder = TestProto.Sample.newBuilder();
        builder.setErrcode(code);
        return builder.build();
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("receive server response:[" + ((TestProto.Sample)msg).getErrcode() + "]");
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
