package com.example.junitspringtestexercises.exercise1Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.junitspringtestexercises.exercise1.CalculatorService;

public class CalculatorServiceTest {
    CalculatorService calculatorService = new CalculatorService();


    @Test
    void testAdd() {
        int result = calculatorService.add(2, 3);
        assertEquals(5, result, "2 + 3 should equal 5");
    }

    @Test
    void testSubtract() {
        int result = calculatorService.subtract(5, 3);
        assertEquals(2, result, "5 - 3 should equal 2");
    }
    @Test
    void testMultiply() {
        int result = calculatorService.multiply(2, 3);
        assertEquals(6, result, "2 * 3 should equal 6");
    }
    @Test
    void testDivide() {
        int result = calculatorService.divide(6, 3);
        assertEquals(2, result, "6 / 3 should equal 2");
    }    
}
