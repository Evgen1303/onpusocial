package com.hunghost.onpusocial.service.post;

import com.hunghost.onpusocial.dto.PostDTO;

import com.hunghost.onpusocial.entity.Faculty;
import com.hunghost.onpusocial.entity.Post;
import com.hunghost.onpusocial.service.faculty.FacultyQueryService;
import com.hunghost.onpusocial.service.kafedra.KafedraQueryService;
import com.hunghost.onpusocial.service.studygroup.StudyGroupQueryService;
import com.hunghost.onpusocial.service.user.UserQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostConverterService {
    private UserQueryService userQueryService;
    private StudyGroupQueryService studyGroupQueryService;
    private KafedraQueryService kafedraQueryService;
    private FacultyQueryService facultyQueryService;
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public PostConverterService(UserQueryService userQueryService, StudyGroupQueryService studyGroupQueryService, KafedraQueryService kafedraQueryService, FacultyQueryService facultyQueryService) {
        this.userQueryService = userQueryService;
        this.studyGroupQueryService = studyGroupQueryService;
        this.kafedraQueryService = kafedraQueryService;
        this.facultyQueryService = facultyQueryService;
    }

    public PostDTO convertToDto(Post post) {
        return modelMapper.map(post, PostDTO.class);
    }

    public Post convertToEntity(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);
        post.setUser(userQueryService.getUserById(postDTO.getUser()));
        if (postDTO.getStudygroup() != null)
        post.setStudygroup(studyGroupQueryService.getStudyGroupById(postDTO.getStudygroup()));
        if (postDTO.getKafedra() != null)
        post.setKafedra(kafedraQueryService.getKafedraById(postDTO.getKafedra()));
        if (postDTO.getFaculty() != null)
        post.setFaculty(facultyQueryService.getKafedraById(postDTO.getFaculty()));
        return post;
    }
}
