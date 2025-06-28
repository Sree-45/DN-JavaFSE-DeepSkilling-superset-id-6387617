package com.example.junitbasictestingexercises;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class AdvancedCalculatorTest {

    private AdvancedCalculator advCalc;

    @BeforeEach
    void setUp() {
        // Arrange: Create a new AdvancedCalculator before each test
        advCalc = new AdvancedCalculator();
    }

    @AfterEach
    void tearDown() {
        // Teardown: Nullify the AdvancedCalculator after each test
        advCalc = null;
    }

    @Test
    void testPower() {
        // Act
        double result = advCalc.power(2, 3);
        // Assert
        assertEquals(8.0, result);
    }

    @Test
    void testSqrt() {
        // Act
        double result = advCalc.sqrt(16);
        // Assert
        assertEquals(4.0, result);
    }

    @Test
    void testSqrtNegative() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> advCalc.sqrt(-1));
        assertEquals("Cannot take sqrt of negative number.", exception.getMessage());
    }

    @Test
    void testLog() {
        // Act
        double result = advCalc.log(Math.E);
        // Assert
        assertEquals(1.0, result, 0.0001);
    }

    @Test
    void testLogZeroOrNegative() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> advCalc.log(0));
        assertEquals("Logarithm undefined for zero or negative.", exception.getMessage());
    }

    @Test
    void testSin() {
        // Act
        double result = advCalc.sin(Math.PI / 2);
        // Assert
        assertEquals(1.0, result, 0.0001);
    }

    @Test
    void testCos() {
        // Act
        double result = advCalc.cos(0);
        // Assert
        assertEquals(1.0, result, 0.0001);
    }
}