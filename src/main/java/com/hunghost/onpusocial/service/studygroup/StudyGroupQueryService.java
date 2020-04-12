package com.hunghost.onpusocial.service.studygroup;

import com.hunghost.onpusocial.entity.Studygroup;
import com.hunghost.onpusocial.exception.ResourceNotFoundException;
import com.hunghost.onpusocial.repositories.StudygroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudyGroupQueryService {
    private StudygroupRepository studygroupRepository;

    @Autowired
    public StudyGroupQueryService(StudygroupRepository studygroupRepository) {
        this.studygroupRepository = studygroupRepository;
    }

    public Studygroup getStudyGroupById(Long id) {
        return studygroupRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Page<Studygroup> getPage(Pageable pageable) {
        return studygroupRepository.findAll(pageable);
    }
    public Page<Studygroup> getPageByKafedraId(Pageable pageable, Long id){
        return studygroupRepository.findAllByKafedra_Id(id,pageable);
    }

}
