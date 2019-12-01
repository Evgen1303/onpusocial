package com.hunghost.onpusocial.repositories;

import com.hunghost.onpusocial.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
Page<Post> findByUserUsername(String username, Pageable pageable);


}
