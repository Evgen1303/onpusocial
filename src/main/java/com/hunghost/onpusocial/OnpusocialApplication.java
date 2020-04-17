package com.hunghost.onpusocial;

        import com.hunghost.onpusocial.entity.*;
        import com.hunghost.onpusocial.repositories.*;
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


        User user = new User();
        user.setFirstName("General");
        user.setLastName("Admin");
        user.setBirthday(12345L);
        user.setEmail("test@gmail.com");
        user.setPhone("380664651493");
        user.setDescription("Test description");
        user.setUsername("login");
        user.setPassword(passwordEncoder.encode("pass"));
        user.setAuthorities(Arrays.asList(
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

        User user2 = new User();
        user2.setFirstName("Victor");
        user2.setLastName("Sap");
        user2.setBirthday(1220231l);
        user2.setEmail("victor@gmail.com");
        user2.setPhone("380664651493");
        user2.setDescription("test2 description");
        user2.setUsername("victor");
        user2.setPassword(passwordEncoder.encode("victor"));
        user2.setAuthorities(Arrays.asList(
                new Role("ROLE_USER"),
                new Role("ROLE_ADMIN")));
        user2.setStudygroup(studygroupRepository.findAll().get(0));

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
