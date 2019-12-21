package com.hunghost.onpusocial.repositories;

import com.hunghost.onpusocial.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableRepository extends JpaRepository<Discipline, Long> {
}
