package com.example.junitadvancedtestingexercices;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void testAdd() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    void testSubtract() {
        assertEquals(1, calculator.subtract(3, 2));
    }
    @Test
    void testMultiply() {
        assertEquals(6, calculator.multiply(2, 3));
    }
    @Test
    void testDivide() {
        assertEquals(2, calculator.divide(6, 3));
        assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0), "Division by zero is not allowed");
    }
}