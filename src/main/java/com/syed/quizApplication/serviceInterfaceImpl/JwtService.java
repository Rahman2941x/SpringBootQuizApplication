package com.syed.quizApplication.serviceInterfaceImpl;

import com.syed.quizApplication.serviceInterface.JwtServiceInterface;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService implements JwtServiceInterface {


    @Override
    public String generateToken(String username) {


        Map<String, Object> claim = new HashMap<>();


        return Jwts
                .builder()
                .claims()
                .add(claim)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .and()
                .signWith(getkey())
                .compact();
    }



    private String secretKey;

    public JwtService(){
        try{
        KeyGenerator keygen=KeyGenerator.getInstance("HmacSHA256");
       SecretKey sk =keygen.generateKey();
       secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private SecretKey getkey() {
        byte[] keybit= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keybit);
    }



    @Override
    public String extractUsername(String token) {
        return extractClaim(token , Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimResolver) {
        final Claims claims =extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getkey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName=extractUsername(token);
        System.out.println(userName + "---> User name");
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
         return extraxtExpiration(token).before(new Date());
    }

    private Date extraxtExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

}




