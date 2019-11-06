package com.hunghost.onpusocial.service.user;

import com.hunghost.onpusocial.entity.Role;
import com.hunghost.onpusocial.entity.User;
import com.hunghost.onpusocial.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class UserAuthService implements UserDetailsService {

    private UserRepository userRepository;
    private static final Logger log = LogManager.getLogger(UserAuthService.class);
    @Autowired
    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Find user by login: "+ username);
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getAuthorities()));    }

        private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

    }


    public UserDetails loadUserByLoginAndPass (String login, String password) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(login);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        if(new BCryptPasswordEncoder().matches(password,user.getPassword())== true){

            log.info("Password^ "+password+" = " + user.getPassword());
            return null;

        }
        else throw new UsernameNotFoundException("Invalid username or password.");

    }
}
