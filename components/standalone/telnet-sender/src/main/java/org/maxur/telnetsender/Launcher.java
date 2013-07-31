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
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Telnet Sender Launcher.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>31/07/13</pre>
 */
public final class Launcher {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private Launcher() {
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage with arguments: <host> <port> <command>");
            return;
        }
        final String host = args[0];
        final int port = Integer.parseInt(args[1]);
        final String command = args[2];
        try {
            run(host, port, command);
        } catch (InterruptedException e) {
            System.err.println("Unexpected exception.\n" + e.getMessage());
        }
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

}
