package com.hunghost.onpusocial.controller;


        import com.hunghost.onpusocial.security.CustomAuthenticationManager;
        import com.hunghost.onpusocial.entity.User;
        import com.hunghost.onpusocial.security.UserAuthService;
        import com.hunghost.onpusocial.service.user.UserCommandService;
        import com.hunghost.onpusocial.service.user.UserQueryService;
        import org.apache.logging.log4j.LogManager;
        import org.apache.logging.log4j.Logger;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
        import org.springframework.security.core.Authentication;
        import org.springframework.security.core.context.SecurityContext;
        import org.springframework.security.core.context.SecurityContextHolder;
        import org.springframework.security.core.session.SessionRegistry;
        import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
        import org.springframework.web.bind.annotation.*;

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;


        import java.util.Collection;
        import java.util.List;

        import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@CrossOrigin
@RequestMapping("/")
public class AuthController {

    private UserQueryService userQueryService;
    private UserCommandService userCommandService;
    @Autowired
    private SessionRegistry sessionRegistry;

    private static final Logger log = LogManager.getLogger(UserAuthService.class);
    @Autowired
    HttpServletRequest req;
    HttpServletResponse response;
    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;

    @Autowired
    public AuthController(UserQueryService userQueryService, UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
    }

    @GetMapping("login")
    @ResponseBody
    public Boolean login(@RequestParam String login, @RequestParam String password) {
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(login,password);
        Authentication auth = customAuthenticationManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);

        HttpSession session = req.getSession(true);
        sessionRegistry.registerNewSession(session.getId(), SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        log.info("Login session: "+ session.getId());
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
        return sc.getAuthentication().isAuthenticated();
    }

    @GetMapping("authuser")
    public User getAuthUser() {
        return userQueryService.getAuthUser();
    }


    @GetMapping("/")
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public String logout_successful() {
          return "logout successful";
    }

    @GetMapping("authusers")
    public List<Object> getUsersFromSessionRegistry() {
        return sessionRegistry.getAllPrincipals();
    }

    @PostMapping("authuser/subscriptions/{peruser}")
    public String subscribeToUser (@PathVariable String peruser){
        return  userCommandService.SubscribeToUser(peruser);

    }

    @GetMapping("authuser/subscriptions")
    public Collection<User> getSubscriptionAuthUser (){
        return userQueryService.getSubscriptionAuthUser();
    }

    @GetMapping("authuser/subscriptions/{login}")
    public Boolean isSubscriptionAuthUser (@PathVariable String login){
        return userQueryService.isSubscriptionToUser(login);
    }

    @GetMapping("authuser/subscribers")
    public Collection<User> getSubscribersAuthUser (){
        return userQueryService.getSubscribersAuthUser();
    }

}
