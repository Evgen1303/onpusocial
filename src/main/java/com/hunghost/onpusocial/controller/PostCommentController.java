package com.hunghost.onpusocial.controller;

import com.hunghost.onpusocial.dto.PostCommentDTO;
import com.hunghost.onpusocial.entity.PostComment;
import com.hunghost.onpusocial.service.postcomment.PostCommentCommandService;
import com.hunghost.onpusocial.service.postcomment.PostCommentConverterService;
import com.hunghost.onpusocial.service.postcomment.PostCommentQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("postscomments")
public class PostCommentController {
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final String DEFAULT_SORT_FIELD = "id";

    private PostCommentQueryService postCommentQueryService;
    private PostCommentCommandService postCommentCommandService;
    private PostCommentConverterService postCommentConverterService;

    @Autowired
    public PostCommentController(PostCommentQueryService postCommentQueryService, PostCommentCommandService postCommentCommandService, PostCommentConverterService postCommentConverterService) {
        this.postCommentQueryService = postCommentQueryService;
        this.postCommentCommandService = postCommentCommandService;
        this.postCommentConverterService = postCommentConverterService;
    }

    @GetMapping("/all")
    public Page<PostComment> getPages(
            @PageableDefault(size = DEFAULT_PAGE_SIZE)
            @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
                    Pageable pageable
    ) {
        return postCommentQueryService.getPage(pageable);
    }
    @GetMapping("/{id}")
    public PostCommentDTO getPostComment(@PathVariable Long id) {
        return postCommentConverterService.convertToDto(postCommentQueryService.getPostCommentById(id));
    }

    @GetMapping
    public Page<PostComment> getPageCommentsByPost(
            @PageableDefault(size = DEFAULT_PAGE_SIZE)
            @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
                    Pageable pageable, @RequestParam Long postid
    ) {
        return postCommentQueryService.getCommentsByPostid(pageable,postid);
    }

    @PostMapping
    public PostComment createPostComment(@RequestBody PostCommentDTO postCommentDTO) {
        PostComment postComment = postCommentConverterService.convertToEntity(postCommentDTO);
        postCommentCommandService.savePostComment(postComment);
        return postComment;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PostComment> deletePostComment(@PathVariable Long id) {
        postCommentCommandService.deletePostComment(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostComment> putPostComment(@PathVariable Long id, @RequestBody PostCommentDTO postCommentDTO) {
        return ResponseEntity.ok(postCommentCommandService.updatePostComment(id, postCommentConverterService.convertToEntity(postCommentDTO)));
    }
}
