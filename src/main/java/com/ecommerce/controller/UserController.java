package com.ecommerce.controller;

import com.ecommerce.dto.UserDto;
import com.ecommerce.entity.JwtRequest;
import com.ecommerce.entity.JwtResponse;
import com.ecommerce.entity.User;
import com.ecommerce.security.JwtHelper;
import com.ecommerce.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {

    private UserService userService;
    private AuthenticationManager manager;
    private JwtHelper helper;
    private ModelMapper modelMapper;
    @Autowired
    public UserController(UserService userService,AuthenticationManager manager,JwtHelper jwtHelper,ModelMapper modelMapper){
        this.userService = userService;
        this.manager = manager;
        this.helper = jwtHelper;
        this.modelMapper = modelMapper;
    }
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/auth/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getUsername(), request.getPassword());
        User userDetails = (User) userService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/auth/user")
    public ResponseEntity<UserDto> addUser(@RequestBody User user){
        return new ResponseEntity<>(modelMapper.map(this.userService.addUser(user),UserDto.class), HttpStatus.OK);
    }

    private void doAuthenticate(String username, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
