package com.xunyanhui.jms.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.listener.SessionAwareMessageListener;

public class JMSConsumerSessionAwareMessageListener implements
		SessionAwareMessageListener<TextMessage> {

	@Override
	public void onMessage(TextMessage textMessage, Session session)
			throws JMSException {
		// TODO Auto-generated method stub
		System.out.println("SessionAware");
		System.out.println(textMessage.getText() + "\n");
		System.out.println("***********************");

		ApplicationContext act = new ClassPathXmlApplicationContext(
				"applicationContext-jms.xml");
		Destination queueDestination = (Destination) act
				.getBean("queueDestination");

		

		MessageProducer producer = session.createProducer(queueDestination);
		Message textMessage2 = session.createTextMessage("Producer");
		producer.send(textMessage2);

	}

}
