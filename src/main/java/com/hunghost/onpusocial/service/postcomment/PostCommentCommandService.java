package com.hunghost.onpusocial.service.postcomment;

import com.hunghost.onpusocial.entity.PostComment;
import com.hunghost.onpusocial.repositories.PostCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostCommentCommandService {
    PostCommentRepository postCommentRepository;

    @Autowired
    public PostCommentCommandService(PostCommentRepository postCommentRepository) {
        this.postCommentRepository = postCommentRepository;
    }

    public void savePostComment(PostComment postComment) {
        postCommentRepository.save(postComment);
    }
    public void deletePostComment(Long id) {
        postCommentRepository.deleteById(id);
    }

    public PostComment updatePostComment(Long id, PostComment postComment) {
        postComment.setId(id);
        postCommentRepository.save(postComment);
        return postComment;
    }
}
