package spicinemas.api.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import spicinemas.api.db.UserRepository;
import spicinemas.api.model.Users;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JwtAuthControllerTest {

    @InjectMocks
    private JwtAuthController controller;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setup(){

    }

    @Test
    public void loginShouldCallGetUserByEmailOfUserRepository(){
        Users user=new Users("","alice@gmail.com","password");
        controller.login(user);

        verify(userRepository,times(1)).getUserByEmail("alice@gmail.com");

    }

    @Test
    public void loginShouldReturnUnauthorizedWhenPasswordIsIncorrect(){
        String salt=BCrypt.gensalt();
        String hash= BCrypt.hashpw("abcd",salt);
        Users mockUser=new Users("","alice@gmail.com",hash);
        when(userRepository.getUserByEmail(anyString())).thenReturn(mockUser);

        Users user=new Users("","alice@gmail.com","password");
        ResponseEntity res=controller.login(user);

        Assert.assertEquals(HttpStatus.UNAUTHORIZED,res.getStatusCode());
    }

    @Test
    public void loginShouldReturnOKWhenPasswordIsCorrect(){
        String salt=BCrypt.gensalt();
        String hash= BCrypt.hashpw("abcd",salt);
        Users mockUser=new Users("","alice@gmail.com",hash);
        when(userRepository.getUserByEmail(anyString())).thenReturn(mockUser);

        Users user=new Users("","alice@gmail.com","abcd");
        ResponseEntity res=controller.login(user);

        Assert.assertEquals(HttpStatus.OK,res.getStatusCode());
        Assert.assertNotNull(res.getBody());
    }

    @Test
    public void loginShouldReturnInternalServererrorWhenThereIsException(){
        when(userRepository.getUserByEmail(anyString())).thenThrow(new RuntimeException());

        Users user=new Users("","alice@gmail.com","abcd");
        ResponseEntity res=controller.login(user);

        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,res.getStatusCode());
    }

    @After
    public void tearDown(){}
}
