package com.mypro.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketServer {

	public void run(int port) throws Exception {

		EventLoopGroup boosGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();

		ServerBootstrap bootStrap = new ServerBootstrap();

		try {
			bootStrap.group(boosGroup, workGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch)
								throws Exception {
							ChannelPipeline cp = ch.pipeline();
							cp.addLast("http-codec", new HttpServerCodec())
									.addLast("aggregator",
											new HttpObjectAggregator(65536))
									.addLast("http-chunked",
											new ChunkedWriteHandler())
									.addLast("handler", new WebSocketHandler());
						}
					});
			Channel ch = bootStrap.bind(port).sync().channel();
			System.out.println("server started at port "+port);
			ch.closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			boosGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}
	
	public static void main(String args[]) throws Exception{
		int port = 8080;
		try {
			if(args.length>0){
				port = Integer.parseInt(args[0]);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		new WebSocketServer().run(port);
	}
}
