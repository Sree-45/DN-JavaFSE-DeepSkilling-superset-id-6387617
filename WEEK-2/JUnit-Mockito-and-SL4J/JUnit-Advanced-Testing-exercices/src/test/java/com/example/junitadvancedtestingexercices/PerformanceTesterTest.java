package com.example.junitadvancedtestingexercices;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;

public class PerformanceTesterTest {

    @Test
    void testPerformTaskCompletesWithinTimeout() {
        PerformanceTester tester = new PerformanceTester();
        assertTimeoutPreemptively(
            Duration.ofMillis(300), // Set your timeout here
            tester::performTask,
            "Task exceeded the time limit!"
        );
    }
}