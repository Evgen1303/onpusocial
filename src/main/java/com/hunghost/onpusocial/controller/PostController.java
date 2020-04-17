package com.hunghost.onpusocial.controller;

import com.hunghost.onpusocial.dto.PostDTO;
import com.hunghost.onpusocial.entity.Post;
import com.hunghost.onpusocial.service.post.PostCommandService;
import com.hunghost.onpusocial.service.post.PostConverterService;
import com.hunghost.onpusocial.service.post.PostQueryService;
import com.hunghost.onpusocial.service.user.UserCommandService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("posts")
public class PostController {
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final String DEFAULT_SORT_FIELD = "id";

    private PostQueryService postQueryService;
    private PostCommandService postCommandService;
    private PostConverterService postConverterService;
    private static final Logger log = LogManager.getLogger(UserCommandService.class);
    @Autowired
    public PostController(PostQueryService postQueryService, PostCommandService postCommandService
            , PostConverterService postConverterService
    ) {
        this.postQueryService = postQueryService;
        this.postCommandService = postCommandService;
        this.postConverterService = postConverterService;
    }
    @GetMapping
    public Page<Post> getPages(
            @PageableDefault(size = DEFAULT_PAGE_SIZE)
            @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD, direction = Sort.Direction.DESC)})
                    Pageable pageable
    ) {
        log.info("Получена страница с поостами № " +pageable.getPageNumber());
        return postQueryService.getPage(pageable);
    }
    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        log.info("Получен пост ID: "+id);
        return postQueryService.getPostById(id);
    }

    @PostMapping
    public Post createPost(@RequestBody PostDTO postDTO) {
        Post post = postConverterService.convertToEntity(postDTO);
        postCommandService.savePost(post);
        log.info("Создан пост: "+post.getId());
        return post;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id) {
        postCommandService.deletePost(id);
        log.info("Удалён пост: "+id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> putPost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        log.info("Изменён пост: "+id);
        return ResponseEntity.ok(postCommandService.updatePost(id, postConverterService.convertToEntity(postDTO)));
    }

    @GetMapping("/user/{login}")
    public Page<Post> getPostsbyUserLogin(@PathVariable String login,
                                          @PageableDefault(size = DEFAULT_PAGE_SIZE)
                                          @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD, direction = Sort.Direction.DESC)})
                                          Pageable pageable) {
        log.info("Получена страница постов №" + pageable.getPageNumber() + " Пользователя " + login);
        return postQueryService.getPostByUserLogin(login, pageable);
    }

    @GetMapping("/authuser")
    public Page<Post> getPostsForUser(
                                          @PageableDefault(size = DEFAULT_PAGE_SIZE)
                                          @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
                                                  Pageable pageable) {
        log.info("Получена страница постов №" + pageable.getPageNumber() + " Для авторизированного пользователя ");
        return postQueryService.getPostsForUser(pageable);
    }



}
