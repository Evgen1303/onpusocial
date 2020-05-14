package com.hunghost.onpusocial.repositories;


import com.hunghost.onpusocial.chats.Chat;
import com.hunghost.onpusocial.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByMembersIsContaining(Set<User> members);
}
