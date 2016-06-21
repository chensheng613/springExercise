package com.mypro.activeMQ;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class MessageTopicProducer {

	@Autowired
	private JmsTemplate myJmsTemplate;
	
	@Autowired
	private Destination notifyTopic;
	
	
	public void sendMessageToTopic(Destination destination,final String msg){
		myJmsTemplate.setPubSubDomain(true);
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


	public void setNotifyTopic(Destination notifyTopic) {
		this.notifyTopic = notifyTopic;
	}
	
}
