import com.hunghost.onpusocial.OnpusocialApplication;
import com.hunghost.onpusocial.dto.UserDTO;
import com.hunghost.onpusocial.entity.Role;
import com.hunghost.onpusocial.entity.User;
import com.hunghost.onpusocial.repositories.PostRepository;
import com.hunghost.onpusocial.repositories.UserRepository;
import com.hunghost.onpusocial.service.user.UserCommandService;
import com.hunghost.onpusocial.service.user.UserConverterService;
import com.hunghost.onpusocial.service.user.UserQueryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

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
        User user = new User();
        user.setFirstName("General");
        user.setLastName("Admin");
        user.setBirthday(12345L);
        user.setEmail("test3@gmail.com");
        user.setPhone("380664651493");
        user.setDescription("Test description");
        user.setUsername("login3");
        user.setPassword(passwordEncoder.encode("pass"));
        user.setAuthorities(Arrays.asList(
                new Role("ROLE_USER"),
                new Role("ROLE_ADMIN")));
        userRepository.save(user);

        userRepository.delete(user);
    }

    @Test
    public void userRepository_FindUser_Test() throws Exception {
        User user = userRepository.findByUsername("login");
        assertEquals("login", user.getUsername());
      assertTrue(passwordEncoder.matches("pass",user.getPassword()));
    }

    @Test
    public void userQueryService_getUserByLogin_Test() throws Exception{
    User user = userQueryService.getUserByUsername("login");
    assertEquals("login",user.getUsername());

    }

    }