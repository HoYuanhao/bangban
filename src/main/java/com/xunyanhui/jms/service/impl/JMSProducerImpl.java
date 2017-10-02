package com.xunyanhui.jms.service.impl;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.xunyanhui.jms.jms.JMSMessageCreator;
import com.xunyanhui.jms.service.JMSProducer;

@Service
public class JMSProducerImpl implements JMSProducer {
	
	@Resource
	private JmsTemplate jmsTemplate;
	
	@Resource
	private ActiveMQQueue defaultResponseQueue;
	
	@Override
	public void sendMessage(Destination destination,String message) {

		System.out.println(message);
		// TODO Auto-generated method stub	
		JMSMessageCreator myMessageCreator = new JMSMessageCreator(message,defaultResponseQueue);
		jmsTemplate.send(destination, myMessageCreator);
		System.out.println("sucess");
	}

}
