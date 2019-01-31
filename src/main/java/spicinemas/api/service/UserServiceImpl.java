package spicinemas.api.service;

import org.springframework.stereotype.Component;
import spicinemas.api.to.UserTO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserServiceImpl implements UserService{

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    @Override
    public boolean validateEmail(String email){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if(email != null && matcher.find()){
            return true;
        }
        return false;
    }

    @Override
    public boolean validatePassword(String password, String confirmedPassword){
        if(password == null || password.isEmpty() || !password.equals(confirmedPassword)){
            return false;
        }
        return true;
    }

    @Override
    public boolean validateName(String name){
        if(name == null || name.isEmpty()){
            return false;
        }
        return  true;
    }
}
