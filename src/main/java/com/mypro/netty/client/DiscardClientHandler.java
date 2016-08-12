package com.mypro.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

public class DiscardClientHandler extends SimpleChannelInboundHandler<Object> {

	private ByteBuf content;
	private ChannelHandlerContext ctx;

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		this.ctx = ctx;

		// Initialize the message.
		content = ctx.alloc().directBuffer(DiscardClient.SIZE)
				.writeZero(DiscardClient.SIZE);

		// Send the initial messages.
		generateTraffic();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) {
		content.release();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}

	long counter;

	private void generateTraffic() {
		ctx.writeAndFlush(content.duplicate().retain()).addListener(
				trafficGenerator);
	}

	private final ChannelFutureListener trafficGenerator = new ChannelFutureListener() {
		public void operationComplete(ChannelFuture future) {
			if (future.isSuccess()) {
				generateTraffic();
			} else {
				future.cause().printStackTrace();
				future.channel().close();
			}
		}
	};

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg)
			throws Exception {
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
