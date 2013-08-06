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

package org.maxur.clitelnetclient;

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
        Terminal.info(msg);
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) {
        Terminal.error("Unexpected exception from downstream.\n" + cause.getMessage());
        ctx.close();
    }
}
