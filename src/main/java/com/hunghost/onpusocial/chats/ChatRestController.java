package com.hunghost.onpusocial.chats;

import com.hunghost.onpusocial.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
        return chatService.checkChat(chatid,owner);
    }

    @PostMapping
    public Chat saveChat(@RequestParam String owner) {
            return chatService.saveChat(owner);
    }

    @PostMapping("/addmember")
    public Set<User> addMemberToChat(@RequestParam String member, @RequestParam Long chatid ) {
        return chatService.addMemberToChat(member,chatid);
    }

    @GetMapping("/byuser")
    public List<Chat> getChatByUserLogin(@RequestParam String login) {
        return chatService.getChatListByUserLogin(login);
    }
}
