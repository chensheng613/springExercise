package com.mypro.activeMQ;

import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.listener.SessionAwareMessageListener;


/* * 消息订阅者
 * */
public class QueueMessageListener implements SessionAwareMessageListener<TextMessage> {

	
	public void onMessage(TextMessage message,Session session) {
		try {
			System.out.println("-----------------"+"queue1收到消息" + message.getText()+"------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
