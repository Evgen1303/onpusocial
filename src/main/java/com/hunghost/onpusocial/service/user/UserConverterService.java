package com.hunghost.onpusocial.service.user;

import com.hunghost.onpusocial.dto.UserDTO;
import com.hunghost.onpusocial.entity.User;
import com.hunghost.onpusocial.service.file.FileQueryService;
import com.hunghost.onpusocial.service.studygroup.StudyGroupQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserConverterService {
    private StudyGroupQueryService studyGroupQueryService;
    private FileQueryService fileQueryService;
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public UserConverterService(StudyGroupQueryService studyGroupQueryService, FileQueryService fileQueryService) {
        this.studyGroupQueryService = studyGroupQueryService;
        this.fileQueryService = fileQueryService;
    }

    public UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertToEntity(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        if (userDTO.getStudygroup() != null)
            user.setStudygroup(studyGroupQueryService.getStudyGroupById(userDTO.getStudygroup()));
        return user;
    }

    public User convertToEntityForUpdate(UserDTO userDTO, User olduser) {

        User user = olduser;

        if (userDTO.getStudygroup() != null)
         user.setStudygroup(studyGroupQueryService.getStudyGroupById(userDTO.getStudygroup()));

        if(userDTO.getProfilephoto() != null)
            user.setProfilephoto(fileQueryService.getFilebyId(userDTO.getProfilephoto()));

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setBirthday(userDTO.getBirthday());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setDescription(userDTO.getDescription());
        user.setStarosta(userDTO.getStarosta());
        user.setUsername(userDTO.getUsername());
        return user;
    }

}
