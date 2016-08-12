package com.mypro.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class DiscardServerHandler extends ChannelHandlerAdapter{

	    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
	    	ByteBuf in = (ByteBuf) msg;
	        try {
	            while (in.isReadable()) { // (1)
	                System.out.print((char) in.readByte());
	                System.out.flush();
	            }
	        } finally {
	            ReferenceCountUtil.release(msg); // (2)
	        }
	    }
}
