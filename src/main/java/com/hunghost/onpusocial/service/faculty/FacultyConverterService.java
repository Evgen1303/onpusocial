package com.hunghost.onpusocial.service.faculty;

import com.hunghost.onpusocial.dto.FacultyDTO;
import com.hunghost.onpusocial.entity.Faculty;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class FacultyConverterService {
    private ModelMapper modelMapper = new ModelMapper();

    public FacultyDTO convertToDto(Faculty faculty) {
        return modelMapper.map(faculty, FacultyDTO.class);
    }

    public  Faculty convertToEntity(FacultyDTO facultyDTO) {
        Faculty faculty = modelMapper.map(facultyDTO, Faculty.class);
        return faculty;

    }
}
