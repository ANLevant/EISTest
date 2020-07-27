package com.test.eis.jms;

import com.test.eis.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.Date;

@Component
public class JmsProcessor {

    @Autowired
    private TestService testService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void processMessage() throws JMSException {
        jmsTemplate.setReceiveTimeout(1);
        StringBuilder stringBuilder = new StringBuilder();
        boolean queueWasFull = false;

        TextMessage message = (TextMessage) jmsTemplate.receive("testQueue");
        while (message != null) {
            queueWasFull = true;
            message.acknowledge();
            stringBuilder.append(message.getText()+" ");
            message = (TextMessage) jmsTemplate.receive("testQueue");
            System.out.println(message);
        }

        if(queueWasFull) {
            testService.storeMessageInDB(stringBuilder.toString());
        }
    }
}
