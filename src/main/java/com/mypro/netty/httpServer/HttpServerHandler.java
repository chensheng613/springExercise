package com.mypro.netty.httpServer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest>{

	private final String url;
	
	public HttpServerHandler(String url) {
		super();
		this.url = url;
	}


	@Override
	protected void messageReceived(ChannelHandlerContext ctx,
			FullHttpRequest msg) throws Exception {
		
	}

}
