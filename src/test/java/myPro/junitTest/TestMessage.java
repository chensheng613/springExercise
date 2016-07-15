/*package myPro.junitTest;

import javax.jms.Destination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mypro.activeMQ.MessageConsumer;
import com.mypro.activeMQ.MessageProducer;
import com.mypro.activeMQ.MessageTopicProducer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestMessage {

	@Autowired
	private Destination notifyQueue;
	
	@Autowired
	private Destination notifyTopic;

	@Autowired
	private MessageProducer messageProducer;
	
	@Autowired
	private MessageTopicProducer messageTopicProducer;

	@Autowired
	private MessageConsumer messageConsumer;

	@Test
	public void testQueueProduce() {
		String msg = null;
		for (int i = 0; i < 10; i++) {
			msg = "这是第" + i + "次发送" + "Hello queue!";
			messageProducer.sendMessage(notifyQueue, msg);
		}
	}
	@Test
	public void testTopicProduce() {
		String msg = null;
		for (int i = 0; i < 10; i++) {
			msg = "这是第" + i + "次发送" + "Hello topic!";
			messageTopicProducer.sendMessageToTopic(notifyTopic, msg);
		}
	}
	@Test
	public void testConsume() {
		List<TextMessage> list = messageConsumer.receiveMsg(notifyQueue);
		for (TextMessage textMessage : list) {
			try {
				System.out.println("------------------------"+textMessage.getText()+"-------------------------------");
				Thread.sleep(1000); 
			} catch (JMSException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
*/