package com.test.eis.service;

import com.test.eis.dto.Message;
import com.test.eis.jms.JmsPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private JmsPublisher jmsPublisher;

    public void sendMessageToQueue(String message){
        jmsPublisher.publishMessage(message);
    }

    public String getWholePhrase(){
        List<Message> messages = mongoTemplate.findAll(Message.class);

        StringBuilder phraseBuilder = new StringBuilder();

        for(Message message: messages){
            phraseBuilder.append(message.getMessage()+" ");
        }

        return phraseBuilder.toString();
    }
}
