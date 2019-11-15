package com.hunghost.onpusocial.controller;


        import com.hunghost.onpusocial.security.CustomAuthenticationManager;
        import com.hunghost.onpusocial.entity.User;
        import com.hunghost.onpusocial.security.UserAuthService;
        import com.hunghost.onpusocial.service.user.UserQueryService;
        import org.apache.logging.log4j.LogManager;
        import org.apache.logging.log4j.Logger;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
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


        import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@CrossOrigin
@RequestMapping("/")
public class AuthController {

    private UserQueryService userQueryService;
    @Autowired
    private SessionRegistry sessionRegistry;

    private static final Logger log = LogManager.getLogger(UserAuthService.class);
    @Autowired
    HttpServletRequest req;
    HttpServletResponse response;

    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;

    @Autowired
    public AuthController(UserAuthService userAuthService, UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
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
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);

        return sc.getAuthentication().isAuthenticated();
    }

    @GetMapping("authuser")
    public User getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userQueryService.getUserByUsername(auth.getName());
        return user;
    }

    @GetMapping("custom_logout")
    public Boolean logout() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            HttpSession session = req.getSession(true);
            new SecurityContextLogoutHandler().logout(req, response, auth);// <= This is the call you are looking for.
//            sessionRegistry.removeSessionInformation(session.getId());
            return true;
        }
        return false;
    }

    @GetMapping("/")
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public String logout_successful() {
          return "logout successful";
    }




}
