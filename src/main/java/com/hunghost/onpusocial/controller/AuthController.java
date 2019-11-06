package com.hunghost.onpusocial.controller;

        import com.hunghost.onpusocial.CustomAuthenticationManager;
        import com.hunghost.onpusocial.service.user.UserAuthService;
        import org.apache.logging.log4j.LogManager;
        import org.apache.logging.log4j.Logger;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
        import org.springframework.security.core.Authentication;
        import org.springframework.security.core.context.SecurityContext;
        import org.springframework.security.core.context.SecurityContextHolder;
        import org.springframework.web.bind.annotation.*;

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpSession;

        import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@CrossOrigin
@RequestMapping("/")
public class AuthController {
    private UserAuthService userAuthService;
    private static final Logger log = LogManager.getLogger(UserAuthService.class);
    @Autowired
    HttpServletRequest req;
    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;
    @Autowired
    public AuthController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @GetMapping("loginn")
    @ResponseBody
    public Boolean login(@RequestParam String login, @RequestParam String password) {
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(login,password);
        Authentication auth = customAuthenticationManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
        return sc.getAuthentication().isAuthenticated();

    }


}
