package com.hunghost.onpusocial.service.post;

import com.hunghost.onpusocial.entity.Post;
import com.hunghost.onpusocial.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostCommandService {
    private PostRepository postRepository;

    @Autowired
    public PostCommandService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void savePost(Post post) {
Boolean isAlluser = post.getAlluser();
Boolean isSubsc = post.getSubscribers();

        if(
                isAlluser == null &&
                isSubsc == null &&
                post.getStudygroup() == null &&
                post.getKafedra()==null &&
                post.getFaculty() == null
                ){

        post.setAlluser(true);
        }
        postRepository.save(post);
    }
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public Post updatePost(Long id, Post post) {
        post.setId(id);
        postRepository.save(post);
        return post;
    }
}
