package com.hunghost.onpusocial.service.user;

import com.hunghost.onpusocial.dto.UserDTO;
import com.hunghost.onpusocial.entity.User;
import com.hunghost.onpusocial.service.studygroup.StudyGroupQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserConverterService {
    private StudyGroupQueryService studyGroupQueryService;
    private ModelMapper modelMapper = new ModelMapper();

    public UserConverterService(StudyGroupQueryService studyGroupQueryService) {
        this.studyGroupQueryService = studyGroupQueryService;
    }

    public UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertToEntity(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        if(userDTO.getStudygroup() != null)
        user.setStudygroup(studyGroupQueryService.getStudyGroupById(userDTO.getStudygroup()));
        return user;
    }
}
