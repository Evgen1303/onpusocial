package com.hunghost.onpusocial.service.studygroup;

import com.hunghost.onpusocial.dto.StudygroupDTO;
import com.hunghost.onpusocial.entity.Studygroup;
import com.hunghost.onpusocial.service.kafedra.KafedraQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudyGroupConverterService {
    private ModelMapper modelMapper = new ModelMapper();

    private KafedraQueryService kafedraQueryService;

    @Autowired
    public StudyGroupConverterService(KafedraQueryService kafedraQueryService) {
        this.kafedraQueryService = kafedraQueryService;
    }

    public StudygroupDTO convertToDto(Studygroup studygroup) {
        return modelMapper.map(studygroup, StudygroupDTO.class);
    }

    public  Studygroup convertToEntity(StudygroupDTO studygroupDTO) {
        Studygroup studygroup = modelMapper.map(studygroupDTO, Studygroup.class);
        studygroup.setKafedra(kafedraQueryService.getKafedraById(studygroupDTO.getKafedra()));
        return studygroup;

    }
}
