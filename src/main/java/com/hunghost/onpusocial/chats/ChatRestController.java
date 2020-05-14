package com.hunghost.onpusocial.chats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("chats")
public class ChatRestController {
    private ChatService chatService;

    @Autowired
    public ChatRestController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/checkchat")
    public Chat checkChat(@RequestParam Long chatid, @RequestParam String owner) {
        return null;
    }

    @GetMapping("/byuser/{login}")
    public List<Chat> getChatByUserLogin(@RequestParam String login) {
        return chatService.getChatListByUserLogin(login);
    }
}
