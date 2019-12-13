package com.hunghost.onpusocial.service.user;

import com.hunghost.onpusocial.dto.UserDTO;
import com.hunghost.onpusocial.entity.User;
import com.hunghost.onpusocial.exception.RequestValidationException;
import com.hunghost.onpusocial.repositories.UserRepository;
import com.hunghost.onpusocial.service.role.RoleQueryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserCommandService {

    private UserRepository userRepository;
    private UserQueryService userQueryService;
    private RoleQueryService roleQueryService;
    private UserConverterService userConverterService;
    private static final Logger log = LogManager.getLogger(UserCommandService.class);
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserCommandService(UserRepository userRepository, UserQueryService userQueryService, RoleQueryService roleQueryService,
                              UserConverterService userConverterService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userQueryService = userQueryService;
        this.roleQueryService = roleQueryService;
        this.userConverterService = userConverterService;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(User user) {
        if (userQueryService.getUserByUsername(user.getUsername()) == null) {
//        user.addAuthorities(roleQueryService.getRoleById(1l));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if (userQueryService.isFreeEmail(user.getEmail()) && userQueryService.isFreeUsername(user.getUsername())) {
                userRepository.save(user);
                log.info("New user is save");
            } else {
                log.warn("This login or mail is reserved. Login:" + user.getUsername() + ". Email: " + user.getEmail());
                throw new RequestValidationException("This login or mail is reserved");
            }
        } else log.info("This user reserved");
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(String login, UserDTO userDTO) {
        User updateuser = userConverterService.convertToEntityForUpdate(userDTO, userQueryService.getUserByUsername(login));
        userRepository.save(updateuser);
        log.info("Update user: " + updateuser.getUsername());
        return updateuser;
    }

    public String SubscribeToUser(String username) {
        User subuser = userQueryService.getUserByUsername(username);
        User authuser = userQueryService.getAuthUser();
        return LogicSubscribeToUser(subuser, authuser);
    }

    public String SubscribeToUser(String username, String owner) {
        User subuser = userQueryService.getUserByUsername(username);
        User authuser = userQueryService.getUserByUsername(owner);
        return LogicSubscribeToUser(subuser, authuser);

    }

    private String LogicSubscribeToUser(User subuser, User authuser) {
        if (subuser != null) {
            if (!authuser.getSubscriptions().contains(subuser)) {
                authuser.addSubscriptions(subuser);
                userRepository.save(authuser);
                log.info("User: " + authuser.getUsername() + " subscribe to " + subuser.getUsername());
                return "Subscribed";
            } else {
                authuser.deleteSubscriptions(subuser);
                userRepository.save(authuser);
                log.info("User: " + authuser.getUsername() + " unsubscribe to " + subuser.getUsername());
                return "Unsubscribed";
            }
        } else return "Not found user";
    }


}
