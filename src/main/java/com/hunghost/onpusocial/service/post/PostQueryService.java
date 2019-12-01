package com.hunghost.onpusocial.service.post;

import com.hunghost.onpusocial.entity.Post;
import com.hunghost.onpusocial.exception.ResourceNotFoundException;
import com.hunghost.onpusocial.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PostQueryService {
    private PostRepository postRepository;

    @Autowired
    public PostQueryService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Page<Post> getPage(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Page<Post> getPostByUserLogin(String username, Pageable pageable){
        return postRepository.findByUserUsername(username, pageable);
    }



}
