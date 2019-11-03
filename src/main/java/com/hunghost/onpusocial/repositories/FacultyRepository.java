package com.hunghost.onpusocial.repositories;

import com.hunghost.onpusocial.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
