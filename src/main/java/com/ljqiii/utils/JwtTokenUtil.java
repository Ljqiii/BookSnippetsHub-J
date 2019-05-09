package com.ljqiii.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtTokenUtil {


    static long EXPIRE_TIME = 7200;
    static String JWT_SECERT = "secert";

    static Algorithm JWT_ALGORITHM = Algorithm.HMAC256(JWT_SECERT);

    public static String generateToken(String openid) {
        long now = System.currentTimeMillis();
        Date date = new Date(now + EXPIRE_TIME);
        String token = JWT.create()
                .withClaim("openid", openid)
                .withExpiresAt(date)
                .sign(JWT_ALGORITHM);
        return token;

    }

    public static String VerifyToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(JWT_ALGORITHM).build();
        try {
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            String openid = decodedJWT.getClaim("openid").asString();
            return openid;
        } catch (TokenExpiredException e) {
            return null;
        }
        catch (SignatureVerificationException e){
            return null;
        }


    }
}
