package com.hunghost.onpusocial.security;

import com.hunghost.onpusocial.entity.Role;
import com.hunghost.onpusocial.entity.User;
import com.hunghost.onpusocial.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
@Service
public class CustomAuthenticationManager implements AuthenticationManager {

    private UserRepository userRepository;
    private static final Logger log = LogManager.getLogger(UserAuthService.class);


    @Autowired
    public CustomAuthenticationManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        if (!new BCryptPasswordEncoder().matches(password,user.getPassword())) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        else {
            log.info("Authenticate user: "+ username);
            return new UsernamePasswordAuthenticationToken(username, password, mapRolesToAuthorities(user.getAuthorities()));
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

    }

}
