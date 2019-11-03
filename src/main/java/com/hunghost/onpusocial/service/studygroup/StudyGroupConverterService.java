package com.hunghost.onpusocial.service.studygroup;

import com.hunghost.onpusocial.dto.StudygroupDTO;
import com.hunghost.onpusocial.entity.Studygroup;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class StudyGroupConverterService {
    private ModelMapper modelMapper = new ModelMapper();

    public StudygroupDTO convertToDto(Studygroup studygroup) {
        return modelMapper.map(studygroup, StudygroupDTO.class);
    }

    public  Studygroup convertToEntity(StudygroupDTO studygroupDTO) {
        Studygroup studygroup = modelMapper.map(studygroupDTO, Studygroup.class);
        return studygroup;

    }
}
