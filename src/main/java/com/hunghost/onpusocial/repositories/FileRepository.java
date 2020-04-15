package com.hunghost.onpusocial.repositories;

import com.hunghost.onpusocial.entity.ServerFile;
import com.hunghost.onpusocial.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<ServerFile, Long> {
    List<ServerFile> findAllByFileowner(User user);
}
