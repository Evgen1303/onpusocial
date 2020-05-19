package com.hunghost.onpusocial.chats;

import com.hunghost.onpusocial.exception.ResourceNotFoundException;
import com.hunghost.onpusocial.repositories.MessageRepository;
import com.hunghost.onpusocial.service.user.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    private ChatService chatService;
    private UserQueryService userQueryService;

    @Autowired
    public MessageService(MessageRepository messageRepository, ChatService chatService, UserQueryService userQueryService) {
        this.messageRepository = messageRepository;
        this.chatService = chatService;
        this.userQueryService = userQueryService;
    }

    public void saveMessage(ChatMessage chatMessage, Long chatid){
        Message message = new Message();
        message.setChat(chatService.getChatById(chatid));
        message.setContent(chatMessage.getContent());
        message.setLocalDateTime(chatMessage.getLocalDateTime());
        message.setMessageType(chatMessage.getType());
        message.setSender(chatMessage.getSender());
        message.setOwner(userQueryService.getUserByUsername(chatMessage.getSender()));
        messageRepository.save(message);
    }

    public List<Message> getMessagesByUser(String login){
        return messageRepository.findByOwner(userQueryService.getUserByUsername(login));
    }
}
