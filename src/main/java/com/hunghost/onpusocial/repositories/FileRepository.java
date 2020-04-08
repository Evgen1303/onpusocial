package com.hunghost.onpusocial.repositories;

import com.hunghost.onpusocial.entity.ServerFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<ServerFile, Long> {
}
