package com.syed.quizApplication.serviceInterfaceImpl;

import com.syed.quizApplication.model.UserPrincipal;
import com.syed.quizApplication.model.Users;
import com.syed.quizApplication.repository.UserRespositoy;

import com.syed.quizApplication.serviceInterface.MyUserDetailsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsServiceInterface {


    @Autowired
    private UserRespositoy userRespositoy;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users=userRespositoy.findByUsername(username);


        if(users ==null){
            System.out.println("User not Found");
            throw  new UsernameNotFoundException("User Not Found");
        }

        return  new UserPrincipal(users);


//        return  new UserDetails() {
//            @Override
//            public Collection<? extends GrantedAuthority> getAuthorities() {
//                return  Collections.singleton(new SimpleGrantedAuthority("User"));
//            }
//
//            @Override
//            public String getPassword() {
//                return users.getPassword();
//            }
//
//            @Override
//            public String getUsername() {
//                return users.getUsername();
//            }
//        };
    }
}
