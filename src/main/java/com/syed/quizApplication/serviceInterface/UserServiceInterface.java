package com.syed.quizApplication.serviceInterface;

import com.syed.quizApplication.model.Users;
import org.springframework.http.ResponseEntity;

public interface UserServiceInterface {

    ResponseEntity<String> registerUser(Users users);

    ResponseEntity<String> Verify(Users users);
}
