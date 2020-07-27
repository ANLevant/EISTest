package com.test.eis.controller;

import com.test.eis.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(path="/{message}", method = RequestMethod.POST)
    public void publishMessage(@PathVariable String message){
        testService.sendMessageToQueue(message);
    }

    @RequestMapping
    public String publishMessage(){
        return testService.getWholePhrase();
    }

}