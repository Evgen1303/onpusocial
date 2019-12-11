import com.hunghost.onpusocial.OnpusocialApplication;
import com.hunghost.onpusocial.dto.UserDTO;
import com.hunghost.onpusocial.entity.User;
import com.hunghost.onpusocial.repositories.UserRepository;
import com.hunghost.onpusocial.service.user.UserCommandService;
import com.hunghost.onpusocial.service.user.UserConverterService;
import com.hunghost.onpusocial.service.user.UserQueryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OnpusocialApplication.class)
public class ApplicationTest {
    @Autowired
    UserConverterService userConverterService;
    @Autowired
    UserCommandService userCommandService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserQueryService userQueryService;


    @Test
    public void testConverterService() throws Exception {
        UserDTO userDTO = new UserDTO(
                "testName",
                "testLastName",
                13031998l,
                "testemail@gmail.com",
                "+380664651493",
                "bla bla bla",
                "asda/asda/asd.png",
                null,
                null,
                "testlogin",
                "testpassword"
        );

        User user = userConverterService.convertToEntity(userDTO);

        assertEquals("testlogin",user.getUsername());
        assertEquals("testpassword", user.getPassword());
    }


    @Test
    public void testSaveCommand() throws Exception {
        UserDTO userdto = new UserDTO(
                "Viktor",
                "Sapojnyak",
                1220231l,
                "sapmail@gmail.com",
                "380664651493",
                "test description",
                "/photo/test.png",
                null,
                null,
                "sap",
                "pass");
        userCommandService.saveUser(userConverterService.convertToEntity(userdto));
        User user = userQueryService.getUserByUsername("sap");
        assertEquals("sap", user.getUsername());
        assertTrue(passwordEncoder.matches("pass",user.getPassword()));
    }

    @Test
    public void FindUserInDB() throws Exception {
        User user = userRepository.findByUsername("login");
        assertEquals("login", user.getUsername());
      assertTrue(passwordEncoder.matches("pass",user.getPassword()));
    }

    @Test
    public void SubscribeOnUser()throws Exception{
       String answer = userCommandService.SubscribeToUser("sap","login");
        assertEquals("Subscribed",answer);
    }




}