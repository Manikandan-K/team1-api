package spicinemas.api.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import spicinemas.api.db.UserRepository;
import spicinemas.api.model.User;
import spicinemas.api.to.UserTO;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    UserController userController;

    @MockBean
    UserRepository userRepoMock;

    //@Test
    public void shouldCallCreateUser() {
        UserTO user = new UserTO();
        user.setName("alice");
        user.setEmail("alice@example.com");
        user.setPassword("abc");
        user.setConfirmedPassword("abc");
        userController.handleCreateUsers(user);
        verify(userRepoMock,times(1)).createUser(any(User.class));
    }
}
