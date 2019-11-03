package com.hunghost.onpusocial;

        import com.hunghost.onpusocial.entity.Role;
        import com.hunghost.onpusocial.entity.User;
        import com.hunghost.onpusocial.repositories.UserRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


        import javax.annotation.PostConstruct;
        import java.util.Arrays;

@SpringBootApplication
public class OnpusocialApplication {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;



    @PostConstruct
    public void init(){
        User user = new User(
                "Test name",
                "Test lastname",
                1220231l,
                "test@gmail.com",
                "380664651493",
                "test description",
                "/photo/test.png",
                null,
                null,
                "login",
                passwordEncoder.encode("pass"),
                Arrays.asList(
                        new Role("ROLE_USER"),
                        new Role("ROLE_ADMIN")));

        if (userRepository.findByUsername(user.getUsername()) == null){
            userRepository.save(user);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(OnpusocialApplication.class, args);
    }

}
