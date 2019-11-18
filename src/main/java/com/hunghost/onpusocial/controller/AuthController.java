package com.hunghost.onpusocial.controller;

        import com.hunghost.onpusocial.security.CustomAuthenticationManager;
        import com.hunghost.onpusocial.entity.User;
        import com.hunghost.onpusocial.service.user.UserCommandService;
        import com.hunghost.onpusocial.service.user.UserQueryService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.security.core.session.SessionRegistry;
        import org.springframework.web.bind.annotation.*;
        import java.util.Collection;
        import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/")
public class AuthController {

    private UserQueryService userQueryService;
    private UserCommandService userCommandService;
    @Autowired
    private SessionRegistry sessionRegistry;
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

        return customAuthenticationManager.login(login,password);
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
