package com.hunghost.onpusocial.chats;

import com.hunghost.onpusocial.entity.User;
import com.hunghost.onpusocial.exception.ResourceNotFoundException;
import com.hunghost.onpusocial.repositories.ChatRepository;
import com.hunghost.onpusocial.service.user.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
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

            Set<User> userSet = chat.getMembers();
            userSet.add(user);
            chat.setMembers(userSet);
            chatRepository.save(chat);
            return chat;
        }
    }

    public Chat checkChat(Long id, String owner){
        try {
            Chat chat = getChatById(id);
            return chat;
        }catch (Exception e){
            User user = userQueryService.getUserByUsername(owner);
            Chat chat = new Chat();
            chat.setId(id);
            chat.setOwner(user);
            Set<User> userSet = chat.getMembers();
            userSet.add(user);
            chat.setMembers(userSet);
            chatRepository.save(chat);

            return chat;
        }
    }

    public Chat saveChat(String owner){
        Chat chat = new Chat();
        chat.setOwner(userQueryService.getUserByUsername(owner));
        chat.setName("Chat "+owner);
        Set<User> memberChat = chat.getMembers();
        memberChat.add(userQueryService.getUserByUsername(owner));
        chat.setMembers(memberChat);
        chatRepository.save(chat);
        return chat;
    }

    public Chat getChatById(Long id){
        return chatRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<Chat> getChatListByUserLogin(String login){
        User user = userQueryService.getUserByUsername(login);
        return chatRepository.findByMembersIsContaining(user);
    }
}
