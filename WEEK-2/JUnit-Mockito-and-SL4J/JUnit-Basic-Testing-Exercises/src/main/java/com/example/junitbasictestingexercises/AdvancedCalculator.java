package com.example.junitbasictestingexercises;

public class AdvancedCalculator {
    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public double sqrt(double value) {
        if (value < 0) throw new IllegalArgumentException("Cannot take sqrt of negative number.");
        return Math.sqrt(value);
    }

    public double log(double value) {
        if (value <= 0) throw new IllegalArgumentException("Logarithm undefined for zero or negative.");
        return Math.log(value);
    }

    public double sin(double radians) {
        return Math.sin(radians);
    }

    public double cos(double radians) {
        return Math.cos(radians);
    }
}