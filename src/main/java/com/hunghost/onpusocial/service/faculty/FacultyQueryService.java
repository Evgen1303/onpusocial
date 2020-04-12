package com.hunghost.onpusocial.service.faculty;

import com.hunghost.onpusocial.entity.Faculty;
import com.hunghost.onpusocial.exception.ResourceNotFoundException;
import com.hunghost.onpusocial.repositories.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FacultyQueryService {

    private FacultyRepository facultyRepository;

    @Autowired
    public FacultyQueryService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty getFacultyById(Long id) {
        return facultyRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Page<Faculty> getPage(Pageable pageable) {
        return facultyRepository.findAll(pageable);
    }
}
