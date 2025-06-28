package com.example.sl4jloggingexercises;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ParameterizedLoggingExample {
    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggingExample.class);

    public static void main(String[] args) {
        String username = "sreeshanth";
        int age = 20;
        logger.info("User '{}' is {} years old.", username, age);
        logger.debug("Debug info for user: {}, age: {}", username, age);
    }
}
