package com.syed.quizApplication.securityFIlter;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtServiceInterface {

    String generateToken(String username);

    String extractUsername(String token);

    boolean validateToken(String token, UserDetails userDetails);
}
