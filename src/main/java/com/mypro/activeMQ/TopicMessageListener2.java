package com.mypro.activeMQ;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TopicMessageListener2 implements MessageListener {

	public void onMessage(Message message) {
		try {
			TextMessage tm = (TextMessage) message;
			System.out.println("-----------------" + "topic2收到消息" + tm.getText()
					+ "------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
