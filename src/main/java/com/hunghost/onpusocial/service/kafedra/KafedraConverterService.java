package com.hunghost.onpusocial.service.kafedra;

import com.hunghost.onpusocial.dto.KafedraDTO;
import com.hunghost.onpusocial.entity.Kafedra;
import com.hunghost.onpusocial.service.faculty.FacultyQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafedraConverterService {
    private ModelMapper modelMapper = new ModelMapper();
    private FacultyQueryService facultyQueryService;

    @Autowired
    public KafedraConverterService(FacultyQueryService facultyQueryService) {
        this.facultyQueryService = facultyQueryService;
    }



    public KafedraDTO convertToDto(Kafedra kafedra) {
        return modelMapper.map(kafedra, KafedraDTO.class);
    }

    public  Kafedra convertToEntity(KafedraDTO kafedraDTO) {
        Kafedra kafedra = modelMapper.map(kafedraDTO, Kafedra.class);
        kafedra.setFaculty(facultyQueryService.getFacultyById(kafedraDTO.getFaculty()));
        return kafedra;

    }
}
