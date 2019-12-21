package com.hunghost.onpusocial.repositories;

import com.hunghost.onpusocial.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudygroupTimetableRepository extends JpaRepository<Timetable, Long> {
}
