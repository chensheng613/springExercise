package com.mypro.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

public class TimeServerHandler extends ChannelHandlerAdapter {

	private int counter;
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		/*ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);*/
		String body = (String) msg;
//		String body = new String(req, "UTF-8").substring(0,req.length-System.getProperty("line.separator").length());
		System.out.println("TIME SERVER read:"+body+"this count"+ ++counter);
		System.out.println(System.getProperty("line.separator"));
		String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(
				System.currentTimeMillis()).toString() : "BAD ORDER";
		currentTime = currentTime+System.getProperty("line.separator");	
		ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
		ctx.write(resp);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.close();
	}
}
