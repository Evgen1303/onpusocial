package com.hunghost.onpusocial.repositories;

import com.hunghost.onpusocial.entity.Kafedra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KafedraRepository extends JpaRepository<Kafedra, Long> {
}
