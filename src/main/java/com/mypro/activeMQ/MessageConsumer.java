package com.mypro.activeMQ;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Destination;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

//消息消费者
public class MessageConsumer {

	@Autowired
	private JmsTemplate myJmsTemplate;

	@Autowired
	private Destination notifyQueue;

	public List<TextMessage> receiveMsg(Destination notifyQueue) {
		TextMessage msg = null;
		List<TextMessage> msgList = new ArrayList<TextMessage>();
		try {
			for (int i = 0; i < 5; i++) {
				msg = (TextMessage) myJmsTemplate.receive(notifyQueue);
				msgList.add(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msgList;
	}

	public JmsTemplate getMyJmsTemplate() {
		return myJmsTemplate;
	}

	public void setMyJmsTemplate(JmsTemplate myJmsTemplate) {
		this.myJmsTemplate = myJmsTemplate;
	}

}
