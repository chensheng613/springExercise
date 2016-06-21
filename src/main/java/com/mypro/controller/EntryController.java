package com.mypro.controller;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mypro.Util.CheckSignature;
import com.mypro.Util.MessageUtil;
import com.mypro.Util.MyProConstants;
import com.mypro.Util.WeChat.AesException;
import com.mypro.Util.WeChat.WXBizMsgCrypt;

@Controller
@RequestMapping("/weChat")
public class EntryController {

	/** 接入微信后台 */
	@RequestMapping(value = { "/weChatEntry" }, method = { RequestMethod.GET })
	public void coreEntry(HttpServletRequest request,
			HttpServletResponse reponse) {
		String signature = "";
		signature = request.getParameter("signature");

		String timestamp = "";
		timestamp = request.getParameter("timestamp");

		String nonce = "";
		nonce = request.getParameter("nonce");

		String echostr = "";
		echostr = request.getParameter("echostr");

		String token = MyProConstants.Token;
		PrintWriter out = null;
		try {
			out = reponse.getWriter();
			if (CheckSignature.check(signature, timestamp, nonce, token)) {
				out.print(echostr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	@RequestMapping(value = { "/weChatEntry" }, method = { RequestMethod.POST })
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		String msg_signature = request.getParameter("msg_signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		try {
			request.setCharacterEncoding("UTF-8");
			InputStream in = request.getInputStream();
			String postData = IOUtils.toString(in, "UTF-8");

			// 创建解密对象
			WXBizMsgCrypt wbc = new WXBizMsgCrypt(MyProConstants.Token,
					MyProConstants.EncodingAESKey, MyProConstants.appId);
			// 解密
			String dataFromUser = wbc.decryptMsg(msg_signature, timestamp,
					nonce, postData);
			System.out.println("用户提交的数据" + dataFromUser);
			Map<String, String> map = MessageUtil.xmlToMap(dataFromUser);
			String toUserName = map.get("ToUserName");
			String fromUserName = map.get("FromUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			String MediaId = map.get("MediaId");
			String message = "";
			String fromServer = "";
			if (MessageUtil.MESSAGE_TEXT.equals(msgType)) {
				if (content.equals(content)) {
					fromServer = MessageUtil.textToXml(MessageUtil.initText(
							toUserName, fromUserName, content));
					// 加密
					message = wbc.encryptMsg(fromServer, timestamp, nonce);
					System.out.println("发送的是文本：" + message);
				}
			} else if (MessageUtil.MESSAGE_IMAGE.equals(msgType)) {
				fromServer = MessageUtil.textToXml(MessageUtil.initImage(
						toUserName, fromUserName, MediaId));
				message = wbc.encryptMsg(fromServer, timestamp, nonce);
				System.out.println("发送的是图片" + message);
			} else if (MessageUtil.MESSAGE_EVENT.equals(msgType)) {
				if (MessageUtil.MESSAGE_SUBSCRIBE.equals(map.get("Event"))) {
					// 回复文本信息，组装成xml格式
					fromServer = MessageUtil.textToXml(MessageUtil.initText(
							toUserName, fromUserName, "欢迎关注我的微信号"));
					message = wbc.encryptMsg(fromServer, timestamp, nonce);
				}
			}
			out = response.getWriter();
			out.print(message);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}