package com.hunghost.onpusocial.chats;

import com.hunghost.onpusocial.entity.User;
import com.hunghost.onpusocial.exception.ResourceNotFoundException;
import com.hunghost.onpusocial.repositories.ChatRepository;
import com.hunghost.onpusocial.service.user.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ChatService {

    private ChatRepository chatRepository;
    private UserQueryService userQueryService;

    @Autowired
    public ChatService(ChatRepository chatRepository, UserQueryService userQueryService) {
        this.chatRepository = chatRepository;
        this.userQueryService = userQueryService;
    }

    public Chat checkChat(Long id, ChatMessage chatMessage){
        try {
            Chat chat = getChatById(id);
            return chat;
        }catch (Exception e){
            User user = userQueryService.getUserByUsername(chatMessage.getSender());
            Chat chat = new Chat();
            chat.setId(id);
            chat.setOwner(user);
            chat.addMember(user);
            return chat;
        }
    }

    public void saveChat(Chat chat){
        chatRepository.save(chat);
    }

    public Chat getChatById(Long id){
        return chatRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<Chat> getChatListByUserLogin(String login){
        Set<User> userSet = null;
        User user = userQueryService.getUserByUsername(login);
        userSet.add(user);
        return chatRepository.findByMembersIsContaining(userSet);
    }
}
