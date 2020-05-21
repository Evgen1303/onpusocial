package com.hunghost.onpusocial.chats;

import com.hunghost.onpusocial.service.user.UserCommandService;
import com.hunghost.onpusocial.service.user.UserQueryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;


@Controller
public class ChatController {
    private ChatService chatService;
    private MessageService messageService;
    private UserQueryService userQueryService;
    private static final Logger log = LogManager.getLogger(UserCommandService.class);

    @Autowired
    public ChatController(ChatService chatService, MessageService messageService, UserQueryService userQueryService) {
        this.chatService = chatService;
        this.messageService = messageService;
        this.userQueryService = userQueryService;
    }

    @MessageMapping("/chat.sendMessage/{chatid}")
    @SendTo("/topic/public/{chatid}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage, @DestinationVariable Long chatid) {
        //chatService.checkChat(chatid,chatMessage);
        chatMessage.setChatId(chatid);
        chatMessage.setServerFile(userQueryService.getUserByUsername(chatMessage.getSender()).getProfilephoto());
        if(chatMessage.getType() == ChatMessage.MessageType.CHAT){
            messageService.saveMessage(chatMessage,chatid);
        }
        return chatMessage;
    }

    @MessageMapping("/chat.addUser/{chatid}")
    @SendTo("/topic/public/{chatid}")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, @DestinationVariable Long chatid,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        //chatService.checkChat(chatid,chatMessage);
        chatMessage.setChatId(chatid);
        chatMessage.setServerFile(userQueryService.getUserByUsername(chatMessage.getSender()).getProfilephoto());
        if(chatMessage.getType() == ChatMessage.MessageType.CHAT){
            messageService.saveMessage(chatMessage,chatid);
        }
        return chatMessage;
    }

}