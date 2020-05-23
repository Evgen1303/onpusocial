package com.hunghost.onpusocial.chats;

import com.hunghost.onpusocial.dto.ChatDTO;
import com.hunghost.onpusocial.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("chats")
public class ChatRestController {
    private ChatService chatService;
    private  MessageService messageService;

    @Autowired
    public ChatRestController(ChatService chatService, MessageService messageService) {
        this.chatService = chatService;
        this.messageService = messageService;
    }

    @GetMapping("/checkchat")
    public Chat checkChat(@RequestParam Long chatid, @RequestParam String owner) {
        return chatService.checkChat(chatid,owner);
    }

    @PostMapping
    public Chat saveChat(@RequestBody ChatDTO chatDTO) throws IOException {
            return chatService.saveChat(chatDTO);
    }

    @PostMapping("/addmember")
    public Set<User> addMemberToChat(@RequestParam String member, @RequestParam Long chatid ) {
        return chatService.addMemberToChat(member,chatid);
    }

    @GetMapping("/byuser")
    public List<Chat> getChatByUserLogin(@RequestParam String login) {
        return chatService.getChatListByUserLogin(login);
    }

    @GetMapping("/messages")
    public List<Message> getMessagesByUserLogin(@RequestParam String login) {
        return messageService.getMessagesByUser(login);
    }
    @GetMapping("/chatmessages")
    public List<Message> getMessagesByChatId(@RequestParam Long chatId) {
        return messageService.getMessagesByChat(chatId);
    }
    @PostMapping("/messages")
    public Message createMessagesByUserLogin(@RequestBody ChatMessage chatMessage,@RequestParam Long chatId) {
        return messageService.saveMessage(chatMessage,chatId);
    }
    @DeleteMapping("/deleteuserfromchat")
    public Set<User> deleteuserFromChat(@RequestParam String login, @RequestParam Long chatId) {

        return chatService.deleteUserFromChat(login,chatId);
    }
}
