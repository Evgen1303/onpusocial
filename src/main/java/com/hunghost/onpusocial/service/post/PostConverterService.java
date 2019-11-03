package com.hunghost.onpusocial.service.post;

import com.hunghost.onpusocial.dto.PostDTO;

import com.hunghost.onpusocial.entity.Post;
import com.hunghost.onpusocial.service.user.UserQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostConverterService {
    private UserQueryService userQueryService;
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public PostConverterService(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    public PostDTO convertToDto(Post post) {
        return modelMapper.map(post, PostDTO.class);
    }

    public Post convertToEntity(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);
        post.setUser(userQueryService.getUserById(postDTO.getUser()));
        return post;
    }
}
