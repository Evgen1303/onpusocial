package com.hunghost.onpusocial.repositories;

import com.hunghost.onpusocial.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 User findByUsername(String username);
 User findByEmail(String email);
 List<User> findByStudygroup_Id(Long id);
 Set<User> findByIdIn(Set<Long> ids);

}
