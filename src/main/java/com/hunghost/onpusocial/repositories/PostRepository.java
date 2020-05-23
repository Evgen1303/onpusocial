package com.hunghost.onpusocial.repositories;

import com.hunghost.onpusocial.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface
PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
Page<Post> findByUserUsername(String username, Pageable pageable);
List<Post> findBySubscribers(Boolean isForSubsc);

Page<Post> findByAlluserOrStudygroup_IdOrKafedra_IdOrFaculty_IdOrUserIn(Boolean allUsers, Long studygroup, Long kafedra, Long faculty, List<User> user, Pageable pageable);
Page<Post> findByAlluserOrUserIn(Boolean allUsers, List<User> user, Pageable pageable);

}
