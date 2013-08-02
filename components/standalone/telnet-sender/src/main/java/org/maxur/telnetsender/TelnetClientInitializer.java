package org.maxur.telnetsender;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Creates a newly configured {@link io.netty.channel.ChannelPipeline} for a new channel.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>31/07/13</pre>
 */
class TelnetClientInitializer extends ChannelInitializer<SocketChannel> {

    private static final StringDecoder DECODER = new StringDecoder();

    private static final StringEncoder ENCODER = new StringEncoder();

    private static final TelnetClientHandler CLIENTHANDLER = new TelnetClientHandler();

    @Override
    public void initChannel(final SocketChannel channel) {
        final ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", DECODER);
        pipeline.addLast("encoder", ENCODER);
        pipeline.addLast("handler", CLIENTHANDLER);
    }
}
