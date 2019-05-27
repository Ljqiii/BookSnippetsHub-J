package com.ljqiii.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class JwtTokenService {

    @Value("${jwt.expiretime}")
    long EXPIRE_TIME;


    Algorithm JWT_ALGORITHM;


    public JwtTokenService(@Value("${jwt.secert}") String JWT_SECERT){
        JWT_ALGORITHM = Algorithm.HMAC256(JWT_SECERT);
    }

    public String generateToken(String openid) {

        long now = System.currentTimeMillis();
        Date date = new Date(now + EXPIRE_TIME);

        System.out.println("EXPIRE_TIME" + EXPIRE_TIME);
        System.out.println("now date" + new Date(now).toString());
        System.out.println("expire date" + date.toString());

        String token = JWT.create()
                .withClaim("id", openid)
//                .withExpiresAt(date)
                .sign(JWT_ALGORITHM);
        return token;
    }

    public Integer VerifyToken(String token) {
        System.out.println("token: "+token);

        JWTVerifier jwtVerifier = JWT.require(JWT_ALGORITHM).build();
        try {
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return Integer.valueOf(decodedJWT.getClaim("id").asString());
        } catch (TokenExpiredException e) {
            throw new TokenExpiredException("TokenExpiredException");
        } catch (SignatureVerificationException e) {
            throw new SignatureVerificationException(JWT_ALGORITHM);
        }
    }
}
