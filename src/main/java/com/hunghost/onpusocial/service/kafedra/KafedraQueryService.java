package com.hunghost.onpusocial.service.kafedra;

import com.hunghost.onpusocial.entity.Kafedra;
import com.hunghost.onpusocial.exception.ResourceNotFoundException;

import com.hunghost.onpusocial.repositories.KafedraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class KafedraQueryService {


    private KafedraRepository kafedraRepository;

    @Autowired
    public KafedraQueryService(KafedraRepository kafedraRepository) {
        this.kafedraRepository = kafedraRepository;
    }

    public Kafedra getKafedraById(Long id) {
        return kafedraRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Page<Kafedra> getPageByFacultyId(Long id,Pageable pageable){
        return kafedraRepository.findAllByFaculty_Id(id,pageable);
    }

    public Page<Kafedra> getPage(Pageable pageable) {
        return kafedraRepository.findAll(pageable);
    }
}
