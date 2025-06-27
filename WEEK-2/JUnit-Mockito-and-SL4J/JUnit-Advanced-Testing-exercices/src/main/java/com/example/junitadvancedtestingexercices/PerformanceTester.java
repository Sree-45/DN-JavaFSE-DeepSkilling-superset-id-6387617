package com.example.junitadvancedtestingexercices;

public class PerformanceTester {
    public void performTask() {
        // Simulating a task (e.g., sleep for demonstration)
        try {
            Thread.sleep(200); // 200 milliseconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}