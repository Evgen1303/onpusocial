package com.hunghost.onpusocial.chats;

import com.hunghost.onpusocial.dto.ChatDTO;
import com.hunghost.onpusocial.entity.User;
import com.hunghost.onpusocial.exception.ResourceNotFoundException;
import com.hunghost.onpusocial.repositories.ChatRepository;
import com.hunghost.onpusocial.service.file.FileQueryService;
import com.hunghost.onpusocial.service.user.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public class ChatService {

    private ChatRepository chatRepository;
    private UserQueryService userQueryService;
    private FileQueryService fileQueryService;

    @Autowired
    public ChatService(ChatRepository chatRepository, UserQueryService userQueryService, FileQueryService fileQueryService) {
        this.chatRepository = chatRepository;
        this.userQueryService = userQueryService;
        this.fileQueryService = fileQueryService;
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

    public Chat saveChat(ChatDTO chatDTO) throws IOException {
        Chat chat = new Chat();
        chat.setOwner(userQueryService.getUserByUsername(chatDTO.getOwner()));
        chat.setName(chatDTO.getName());
        Set<User> memberChat = userQueryService.getUserListByIds(chatDTO.getMembers());
        memberChat.add(userQueryService.getUserByUsername(chatDTO.getOwner()));
        chat.setMembers(memberChat);
        if(chatDTO.getChatphoto()!=null){
            chat.setChatphoto(fileQueryService.getFilebyId(chatDTO.getChatphoto()));
        }
        chatRepository.save(chat);
        return chat;
    }

    public Chat getChatById(Long id){
        return chatRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Set<User> addMemberToChat(String member, Long chatId){
        User user = userQueryService.getUserByUsername(member);
        Chat chat = getChatById(chatId);
        Set<User> userSet = chat.getMembers();
        if(!chat.getMembers().contains(user)){
            userSet.add(user);
        }
        chat.setMembers(userSet);
        chatRepository.save(chat);
        return userSet;
    }

    public List<Chat> getChatListByUserLogin(String login){
        User user = userQueryService.getUserByUsername(login);
        return chatRepository.findByMembersIsContaining(user);
    }

    public Set<User> deleteUserFromChat(String login, Long chat_id){
        Chat chat = getChatById(chat_id);
        User user = userQueryService.getUserByUsername(login);

        Set<User> userSet = chat.getMembers();
        if(chat.getMembers().contains(user)){
            userSet.remove(user);
        }
        chat.setMembers(userSet);
        chatRepository.save(chat);
        return userSet;
    }
}
