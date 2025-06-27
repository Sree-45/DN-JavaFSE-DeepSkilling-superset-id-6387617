package com.example.junitadvancedtestingexercices;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class EvenCheckerTest {

    private final EvenChecker evenChecker = new EvenChecker();

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10, 0, -2, -4})
    void testIsEvenWithEvenNumbers(int number) {
        assertTrue(evenChecker.isEven(number), number + " should be even");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 9, -1, -3})
    void testIsEvenWithOddNumbers(int number) {
        assertFalse(evenChecker.isEven(number), number + " should be odd");
    }
}