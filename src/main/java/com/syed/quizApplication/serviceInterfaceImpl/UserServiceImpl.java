package com.syed.quizApplication.serviceInterfaceImpl;

import com.syed.quizApplication.model.Users;
import com.syed.quizApplication.repository.UserRespositoy;
import com.syed.quizApplication.securityFIlter.JwtService;
import com.syed.quizApplication.serviceInterface.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServiceInterface {


    @Autowired
    private UserRespositoy respositoy;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<String> registerUser(Users users) {

        //BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();

        //geting from same user and setting as encoded user
        users.setPassword(passwordEncoder.encode(users.getPassword()));

        respositoy.save(users);
        return  ResponseEntity.status(HttpStatus.CREATED).body("User has been Register Successfully");
    }

    @Override
    public ResponseEntity<String> Verify(Users users) {

      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(),users.getPassword()));

        if (authentication.isAuthenticated())
          return  ResponseEntity.status(HttpStatus.OK).body(jwtService.generateToken(users.getUsername()));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Details not found");
    }
}
