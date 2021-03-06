package com.hunghost.onpusocial.chats;

import com.hunghost.onpusocial.repositories.ChatRepository;
import com.hunghost.onpusocial.repositories.MessageRepository;
import com.hunghost.onpusocial.service.user.UserCommandService;
import com.hunghost.onpusocial.service.user.UserQueryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    private ChatService chatService;
    private UserQueryService userQueryService;
    private ChatRepository chatRepository;

    private static final Logger log = LogManager.getLogger(UserCommandService.class);

    @Autowired
    public MessageService(MessageRepository messageRepository, ChatService chatService, UserQueryService userQueryService, ChatRepository chatRepository) {
        this.messageRepository = messageRepository;
        this.chatService = chatService;
        this.userQueryService = userQueryService;
        this.chatRepository = chatRepository;
    }




    public Message saveMessage(ChatMessage chatMessage, Long chatid){
        Message message = new Message();
        message.setChat(chatService.getChatById(chatid));
        message.setContent(chatMessage.getContent());
        message.setLocalDateTime(chatMessage.getLocalDateTime());
        message.setMessageType(chatMessage.getType());
        message.setSender(chatMessage.getSender());
        message.setOwner(userQueryService.getUserByUsername(chatMessage.getSender()));
        messageRepository.save(message);
        log.info("Сообщение сохранилось");
        Chat chat = chatService.getChatById(message.getChat());
        chat.setMessage(message);
        chatRepository.save(chat);
        return message;
    }

    public List<Message> getMessagesByUser(String login){
        return messageRepository.findByOwner(userQueryService.getUserByUsername(login));
    }

    public List<Message> getMessagesByChat(Long chatId){
        return messageRepository.findByChat(chatService.getChatById(chatId));
    }
}
