package com.hunghost.onpusocial.repositories;

import com.hunghost.onpusocial.chats.Chat;
import com.hunghost.onpusocial.chats.Message;
import com.hunghost.onpusocial.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByOwner(User user);
    List<Message> findByChat(Chat chat);
}
