package com.test.eis.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsPublisher {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void publishMessage(String publishMessage){
        jmsTemplate.convertAndSend("testProcessedQueue", publishMessage);
    }
}