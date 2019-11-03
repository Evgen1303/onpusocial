package com.hunghost.onpusocial.repositories;

import com.hunghost.onpusocial.entity.Studygroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudygroupRepository extends JpaRepository<Studygroup, Long> {
}
