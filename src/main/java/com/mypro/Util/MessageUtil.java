package com.mypro.Util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.mypro.entity.po.BaseMessage;
import com.mypro.entity.po.ContentMessage;
import com.mypro.entity.po.Image;
import com.mypro.entity.po.ImageMessage;
import com.thoughtworks.xstream.XStream;

public class MessageUtil {

	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";

	@SuppressWarnings("unchecked")
	public static Map<String, String> xmlToMap(String dataFromUser)
			throws IOException, DocumentException {

		Map<String, String> map = new HashMap<String, String>();
		// 创建SAX对象
		SAXReader reader = new SAXReader();
		// 获取输入流
//		InputStream in = request.getInputStream();
		// 根据输入流获取文档对象
		Document doc = reader.read(new ByteArrayInputStream(dataFromUser.getBytes("UTF-8")));
		// 获取根节点
		Element root = doc.getRootElement();
		// 获取根节点下的子节点
		List<Element> list = root.elements();
		// 遍历子节点,使用集合进行封装
		for (Element e : list) {
			map.put(e.getName(), e.getText());
		}
		return map;
	}

	public static String textToXml(BaseMessage content) {
		XStream xstream = new XStream();
		xstream.alias("xml", content.getClass());
		return xstream.toXML(content);
	}

	/**回复文本消息
	 * @param ToUserName
	 * @param FromUserName
	 * @param Content
	 * @return
	 */
	public static ContentMessage initText(String ToUserName,
			String FromUserName, String Content) {
		ContentMessage contentMessage = new ContentMessage();
		contentMessage.setToUserName(FromUserName);
		contentMessage.setFromUserName(ToUserName);
		contentMessage.setContent(Content);
		contentMessage.setCreateTime(new Date().getTime());
		contentMessage.setMsgType(MESSAGE_TEXT);
		return contentMessage;
	}
	
	/**
	 * 回复图片信息
	 * @param ToUserName
	 * @param FromUserName
	 * @param MediaId
	 * @return
	 */
	public static ImageMessage initImage(String ToUserName,String FromUserName,String MediaId ){
		ImageMessage imageMessage = new ImageMessage();
		imageMessage.setToUserName(FromUserName);
		imageMessage.setFromUserName(ToUserName);
		imageMessage.setCreateTime(new Date().getTime());
		imageMessage.setMsgType(MESSAGE_IMAGE);
		Image image = new Image();
		image.setMediaId(MediaId);
		imageMessage.setImage(image);
		return imageMessage;
	}
}
