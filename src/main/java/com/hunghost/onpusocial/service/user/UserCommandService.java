package com.hunghost.onpusocial.service.user;

import com.hunghost.onpusocial.entity.User;
import com.hunghost.onpusocial.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCommandService {

    private UserRepository userRepository;

    @Autowired
    public UserCommandService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
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
