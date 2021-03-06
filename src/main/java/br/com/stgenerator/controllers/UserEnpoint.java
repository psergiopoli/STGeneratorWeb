package br.com.stgenerator.controllers;

import br.com.stgenerator.models.User;
import br.com.stgenerator.service.UserService;
import br.com.stgenerator.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserEnpoint {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserService userService;

    @Autowired
    public UserEnpoint(BCryptPasswordEncoder bCryptPasswordEncoder,
                       UserService userService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    public ResponseEntity<ResponseUtil> signUp(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.createUser(user);
        return new ResponseEntity<ResponseUtil>(new ResponseUtil("Signup success."), HttpStatus.CREATED);
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(Authentication authentication) {
        User user = userService.findUserByEmail(authentication.getName());
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers(Authentication authentication) {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
}
