package com.syed.quizApplication.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/*

Request
→ Security Filter Chain (creates default controller when using .httpBasic(Customizer.withDefaults()))
→ AuthenticationManager
→ DaoAuthenticationProvider
→ MyUserDetailsServiceImpl
→ UserRepository (fetch user from DB)
→ PasswordEncoder (validate password)
→ Authentication object returned
→ SecurityContext updated (on success)


Request (username, password)
        |
        v
AuthenticationManager (ProviderManager)
        |
        v
DaoAuthenticationProvider
        |
        v
MyUserDetailsServiceImpl (UserDetailsService)
        |
        v
UserRepository -> Fetch user from DB
        |
        v
UserPrincipal (implements UserDetails)
        |
        v
PasswordEncoder -> Validates password
        |
        v
Authentication Success -> SecurityContext

 */



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
   private UserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return  http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request->request
                        .requestMatchers("api/user/register","api/user/login").permitAll() // permosting some url
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();

        //http.csrf(cust->cust.disable()); this also work
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // THis will accept the plain text
        provider.setPasswordEncoder(passwordEncoder());
        // This will accept the Encripted value -? provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }



    //Setting the default value
    
//    public UserDetailsService userDetailsService(){
//
//        UserDetails user1= User.withDefaultPasswordEncoder()
//                .username("Syed")
//                .password("Syed@124")
//                .roles("Admin").build();
//
//        UserDetails user2 = User.withDefaultPasswordEncoder()
//                .username("Abdul")
//                .password("Abdul@123")
//                .roles("User")
//                .build();
//
//        // UserDetails... mean we add many user by seprating comma
//        /* public InMemoryUserDetailsManager(UserDetails... users) {
//            for(UserDetails user : users) {
//                this.createUser(user);
//            }*/
//
//        return new InMemoryUserDetailsManager(user1,user2);
//
//    }

}
