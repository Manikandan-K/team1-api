package spicinemas.api.service;

import org.springframework.stereotype.Service;
import spicinemas.api.to.UserTO;


public interface UserService {

    public boolean validateEmail(String email);
    public boolean validatePassword(String password, String confirmedPassword);
    public boolean validateName(String name);
}
