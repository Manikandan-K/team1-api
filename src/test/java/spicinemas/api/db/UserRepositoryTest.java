package spicinemas.api.db;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import spicinemas.SpiCinemasApplication;
import spicinemas.api.model.Users;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpiCinemasApplication.class)
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    Users user;

    @Before
    public void init() {
        user = new Users("Alice","alice@example.com", "password");
    }

    @Test
    public void createUserTest() {
       Users createdUser = userRepository.createUser(user);
      Assert.assertEquals("Alice", createdUser.getName());
    }
}
