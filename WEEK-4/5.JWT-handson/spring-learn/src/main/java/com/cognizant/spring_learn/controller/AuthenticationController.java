package com.cognizant.spring_learn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Base64;
import java.util.Date;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthenticationController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
    
    @GetMapping("/authenticate")
    public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
        LOGGER.info("START");
        LOGGER.debug("Authorization header: {}", authHeader);
        
        String username = getUser(authHeader);
        LOGGER.debug("Extracted username: {}", username);
        
        String token = generateJwt(username);
        LOGGER.debug("Generated token: {}", token);
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", token);
        
        LOGGER.info("END");
        return map;
    }
    
    private String getUser(String authHeader) {
        LOGGER.debug("START - getUser method");
        LOGGER.debug("Input authHeader: {}", authHeader);
        
        try {
            String encodedCredentials = authHeader.substring(6);
            LOGGER.debug("Encoded credentials: {}", encodedCredentials);
            
            byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
            String decodedCredentials = new String(decodedBytes);
            LOGGER.debug("Decoded credentials: {}", decodedCredentials);
            
            String username = decodedCredentials.substring(0, decodedCredentials.indexOf(':'));
            LOGGER.debug("Extracted username: {}", username);
            
            LOGGER.debug("END - getUser method");
            return username;
            
        } catch (Exception e) {
            LOGGER.error("Error decoding Authorization header: {}", e.getMessage());
            return null;
        }
    }
    
    private String generateJwt(String user) {
        LOGGER.debug("START - generateJwt method");
        LOGGER.debug("Generating JWT for user: {}", user);
        
        JwtBuilder builder = Jwts.builder();
        builder.setSubject(user);

        builder.setIssuedAt(new Date());
        LOGGER.debug("Token issued at: {}", new Date());

        builder.setExpiration(new Date((new Date()).getTime() + 1200000));
        LOGGER.debug("Token expires at: {}", new Date((new Date()).getTime() + 1200000));
        
        builder.signWith(SignatureAlgorithm.HS256, "secretkey");
        LOGGER.debug("Token signed with HS256 algorithm");

        String token = builder.compact();
        LOGGER.debug("Generated token: {}", token);
        
        LOGGER.debug("END - generateJwt method");
        return token;
    }
} 