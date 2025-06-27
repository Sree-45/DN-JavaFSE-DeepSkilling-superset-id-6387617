package com.example.junitadvancedtestingexercices;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionThrowerTest {

    @Test
    void testThrowException() {
        ExceptionThrower thrower = new ExceptionThrower();
        assertThrows(IllegalArgumentException.class, thrower::throwException, "Expected IllegalArgumentException to be thrown");
    }
}