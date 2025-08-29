package com.syed.quizApplication.serviceInterfaceImpl;

import com.syed.quizApplication.model.Users;
import com.syed.quizApplication.repository.UserRespositoy;
import com.syed.quizApplication.serviceInterface.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServiceInterface {


    @Autowired
    private UserRespositoy respositoy;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<String> registerUser(Users users) {

        //geting from same user and setting as encoded user
        users.setPassword(passwordEncoder.encode(users.getPassword()));

        respositoy.save(users);
        return  ResponseEntity.status(HttpStatus.CREATED).body("User has been Register Successfully");
    }
}
