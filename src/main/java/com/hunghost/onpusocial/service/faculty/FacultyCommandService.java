package com.hunghost.onpusocial.service.faculty;

import com.hunghost.onpusocial.entity.Faculty;
import com.hunghost.onpusocial.repositories.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacultyCommandService {

    private FacultyRepository facultyRepository;

    @Autowired
    public FacultyCommandService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public void saveFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
    }
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    public Faculty updateFaculty(Long id, Faculty faculty) {
        faculty.setId(id);
        facultyRepository.save(faculty);
        return faculty;
    }
}
