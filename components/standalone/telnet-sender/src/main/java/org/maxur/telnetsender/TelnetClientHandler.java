package org.maxur.telnetsender;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Handles a client-side channel.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>31/07/13</pre>
 */
@ChannelHandler.Sharable
class TelnetClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final String msg) {
        System.out.println(msg);
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) {
        System.err.println("Unexpected exception from downstream.\n" + cause.getMessage());
        ctx.close();
    }
}
