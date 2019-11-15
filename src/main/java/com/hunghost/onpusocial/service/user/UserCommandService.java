package com.hunghost.onpusocial.service.user;

import com.hunghost.onpusocial.entity.User;
import com.hunghost.onpusocial.exception.RequestValidationException;
import com.hunghost.onpusocial.repositories.UserRepository;
import com.hunghost.onpusocial.service.role.RoleQueryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserCommandService {

    private UserRepository userRepository;
    private UserQueryService userQueryService;
    private RoleQueryService roleQueryService;
    private static final Logger log = LogManager.getLogger(UserCommandService.class);
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserCommandService(UserRepository userRepository, UserQueryService userQueryService, RoleQueryService roleQueryService) {
        this.userRepository = userRepository;
        this.userQueryService = userQueryService;
        this.roleQueryService = roleQueryService;
    }

    public void saveUser(User user) {
        user.setAuthorities( Arrays.asList(
                roleQueryService.getRoleById(1L)));
        user.setPassword( passwordEncoder.encode(user.getPassword()));
        if(userQueryService.isFreeEmail(user.getEmail()) == true && userQueryService.isFreeUsername(user.getUsername()) == true) {
            userRepository.save(user);
            log.info("New user is save");
    }
        else {
            log.warn("This login or mail is reserved. Login:" +user.getUsername()+ ". Email: "+ user.getEmail());
            throw new RequestValidationException("This login or mail is reserved");
               }
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User user) {
        user.setId(id);
        userRepository.save(user);
        return user;
    }

}
