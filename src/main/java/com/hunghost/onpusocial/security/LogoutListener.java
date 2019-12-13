package com.hunghost.onpusocial.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;


@Component
public class LogoutListener implements ApplicationListener<SessionDestroyedEvent> {
    private static final Logger log = LogManager.getLogger(UserAuthService.class);
    @Autowired
    private SessionRegistry sessionRegistry;

    @Override
    public void onApplicationEvent(SessionDestroyedEvent event) {
        log.info("Logout session:" + event.getId());
        sessionRegistry.removeSessionInformation(event.getId());
    }

}
