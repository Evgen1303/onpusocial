package com.hunghost.onpusocial.repositories;

import com.hunghost.onpusocial.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 User findByUsername(String username);
 User findByEmail(String email);
}
