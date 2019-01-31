package spicinemas.api.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spicinemas.api.db.UserRepository;
import spicinemas.api.model.User;
import spicinemas.api.model.UserViewModel;

import java.security.Key;

@RestController
public class JwtAuthController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/login",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(User user){
        try {
           return authenticate(user);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity authenticate(User user){
        User dbUser = userRepository.getUserByEmail(user.getEmail());
        //TODO: find an appropriate status code
        return BCrypt.checkpw(user.getEncodedPassword(), dbUser.getEncodedPassword()) ?
                new ResponseEntity(prepareResponse(user), HttpStatus.OK) :
                new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    private UserViewModel prepareResponse(User user){
        String token= generateToken(user);
        return new UserViewModel(user.getName(),user.getEmail(),token);
    }

    private String generateToken(User user) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return  Jwts.builder().setSubject(user.getEmail())
                .signWith(key).compact();
    }
}
