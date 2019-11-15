package com.hunghost.onpusocial.controller;


import com.hunghost.onpusocial.dto.UserDTO;
import com.hunghost.onpusocial.entity.User;
import com.hunghost.onpusocial.service.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("users")
public class UserController {
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final String DEFAULT_SORT_FIELD = "firstName";
    private UserQueryService userQueryService;
    private UserCommandService userCommandService;
    private UserConverterService userConverterService;

    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    public UserController(UserQueryService userQueryService, UserCommandService userCommandService, UserConverterService userConverterService
    ) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
        this.userConverterService = userConverterService;
    }

    @GetMapping
    public Page<User> getPages(
            @PageableDefault(size = DEFAULT_PAGE_SIZE)
            @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
                    Pageable pageable
    ) {
        return userQueryService.getPage(pageable);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
              return userQueryService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody UserDTO userDTO) {
        User user = userConverterService.convertToEntity(userDTO);
        userCommandService.saveUser(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userCommandService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> putUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userCommandService.updateUser(id, userConverterService.convertToEntity(userDTO)));
    }

    @GetMapping("/isfreeusername/{username}")
    public Boolean isFreeUsername(@PathVariable String username) {
        return userQueryService.isFreeUsername(username);
    }

    @GetMapping("/isfreeemail/{email}")
    public Boolean isFreeEmail(@PathVariable String email) {
        return userQueryService.isFreeEmail(email);
    }

    @GetMapping("/authusers")
    public List<Object> getUsersFromSessionRegistry() {
        return sessionRegistry.getAllPrincipals();
    }
}
