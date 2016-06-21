package com.mypro.activeMQ;

import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.listener.SessionAwareMessageListener;

public class QueueMessageListener2 implements SessionAwareMessageListener<TextMessage>{
	public void onMessage(TextMessage message,Session session) {
		try {
			System.out.println("-----------------" + "queue2收到消息" + message.getText()
					+ "------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
