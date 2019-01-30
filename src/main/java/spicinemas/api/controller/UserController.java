package spicinemas.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spicinemas.api.db.UserRepository;
import spicinemas.api.model.User;
import spicinemas.api.service.UserService;
import spicinemas.api.to.UserTO;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;



    @RequestMapping(value = "/users",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> handleCreateUsers(@RequestBody UserTO userTo) {

        ResponseEntity<?> response;
        try{
        List<Error> errors = new ArrayList<Error>();
        if(!userService.validateEmail(userTo.getEmail())){
            errors.add(new Error("Invalid email"));
        }
        if(!userService.validateName(userTo.getName())){
            errors.add(new Error("Invalid name"));
        }
        if(!userService.validatePassword(userTo.getPassword(),userTo.getConfirmedPassword())){
            errors.add(new Error("Invalid Password"));

        }
        if(!errors.isEmpty()) {
            response = new ResponseEntity<List<Error>>(errors, HttpStatus.BAD_REQUEST);
        }
        else{
            User user = getModelFromTo(userTo);
            User createdUser= userRepository.createUser(user);
            response = new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
        }
        }
        catch(Exception e){
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    public User getModelFromTo(UserTO userTo){
        String email =userTo.getEmail();
        String name = userTo.getName();
        String encodedPassword = passwordEncoder.encode(userTo.getPassword());
        return new User(email,name,encodedPassword);
    }
}
