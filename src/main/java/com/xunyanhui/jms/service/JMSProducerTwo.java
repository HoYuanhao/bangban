package com.xunyanhui.jms.service;

import javax.jms.Destination;



public interface JMSProducerTwo {
	
	void sendMessage(Destination destination,String message);
}
