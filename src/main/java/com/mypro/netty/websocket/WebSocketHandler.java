package com.mypro.netty.websocket;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderUtil;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.codec.http2.HttpUtil;
import io.netty.util.CharsetUtil;

public class WebSocketHandler extends SimpleChannelInboundHandler<Object> {

	WebSocketServerHandshaker handshake = null;
	

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		if (msg instanceof FullHttpRequest) {
			handleHttpRequest(ctx, (FullHttpRequest) msg);
		} else if (msg instanceof WebSocketFrame) {
			handleWebSocketFrame(ctx, (WebSocketFrame) msg);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	private void handleHttpRequest(ChannelHandlerContext ctx,
			FullHttpRequest req) throws Exception {
		if (!req.decoderResult().isSuccess()
				|| !"websocket".equals(req.headers().get("Upgrade"))) {
			sendHttpResponse(ctx, req, new DefaultFullHttpResponse(
					HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
		}

		WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
				"ws://localhost:8080/websocket", null, false);
		handshake = wsFactory.newHandshaker(req);
		if (handshake == null) {
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx
					.channel());
		} else {
			handshake.handshake(ctx.channel(), req);
		}
	}

	private void handleWebSocketFrame(ChannelHandlerContext ctx,
			WebSocketFrame frame) {
		if (frame instanceof CloseWebSocketFrame) {
			handshake
					.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
			return;
		}
		if (frame instanceof PingWebSocketFrame) {
			ctx.channel().write(
					new PingWebSocketFrame(frame.content().retain()));
			return;
		}
		if (!(frame instanceof TextWebSocketFrame)) {
			throw new UnsupportedOperationException(String.format(
					"frame type not supported", frame.getClass().getName()));
		}
		String request = ((TextWebSocketFrame) frame).text();

		ctx.channel().write(
				new TextWebSocketFrame(request + "欢迎使用Netty Websocket服务，现在时刻"
						+ new java.util.Date().toString()));
	}

	private static void sendHttpResponse(ChannelHandlerContext ctx,
			FullHttpRequest req, FullHttpResponse rep) {

		if (rep.status().code() != 200) {
			ByteBuf buf = Unpooled.copiedBuffer(rep.status().toString(),
					CharsetUtil.UTF_8);
			rep.content().writeBytes(buf);
			buf.release();
			HttpHeaderUtil.setContentLength(rep, rep.content().readableBytes());
		}
		ChannelFuture f = ctx.channel().writeAndFlush(rep);
		if(HttpHeaderUtil.isKeepAlive(req)||rep.status().code()!=200){
			f.addListener(ChannelFutureListener.CLOSE);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
