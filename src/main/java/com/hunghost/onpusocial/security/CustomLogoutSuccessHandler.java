package com.hunghost.onpusocial.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CustomLogoutSuccessHandler extends
        SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private SessionRegistry sessionRegistry;

    private static final Logger log = LogManager.getLogger(CustomLogoutSuccessHandler.class);
    @Override
    public void onLogoutSuccess (
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {
        if(authentication != null) {
            HttpSession session = request.getSession(true);
            sessionRegistry.removeSessionInformation(session.getId());
            log.info("logout: " + authentication.getName());
            super.onLogoutSuccess(request, response, authentication);
        }


    }
}
