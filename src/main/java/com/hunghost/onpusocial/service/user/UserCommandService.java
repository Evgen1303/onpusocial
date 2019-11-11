package com.hunghost.onpusocial.service.user;

import com.hunghost.onpusocial.entity.Role;
import com.hunghost.onpusocial.entity.User;
import com.hunghost.onpusocial.repositories.RoleRepository;
import com.hunghost.onpusocial.repositories.UserRepository;
import com.hunghost.onpusocial.service.role.RoleQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserCommandService {

    private UserRepository userRepository;


    private RoleQueryService roleQueryService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserCommandService(UserRepository userRepository, RoleQueryService roleQueryService) {
        this.userRepository = userRepository;
        this.roleQueryService = roleQueryService;
    }

    public void saveUser(User user) {
        user.setAuthorities( Arrays.asList(
                roleQueryService.getRoleById(1L)));
        user.setPassword( passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
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
