package com.hunghost.onpusocial.chats;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage/{chatid}")
    @SendTo("/topic/public/{chatid}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage, @DestinationVariable String chatid) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser/{chatid}")
    @SendTo("/topic/public/{chatid}")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, @DestinationVariable String chatid,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}