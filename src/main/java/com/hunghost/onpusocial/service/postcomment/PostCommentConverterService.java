package com.hunghost.onpusocial.service.postcomment;

import com.hunghost.onpusocial.dto.PostCommentDTO;
import com.hunghost.onpusocial.entity.PostComment;
import com.hunghost.onpusocial.service.post.PostQueryService;
import com.hunghost.onpusocial.service.user.UserQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostCommentConverterService {
    private UserQueryService userQueryService;
    private  PostQueryService postQueryService;
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public PostCommentConverterService(UserQueryService userQueryService, PostQueryService postQueryService) {
        this.userQueryService = userQueryService;
        this.postQueryService = postQueryService;
    }

    public PostCommentDTO convertToDto(PostComment postComment) {
        PostCommentDTO postCommentDTO = new PostCommentDTO();

        postCommentDTO.setId(postComment.getId());
        postCommentDTO.setPost(postComment.getPost().getId());
        postCommentDTO.setUser(postComment.getUser().getId());
        postCommentDTO.setContent(postComment.getContent());
        return postCommentDTO;

    }

    public PostComment convertToEntity(PostCommentDTO postCommentDTO) {
        PostComment postComment = modelMapper.map(postCommentDTO, PostComment.class);
        postComment.setUser(userQueryService.getUserById(postCommentDTO.getUser()));
        postComment.setPost(postQueryService.getPostById(postCommentDTO.getPost()));
        return postComment;
    }
}
