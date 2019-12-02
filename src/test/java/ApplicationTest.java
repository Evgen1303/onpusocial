import com.hunghost.onpusocial.OnpusocialApplication;
import com.hunghost.onpusocial.dto.UserDTO;
import com.hunghost.onpusocial.entity.User;
import com.hunghost.onpusocial.service.user.UserConverterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OnpusocialApplication.class)
public class ApplicationTest {
    @Autowired
    UserConverterService userConverterService;

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

    public void testSaveCommand() throws Exception {



    }


}