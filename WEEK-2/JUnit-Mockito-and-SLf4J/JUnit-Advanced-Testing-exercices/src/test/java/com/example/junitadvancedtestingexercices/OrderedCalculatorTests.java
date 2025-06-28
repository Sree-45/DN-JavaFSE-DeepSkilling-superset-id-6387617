package com.example.junitadvancedtestingexercices;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
public class OrderedCalculatorTests {

    private final Calculator calculator = new Calculator();

    @Test
    @Order(4)
    void testAdd() {
        System.out.println("Running testAdd");
        assertEquals(5, calculator.add(2, 3));
        assertEquals(0, calculator.add(-2, 2));
    }

    @Test
    @Order(3)
    void testSubtract() {
        System.out.println("Running testSubtract");
        assertEquals(1, calculator.subtract(3, 2));
        assertEquals(-4, calculator.subtract(-2, 2));
    }

    @Test
    @Order(2)
    void testMultiply() {
        System.out.println("Running testMultiply");
        assertEquals(6, calculator.multiply(2, 3));
        assertEquals(0, calculator.multiply(0, 5));
    }

    @Test
    @Order(1)
    void testDivide() {
        System.out.println("Running testDivide");
        assertEquals(2, calculator.divide(6, 3));
        assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0), "Division by zero is not allowed");
    }
}