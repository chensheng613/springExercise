package com.mypro.activeMQ;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class MessageProducer {

	@Autowired
	private JmsTemplate myJmsTemplate;
	
	@Autowired
	private Destination notifyQueue;
	
	//向指定队列发送消息
	public void sendMessage(Destination destination,final String msg){
		myJmsTemplate.setPubSubDomain(false);
		myJmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage tx =  session.createTextMessage();
				tx.setText(msg);
				return tx;
			}
		});
	}

	public void setMyJmsTemplate(JmsTemplate myJmsTemplate) {
		this.myJmsTemplate = myJmsTemplate;
	}

	public void setNotifyQueue(Destination notifyQueue) {
		this.notifyQueue = notifyQueue;
	}

}
