package com.hunghost.onpusocial.repositories;

import com.hunghost.onpusocial.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface
PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
Page<Post> findByUserUsername(String username, Pageable pageable);

@Query(value = "SELECT * FROM  posts p where  p.studygroup_id = 1 or p.kafedra_id = 1 or p.faculty_id = 1 or p.user_id = 1 or p.alluser = true"
        ,nativeQuery = true)
Page<Post> findByStudygroup_Id(Pageable pageable);

}
