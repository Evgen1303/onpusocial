package com.hunghost.onpusocial;

        import com.hunghost.onpusocial.entity.*;
        import com.hunghost.onpusocial.repositories.*;
        import com.hunghost.onpusocial.service.user.UserCommandService;
        import javafx.geometry.Pos;
        import org.apache.logging.log4j.LogManager;
        import org.apache.logging.log4j.Logger;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


        import javax.annotation.PostConstruct;
        import java.util.Arrays;

@SpringBootApplication
    public class OnpusocialApplication {


    private BCryptPasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private FacultyRepository facultyRepository;
    private KafedraRepository kafedraRepository;
    private StudygroupRepository studygroupRepository;
    private PostRepository postRepository;
    private  PostCommentRepository postCommentRepository;

    private static final Logger log = LogManager.getLogger(OnpusocialApplication.class);

    @Autowired
    public OnpusocialApplication(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository, FacultyRepository facultyRepository, KafedraRepository kafedraRepository, StudygroupRepository studygroupRepository, PostRepository postRepository, PostCommentRepository postCommentRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.facultyRepository = facultyRepository;
        this.kafedraRepository = kafedraRepository;
        this.studygroupRepository = studygroupRepository;
        this.postRepository = postRepository;
        this.postCommentRepository = postCommentRepository;
    }


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
            log.info("User create: "+user.getUsername());

        }

        if(facultyRepository.findAll().size() == 0){
            Faculty faculty = new Faculty();
            faculty.setFacultyName("test faculty");
            faculty.setFacultyDescription("test");
            facultyRepository.save(faculty);
            log.info("Faculty created: "+faculty.getFacultyName());
        }

        if(kafedraRepository.findAll().size() == 0){
            Kafedra kafedra = new Kafedra();
            kafedra.setNameKafedra("test");
            kafedra.setDescriptionKafedra("test");
            kafedra.setFaculty(facultyRepository.findAll().get(0));
            kafedraRepository.save(kafedra);
            log.info("Kafedra created: "+kafedra.getNameKafedra());
        }

        if(studygroupRepository.findAll().size() == 0){
            Studygroup studygroup = new Studygroup();
            studygroup.setCourse(1L);
            studygroup.setKafedra(kafedraRepository.findAll().get(0));
            studygroup.setDescriptionGroup("test");
            studygroup.setNameGroup("test");
            studygroup.setStream(1L);
            studygroupRepository.save(studygroup);
            log.info("Studygroup created: "+studygroup.getNameGroup());
        }

        User user2 = new User(
                "Victor",
                "Sap",
                1220231l,
                "victor@gmail.com",
                "380664651493",
                "test2 description",
                "",
                studygroupRepository.findAll().get(0),
                null,
                "victor",
                passwordEncoder.encode("victor"),
                Arrays.asList(
                        new Role("ROLE_USER"),
                        new Role("ROLE_ADMIN")));

        if (userRepository.findByUsername(user2.getUsername()) == null){
            userRepository.save(user2);
            log.info("User create: "+user2.getUsername());
        }

       if(postRepository.findAll().isEmpty()){
           Post post = new Post();
           post.setUser(userRepository.findByUsername(user2.getUsername()));
           post.setContent("test content");
           post.setNamePost("test");
           postRepository.save(post);
           log.info("Post created: "+post.getNamePost());
       }

       if(postCommentRepository.findAll().size() == 0){
           for(int i =0; i<3; i++){
               PostComment postComment = new PostComment();
               postComment.setPost(postRepository.findAll().get(0));
               postComment.setContent("test content "+i);
               postComment.setUser(userRepository.findByUsername(user.getUsername()));
               postCommentRepository.save(postComment);
               log.info("Comment to post created: "+postComment.getId());

           }
       }

    }
    public static void main(String[] args) {
        SpringApplication.run(OnpusocialApplication.class, args);
    }
}
