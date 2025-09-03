package com.syed.quizApplication.securityFIlter;

import com.syed.quizApplication.serviceInterface.MyUserDetailsServiceInterface;
import com.syed.quizApplication.serviceInterfaceImpl.JwtService;
import com.syed.quizApplication.serviceInterfaceImpl.MyUserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private  JwtService jwtService;

    @Autowired
    ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTeWVkQWJkdWxSYWhtYW4iLCJpYXQiOjE3NTY5MTU0MjAsImV4cCI6MTc1NjkxNjAyMH0.9pRrzznQAh-TpAYViCLOfDb3YdOdSd5GfkTyGJYh19A

        String authHeader=request.getHeader("Authorization");

        //value should be null only if you put "" this will drain your brain
        String token=null;
        String username=null;

        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            token=authHeader.substring(7);
            username =jwtService.extractUsername(token);
        }
        System.out.println("Looking for user: " + username + "-> user is there");

        if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails= context.getBean(MyUserDetailsServiceImpl.class).loadUserByUsername(username);

            if(jwtService.validateToken(token,userDetails)){

                UsernamePasswordAuthenticationToken authToken=
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request,response);

    }
}
