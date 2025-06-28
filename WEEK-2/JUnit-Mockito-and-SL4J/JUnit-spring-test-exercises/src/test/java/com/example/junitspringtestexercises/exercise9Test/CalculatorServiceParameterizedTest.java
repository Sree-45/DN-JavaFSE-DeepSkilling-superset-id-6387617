package com.example.junitspringtestexercises.exercise9Test;

import com.example.junitspringtestexercises.exercise1.CalculatorService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorServiceParameterizedTest {

    CalculatorService calculatorService = new CalculatorService();

    @ParameterizedTest
    @CsvSource({
        "2, 3, 5",
        "0, 0, 0",
        "-1, 1, 0",
        "100, 200, 300"
    })
    void testAddParameterized(int a, int b, int expected) {
        int result = calculatorService.add(a, b);
        assertEquals(expected, result, () -> a + " + " + b + " should equal " + expected);
    }

    @ParameterizedTest
    @CsvSource({
        "5, 3, 2",
        "0, 0, 0",
        "10, 20, -10",
        "-5, -5, 0"
    })
    void testSubtractParameterized(int a, int b, int expected) {
        int result = calculatorService.subtract(a, b);
        assertEquals(expected, result, () -> a + " - " + b + " should equal " + expected);
    }

    @ParameterizedTest
    @CsvSource({
        "2, 3, 6",
        "0, 5, 0",
        "-1, -1, 1",
        "100, 200, 20000"
    })
    void testMultiplyParameterized(int a, int b, int expected) {
        int result = calculatorService.multiply(a, b);
        assertEquals(expected, result, () -> a + " * " + b + " should equal " + expected);
    }

    @ParameterizedTest
    @CsvSource({
        "6, 3, 2",
        "0, 1, 0",
        "-6, -3, 2",
        "100, 20, 5"
    })
    void testDivideParameterized(int a, int b, int expected) {
        int result = calculatorService.divide(a, b);
        assertEquals(expected, result, () -> a + " / " + b + " should equal " + expected);
    }
}
