package com.springdemo.helloworld.Controller;

import com.springdemo.helloworld.Models.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketChatController {
    @MessageMapping("/register")
            @SendTo("/topic/public")
            public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
                headerAccessor.getSessionAttributes().put("email", chatMessage.getSender());
                return chatMessage;
            }

    @MessageMapping("/chat.send")
            @SendTo("/topic/public")
            public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
                return chatMessage;
            }

}
