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
import spicinemas.api.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpiCinemasApplication.class)
@ActiveProfiles("test")
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	private User user;

	@Before
	public void init() {
		user = new User("Alice", "alice@example.com", "password");
	}

	@Test
	public void createUserTest() {
		User createdUser = userRepository.createUser(user);
		Assert.assertEquals("Alice", createdUser.getName());
	}

	@Test
	public void getUserByEmailShouldReturnMeUserObject() {
		userRepository.createUser(user);
		User user = userRepository.getUserByEmail("alice@example.com");
		Assert.assertNotNull(user);
	}

	@Test(expected = Exception.class)
	public void getUserByEmailShouldThrowExceptionWhenEmailNotFound() {
		userRepository.getUserByEmail("fdakjfadjfk@kgadjkf.com");
	}

}
