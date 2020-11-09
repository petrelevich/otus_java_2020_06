package ru.otus.echo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/*
--add-exports java.base/jdk.internal.misc=ALL-UNNAMED -Dio.netty.tryReflectionSetAccessible=true
 */
public class EchoServer {
    private static final Logger logger = LoggerFactory.getLogger(EchoServer.class);
    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        new EchoServer().start();
    }

    private void start() throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(eventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(PORT))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind();
            channelFuture.addListener(future -> {
                if (future.isSuccess()) {
                    logger.info("waiting for client...");
                } else {
                    logger.info("error: {}", future.cause().getMessage());
                }
            });
            logger.info("binding...");

            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }
}
