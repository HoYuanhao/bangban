package com.xunyanhui.jms.service.impl;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.xunyanhui.jms.jms.JMSMessageCreator;
import com.xunyanhui.jms.service.JMSProducerTwo;

@Service
public class JMSProducerTwoImpl implements JMSProducerTwo{
	
	@Resource
	private JmsTemplate jmsTemplateTwo;
	
	@Resource
	private ActiveMQQueue myQueueTwo;
	
	@Override
	public void sendMessage(Destination destination,String message) {

		System.out.println("22222222"+message);
		// TODO Auto-generated method stub	
		JMSMessageCreator myMessageCreator = new JMSMessageCreator(message,myQueueTwo);
		jmsTemplateTwo.send(destination,myMessageCreator);
		System.out.println("sucess");
	}

}
