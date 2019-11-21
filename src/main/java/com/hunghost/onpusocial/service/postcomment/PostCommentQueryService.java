package com.hunghost.onpusocial.service.postcomment;

import com.hunghost.onpusocial.entity.PostComment;
import com.hunghost.onpusocial.exception.ResourceNotFoundException;
import com.hunghost.onpusocial.repositories.PostCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostCommentQueryService {
    PostCommentRepository postCommentRepository;

    @Autowired
    public PostCommentQueryService(PostCommentRepository postCommentRepository) {
        this.postCommentRepository = postCommentRepository;
    }

    public PostComment getPostCommentById(Long id) {
        return postCommentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Page<PostComment> getPage(Pageable pageable) {
        return postCommentRepository.findAll(pageable);
    }
}
