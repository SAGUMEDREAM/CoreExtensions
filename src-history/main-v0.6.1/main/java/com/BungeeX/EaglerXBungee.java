package com.BungeeX;

import com.KafuuChino0722.coreextensions.Config;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketClientCompressionHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.Future;

public class EaglerXBungee {
    public static void bootstrap() {
        startListen();
    }

    public static void startListen() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(new HttpObjectAggregator(65536));
                            pipeline.addLast(new WebSocketServerCompressionHandler());
                            pipeline.addLast(new WebSocketServerProtocolHandler("/websocket"));
                            pipeline.addLast(new WebSocketFrameHandler());
                        }
                    });

            ChannelFuture serverFuture = serverBootstrap.bind(Config.getConfigInt("BUNGEE-X-PORT")).sync();

            serverFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
    private static class WebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
        private Channel tcpChannel;

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
            if (frame instanceof TextWebSocketFrame) {
                // 从WebSocket连接接收数据
                String text = ((TextWebSocketFrame) frame).text();
                System.out.println("Received from WebSocket: " + text);

                // 连接到目标TCP服务器
                if (tcpChannel == null || !tcpChannel.isActive()) {
                    tcpChannel = new Bootstrap()
                            .group(ctx.channel().eventLoop())
                            .channel(NioSocketChannel.class)
                            .handler(new ChannelInitializer<SocketChannel>() {
                                @Override
                                protected void initChannel(SocketChannel ch) {
                                    ChannelPipeline pipeline = ch.pipeline();
                                    pipeline.addLast(WebSocketClientCompressionHandler.INSTANCE);
                                    pipeline.addLast(new WebSocketFrameHandler());
                                }
                            })
                            .connect(Config.getConfigString("BUNGEE-X-FORWARD-IP"), Config.getConfigInt("BUNGEE-X-FORWARD-PORT"))
                            .sync().channel();
                }

                // 发送数据到目标TCP服务器
                byte[] bytes = text.getBytes();
                ByteBuf buffer = tcpChannel.alloc().buffer(bytes.length);
                buffer.writeBytes(bytes);
                tcpChannel.writeAndFlush(buffer);
            }
        }
    }
}
