package com.hunghost.onpusocial.service.studygroup;

import com.hunghost.onpusocial.entity.Studygroup;
import com.hunghost.onpusocial.repositories.StudygroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudyGroupCommandService {
    private StudygroupRepository studygroupRepository;

    @Autowired
    public StudyGroupCommandService(StudygroupRepository studygroupRepository) {
        this.studygroupRepository = studygroupRepository;
    }

    public void saveStudygroup(Studygroup studygroup) {
        studygroupRepository.save(studygroup);
    }
    public void deleteStudygroup(Long id) {
        studygroupRepository.deleteById(id);
    }

    public Studygroup updateStudygroup(Long id, Studygroup studygroup) {
        studygroup.setId(id);
        studygroupRepository.save(studygroup);
        return studygroup;
    }
}
