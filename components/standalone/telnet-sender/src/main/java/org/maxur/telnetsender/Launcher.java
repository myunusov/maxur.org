/*
 * Copyright (c) 2013 Maxim Yunusov
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.maxur.telnetsender;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Telnet Sender Launcher.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>31/07/13</pre>
 */
public class Launcher {

    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.err.println("Usage with arguments: <host> <port> <command>");
            return;
        }
        final String host = args[0];
        final int port = Integer.parseInt(args[1]);
        final String command = args[2];
        run(host, port, command);
    }

    private static void run(String host, int port, String command) throws InterruptedException {
        final EventLoopGroup group = new NioEventLoopGroup();
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new TelnetClientInitializer());

            final Channel channel = bootstrap.connect(host, port).sync().channel();
            final ChannelFuture lastWriteFuture = channel.writeAndFlush(command + "\r\n");
            channel.closeFuture().sync();
            if (lastWriteFuture != null) {
                lastWriteFuture.sync();
            }
        } finally {
            group.shutdownGracefully();

        }
    }

    /**
     * Creates a newly configured {@link io.netty.channel.ChannelPipeline} for a new channel.
     *
     */
    private static class TelnetClientInitializer extends ChannelInitializer<SocketChannel> {

        private static final StringDecoder DECODER = new StringDecoder();

        private static final StringEncoder ENCODER = new StringEncoder();

        private static final TelnetClientHandler CLIENTHANDLER = new TelnetClientHandler();

        @Override
        public void initChannel(final SocketChannel channel) throws Exception {
            final ChannelPipeline pipeline = channel.pipeline();
            pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
            pipeline.addLast("decoder", DECODER);
            pipeline.addLast("encoder", ENCODER);
            pipeline.addLast("handler", CLIENTHANDLER);
        }
    }

    /**
     * Handles a client-side channel.
     *
     */
    @ChannelHandler.Sharable
    private static class TelnetClientHandler extends SimpleChannelInboundHandler<String> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
            System.out.println(msg);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            System.err.println("Unexpected exception from downstream.\n" + cause.getMessage());
            ctx.close();
        }
    }
}
