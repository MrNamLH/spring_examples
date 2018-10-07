package com.namlee.examples.spring_examples.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.namlee.examples.spring_examples.model.Hello;
import com.namlee.examples.spring_examples.model.User;

@Controller
public class WebSocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/hi")
    public Hello greeting(User user) throws Exception {

        return new Hello("Hi, " + user.getName() + "!");
    }
}
