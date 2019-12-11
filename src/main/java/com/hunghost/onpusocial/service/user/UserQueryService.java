package com.hunghost.onpusocial.service.user;

import com.hunghost.onpusocial.entity.User;
import com.hunghost.onpusocial.exception.ResourceNotFoundException;
import com.hunghost.onpusocial.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;


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

    public Boolean isFreeUsername(String username){
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return true;
        }
       else
           return false;
    }

    public Boolean isFreeEmail(String email){
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return true;
        }
        else
            return false;
    }

    public User FindUserByEmail(String email){
        log.info("Find user by email: " + email);
         User user =userRepository.findByEmail(email);
        if (user == null) {
            return null;
        }
        else
            return user;

    }

    public User getUserByUsername (String username){
       User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        else
        return user;
    }

    public User getAuthUser (){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.getUserByUsername(auth.getName());
        if (user == null) {
            throw new UsernameNotFoundException("not found user");
        }
        else
        return user;
    }

    public Collection<User> getSubscriptionAuthUser(){
        User user = this.getAuthUser();
        return user.getSubscriptions();
            }

    public Collection<User> getSubscribersAuthUser(){
        User user = this.getAuthUser();
        return user.getSubscribers();
    }

    public Collection<User> getSubscriptions(String login){

        User user = this.getUserByUsername(login);
        return user.getSubscriptions();
    }

    public Collection<User> getSubscribers(String login){

        User user = this.getUserByUsername(login);
        return user.getSubscribers();
    }

    public Boolean isSubscriptionToUser(String login){
        User authuser = this.getAuthUser();
        User subuser = this.getUserByUsername(login);
        return authuser.getSubscriptions().contains(subuser);
    }
    public Boolean isSubscriptionToUser(String login, String authuserlogin){
        User authuser = this.getUserByUsername(authuserlogin);
        User subuser = this.getUserByUsername(login);
return authuser.getSubscriptions().contains(subuser);
    }

}

