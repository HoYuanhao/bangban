package com.xunyanhui.jms.service;

import javax.jms.Destination;

public interface JMSProducer {
	
	void sendMessage(Destination destination, String message);
}
