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
    public void userConverterService_ConvertToEntity_Test() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("testName");
        userDTO.setLastName("testName");
        userDTO.setBirthday(13031998l);
        userDTO.setEmail("testemail@gmail.com");
        userDTO.setPhone("+380664651493");
        userDTO.setDescription("bla bla bla");
        userDTO.setUsername( "testlogin");
        userDTO.setPassword("testpassword");

        User user = userConverterService.convertToEntity(userDTO);
        assertEquals("testlogin",user.getUsername());
        assertEquals("testpassword", user.getPassword());
    }
    @Test
    public void userCommandService_SaveUser_Test() throws Exception {
        UserDTO userdto = new UserDTO();
        userdto.setFirstName("testName");
        userdto.setLastName("testName");
        userdto.setBirthday(13031998l);
        userdto.setEmail("testemail@gmail.com");
        userdto.setPhone("+380664651493");
        userdto.setDescription("bla bla bla");
        userdto.setUsername( "testlogin");
        userdto.setPassword("testpassword");
        userCommandService.saveUser(userConverterService.convertToEntity(userdto));
        User user = userQueryService.getUserByUsername("sap");
        assertEquals("sap", user.getUsername());
        assertTrue(passwordEncoder.matches("pass",user.getPassword()));
    }

    @Test
    public void userRepository_FindUser_Test() throws Exception {
        User user = userRepository.findByUsername("login");
        assertEquals("login", user.getUsername());
      assertTrue(passwordEncoder.matches("pass",user.getPassword()));
    }

    @Test
    public void userCommandService_SubscribeToUser_Test()throws Exception{
       String answer = userCommandService.SubscribeToUser("sap","login");
        assertEquals("Subscribed",answer);
    }
}