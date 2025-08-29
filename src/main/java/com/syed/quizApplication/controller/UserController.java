package com.syed.quizApplication.controller;


import com.syed.quizApplication.model.Users;
import com.syed.quizApplication.serviceInterface.MyUserDetailsServiceInterface;
import com.syed.quizApplication.serviceInterface.UserServiceInterface;
import com.syed.quizApplication.serviceInterfaceImpl.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceInterface userServiceInterface;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Users users){
        return userServiceInterface.registerUser(users);
    }

}
