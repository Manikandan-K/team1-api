package spicinemas.api.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import spicinemas.SpiCinemasApplication;
import org.junit.Assert;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpiCinemasApplication.class)
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    UserService userService;



    @Test
    public void shouldSuccesfullyValidateName(){
        Assert.assertTrue(userService.validateName("Alice"));
    }
    @Test
    public void shouldFailForEmptyName(){
        Assert.assertFalse(userService.validateName(""));
    }

    @Test
    public void shouldValidateEmail(){
        Assert.assertTrue(userService.validateEmail("alice@demo.com"));
    }
    @Test
    public void shouldFailForEmptyEmail(){
        Assert.assertFalse(userService.validateEmail(""));
    }
    @Test
    public void shouldFailForInvalidEmail1(){
        Assert.assertFalse(userService.validateEmail("alicedemo.com"));
    }
    @Test
    public void shouldFailForInvalidEmail2(){
        Assert.assertFalse(userService.validateEmail("alice@"));
    }

    @Test
    public void shouldValidatePassword(){
        Assert.assertTrue(userService.validatePassword("abc","abc"));
    }
    @Test
    public void shouldFailForInvalidPassword(){
        Assert.assertFalse(userService.validatePassword("abc", "xss"));
    }
    @Test
    public void shouldFailForEmptyPassword(){
        Assert.assertFalse(userService.validatePassword("",""));
    }



}
