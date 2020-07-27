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

    @JmsListener(destination = "testProcessedQueue")
    public void processMessage(String message) throws InterruptedException, JMSException {
        testService.storeMessageInDB(message);
    }
}
