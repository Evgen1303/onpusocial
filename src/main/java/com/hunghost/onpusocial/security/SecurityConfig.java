package com.hunghost.onpusocial.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger log = LogManager.getLogger(SecurityConfig.class);
    @Autowired
    private UserAuthService userAuthService;

    protected void configure(HttpSecurity http) throws Exception {


        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,
                        "/users/isfreeusername/{username}",
                        "/users/isfreeemail/{email}",
                        "/authusers",
                        "/users/getbyemail/{email}",
                        "/users/{login}",
                        "/departments/faculty?facultyid={facultyid}"
                ).permitAll()
                .antMatchers(
                        "/ws",
                        "/ws/**",
                        "/app",
                        "/topic"
                ).permitAll()
                .antMatchers(HttpMethod.POST, "/users", "/login").permitAll()
                //.anyRequest().authenticated()
                .and()
                .sessionManagement()
                .maximumSessions(1).sessionRegistry(sessionRegistry())
                .and().and()
                .addFilterBefore(new WebSecurityCorsFilter(), ChannelProcessingFilter.class)
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler())
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .csrf().disable()
                .formLogin().disable();
                http.httpBasic().disable();



    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userAuthService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}
