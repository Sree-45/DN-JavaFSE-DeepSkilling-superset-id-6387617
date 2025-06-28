package com.example.junitadvancedtestingexercices;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    EvenCheckerTest.class,
    CalculatorTest.class
})
public class AllTests {
}