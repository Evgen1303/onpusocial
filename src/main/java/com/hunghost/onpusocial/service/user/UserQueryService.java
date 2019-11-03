package com.hunghost.onpusocial.service.user;

import com.hunghost.onpusocial.entity.User;
import com.hunghost.onpusocial.exception.ResourceNotFoundException;
import com.hunghost.onpusocial.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Service
public class UserQueryService {

    private UserRepository userRepository;
    private static final Logger log = LogManager.getLogger(UserQueryService.class);

    @Autowired
    public UserQueryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        log.info("Find user by id: " + id);
        return userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }


    public Page<User> getPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

}

