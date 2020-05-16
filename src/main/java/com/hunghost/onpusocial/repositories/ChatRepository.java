package com.hunghost.onpusocial.repositories;


import com.hunghost.onpusocial.chats.Chat;
import com.hunghost.onpusocial.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByMembersIsContaining(User user);
}
