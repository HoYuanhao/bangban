package com.xunyanhui.jms.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class JMSDefaultResponseQueueListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		System.out.println("===========DefaultResponseQueueListener=========");
		try {
			System.out.println(((TextMessage) message).getText());

			System.out.println("===========DefaultResponseQueueListener�յ���Ϣ��=========");

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
