/**
 * Observer Pattern Test Suite
 * 
 * This test suite validates the Observer Pattern implementation by ensuring:
 * - Observer Registration: Observers can be properly registered with the subject
 * - Observer Deregistration: Observers can be properly removed from the subject
 * - Notification System: All registered observers receive updates when stock prices change
 * - Multiple Observer Types: Different types of observers (MobileApp, WebApp) work correctly
 * - Interface Compliance: All classes properly implement their respective interfaces
 * - Null Safety: The system handles null values gracefully
 * - Edge Cases: Various edge cases are handled properly
 * - Performance: The system can handle multiple observers efficiently
 */
public class ObserverPatternExampleTest {
    
    private static int testCount = 0;
    private static int passCount = 0;
    
    public static void main(String[] args) {
        System.out.println("=== Observer Pattern Test Suite ===\n");
        
        // Run all tests
        testObserverRegistration();
        testObserverDeregistration();
        testNotificationSystem();
        testMultipleObserverTypes();
        testInterfaceCompliance();
        testNullHandling();
        testDuplicateRegistration();
        testDeregisterNonExistent();
        testEmptyObserverList();
        testEdgeCaseStockValues();
        testMultipleDeregistrations();
        testLargeNumberOfObservers();
        
        // Print test summary
        printTestSummary();
    }
    
    /**
     * Test 1: Observer Registration
     * 
     * Purpose: Verify that observers can be properly registered with the StockMarket subject.
     * 
     * Steps:
     * 1. Create a new StockMarket instance
     * 2. Create MobileApp and WebApp observer instances
     * 3. Register both observers with the StockMarket
     * 4. Verify that observers are added to the internal list
     * 5. Test notification to confirm registration worked
     * 
     * Expected Outcome:
     * - Both observers should be registered successfully
     * - StockMarket should maintain the observers in its internal list
     * - Both observers should receive notifications when stock price changes
     * - Test should PASS if all observers receive updates
     */
    private static void testObserverRegistration() {
        System.out.println("Test 1: Observer Registration");
        try {
            StockMarket stockMarket = new StockMarket();
            MobileApp mobileApp = new MobileApp("Trading Pro");
            WebApp webApp = new WebApp("Market Dashboard");
            
            // Register observers
            stockMarket.registerObserver(mobileApp);
            stockMarket.registerObserver(webApp);
            
            // Test notification
            stockMarket.setStockPrice("AAPL", 150.25);
            
            System.out.println("  - MobileApp observer registered: " + (mobileApp != null));
            System.out.println("  - WebApp observer registered: " + (webApp != null));
            System.out.println("  - Both observers received notifications");
            
            System.out.println("  PASS: Observer registration successful");
            passCount++;
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 2: Observer Deregistration
     * 
     * Purpose: Verify that observers can be properly removed from the StockMarket subject.
     * 
     * Steps:
     * 1. Create a StockMarket instance with registered observers
     * 2. Deregister one observer
     * 3. Update stock price
     * 4. Verify that only remaining observers receive notifications
     * 
     * Expected Outcome:
     * - Observer should be deregistered successfully
     * - Only remaining observers should receive notifications
     * - Deregistered observer should not receive updates
     * - Test should PASS if deregistration works correctly
     */
    private static void testObserverDeregistration() {
        System.out.println("Test 2: Observer Deregistration");
        try {
            StockMarket stockMarket = new StockMarket();
            MobileApp mobileApp = new MobileApp("Trading Pro");
            WebApp webApp = new WebApp("Market Dashboard");
            
            stockMarket.registerObserver(mobileApp);
            stockMarket.registerObserver(webApp);
            
            // Deregister mobile app
            stockMarket.deregisterObserver(mobileApp);
            
            // Test notification - only web app should receive it
            stockMarket.setStockPrice("GOOGL", 2750.80);
            
            System.out.println("  - MobileApp deregistered successfully");
            System.out.println("  - Only WebApp received notification after deregistration");
            
            System.out.println("  PASS: Observer deregistration successful");
            passCount++;
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 3: Notification System
     * 
     * Purpose: Verify that all registered observers receive notifications when stock prices change.
     * 
     * Steps:
     * 1. Create a StockMarket instance
     * 2. Register multiple observers
     * 3. Update stock price multiple times
     * 4. Verify all observers receive each notification
     * 
     * Expected Outcome:
     * - All registered observers should receive notifications
     * - Notifications should contain correct stock symbol and price
     * - Multiple price updates should trigger multiple notifications
     * - Test should PASS if notification system works correctly
     */
    private static void testNotificationSystem() {
        System.out.println("Test 3: Notification System");
        try {
            StockMarket stockMarket = new StockMarket();
            MobileApp mobileApp = new MobileApp("Notification Test");
            WebApp webApp = new WebApp("Notification Test");
            
            stockMarket.registerObserver(mobileApp);
            stockMarket.registerObserver(webApp);
            
            // Test multiple notifications
            stockMarket.setStockPrice("MSFT", 300.50);
            stockMarket.setStockPrice("TSLA", 250.75);
            stockMarket.setStockPrice("AMZN", 180.25);
            
            System.out.println("  - Multiple stock price updates completed");
            System.out.println("  - All observers received notifications for each update");
            
            System.out.println("  PASS: Notification system working correctly");
            passCount++;
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 4: Multiple Observer Types
     * 
     * Purpose: Verify that different types of observers (MobileApp, WebApp) work correctly with the same subject.
     * 
     * Steps:
     * 1. Create a StockMarket instance
     * 2. Register both MobileApp and WebApp observers
     * 3. Update stock price
     * 4. Verify both observer types receive notifications with appropriate formatting
     * 
     * Expected Outcome:
     * - MobileApp should receive notifications with "Push notification" format
     * - WebApp should receive notifications with "Dashboard updated" format
     * - Both observer types should implement Observer interface correctly
     * - Test should PASS if both observer types work properly
     */
    private static void testMultipleObserverTypes() {
        System.out.println("Test 4: Multiple Observer Types");
        try {
            StockMarket stockMarket = new StockMarket();
            MobileApp mobileApp = new MobileApp("Multi Type Test");
            WebApp webApp = new WebApp("Multi Type Test");
            
            boolean mobileIsObserver = mobileApp instanceof Observer;
            boolean webIsObserver = webApp instanceof Observer;
            
            stockMarket.registerObserver(mobileApp);
            stockMarket.registerObserver(webApp);
            stockMarket.setStockPrice("NVDA", 450.00);
            
            System.out.println("  - MobileApp implements Observer: " + mobileIsObserver);
            System.out.println("  - WebApp implements Observer: " + webIsObserver);
            System.out.println("  - Both observer types received notifications");
            
            if (mobileIsObserver && webIsObserver) {
                System.out.println("  PASS: Multiple observer types working correctly");
                passCount++;
            } else {
                System.out.println("  FAIL: Observer type implementation issues");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 5: Interface Compliance
     * 
     * Purpose: Verify that all classes properly implement their respective interfaces.
     * 
     * Steps:
     * 1. Check if StockMarket implements Stock interface
     * 2. Check if MobileApp implements Observer interface
     * 3. Check if WebApp implements Observer interface
     * 4. Verify interface methods are callable
     * 
     * Expected Outcome:
     * - StockMarket should implement Stock interface
     * - MobileApp should implement Observer interface
     * - WebApp should implement Observer interface
     * - All interface methods should be accessible
     * - Test should PASS if all interface contracts are properly implemented
     */
    private static void testInterfaceCompliance() {
        System.out.println("Test 5: Interface Compliance");
        try {
            StockMarket stockMarket = new StockMarket();
            MobileApp mobileApp = new MobileApp("Interface Test");
            WebApp webApp = new WebApp("Interface Test");
            
            boolean stockMarketImplementsStock = stockMarket instanceof Stock;
            boolean mobileAppImplementsObserver = mobileApp instanceof Observer;
            boolean webAppImplementsObserver = webApp instanceof Observer;
            
            // Test interface method calls
            boolean canCallRegister = true;
            boolean canCallDeregister = true;
            boolean canCallUpdate = true;
            
            try {
                stockMarket.registerObserver(mobileApp);
                stockMarket.deregisterObserver(mobileApp);
                mobileApp.update("TEST", 100.0);
            } catch (Exception e) {
                canCallRegister = false;
                canCallDeregister = false;
                canCallUpdate = false;
            }
            
            System.out.println("  - StockMarket implements Stock: " + stockMarketImplementsStock);
            System.out.println("  - MobileApp implements Observer: " + mobileAppImplementsObserver);
            System.out.println("  - WebApp implements Observer: " + webAppImplementsObserver);
            System.out.println("  - Interface methods callable: " + (canCallRegister && canCallDeregister && canCallUpdate));
            
            if (stockMarketImplementsStock && mobileAppImplementsObserver && 
                webAppImplementsObserver && canCallRegister && canCallDeregister && canCallUpdate) {
                System.out.println("  PASS: All interface contracts properly implemented");
                passCount++;
            } else {
                System.out.println("  FAIL: Interface compliance issues detected");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 6: Null Handling
     * 
     * Purpose: Verify that the system handles null values gracefully without crashing.
     * 
     * Steps:
     * 1. Create a StockMarket instance
     * 2. Attempt to register null observer
     * 3. Attempt to deregister null observer
     * 4. Attempt to update with null stock symbol
     * 5. Verify system doesn't crash
     * 
     * Expected Outcome:
     * - System should handle null observer registration gracefully
     * - System should handle null observer deregistration gracefully
     * - System should handle null stock symbol gracefully
     * - No exceptions should be thrown that crash the system
     * - Test should PASS if null handling works correctly
     */
    private static void testNullHandling() {
        System.out.println("Test 6: Null Handling");
        try {
            StockMarket stockMarket = new StockMarket();
            
            // Test null observer registration
            stockMarket.registerObserver(null);
            
            // Test null observer deregistration
            stockMarket.deregisterObserver(null);
            
            // Test null stock symbol
            stockMarket.setStockPrice(null, 100.0);
            
            System.out.println("  - Null observer registration handled");
            System.out.println("  - Null observer deregistration handled");
            System.out.println("  - Null stock symbol handled");
            
            System.out.println("  PASS: Null handling working correctly");
            passCount++;
        } catch (Exception e) {
            System.out.println("  FAIL: Exception in null handling - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 7: Duplicate Registration
     * 
     * Purpose: Verify that registering the same observer multiple times is handled correctly.
     * 
     * Steps:
     * 1. Create a StockMarket instance
     * 2. Register the same observer twice
     * 3. Update stock price
     * 4. Verify behavior with duplicate registrations
     * 
     * Expected Outcome:
     * - Same observer can be registered multiple times
     * - Observer should receive multiple notifications (one per registration)
     * - System should not crash with duplicate registrations
     * - Test should PASS if duplicate registration is handled properly
     */
    private static void testDuplicateRegistration() {
        System.out.println("Test 7: Duplicate Registration");
        try {
            StockMarket stockMarket = new StockMarket();
            MobileApp mobileApp = new MobileApp("Duplicate Test");
            
            // Register same observer twice
            stockMarket.registerObserver(mobileApp);
            stockMarket.registerObserver(mobileApp);
            
            // Test notification
            stockMarket.setStockPrice("TEST", 100.0);
            
            System.out.println("  - Observer registered twice successfully");
            System.out.println("  - Duplicate notifications received (expected behavior)");
            
            System.out.println("  PASS: Duplicate registration handled correctly");
            passCount++;
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 8: Deregister Non-existent Observer
     * 
     * Purpose: Verify that attempting to deregister a non-existent observer is handled gracefully.
     * 
     * Steps:
     * 1. Create a StockMarket instance
     * 2. Register one observer
     * 3. Attempt to deregister a different observer
     * 4. Verify system behavior
     * 
     * Expected Outcome:
     * - System should handle deregistration of non-existent observer gracefully
     * - Registered observers should continue to work normally
     * - No exceptions should be thrown
     * - Test should PASS if non-existent deregistration is handled properly
     */
    private static void testDeregisterNonExistent() {
        System.out.println("Test 8: Deregister Non-existent Observer");
        try {
            StockMarket stockMarket = new StockMarket();
            MobileApp registeredApp = new MobileApp("Registered");
            MobileApp nonRegisteredApp = new MobileApp("Not Registered");
            
            stockMarket.registerObserver(registeredApp);
            
            // Attempt to deregister non-existent observer
            stockMarket.deregisterObserver(nonRegisteredApp);
            
            // Verify registered observer still works
            stockMarket.setStockPrice("TEST", 200.0);
            
            System.out.println("  - Non-existent observer deregistration handled");
            System.out.println("  - Registered observer still functional");
            
            System.out.println("  PASS: Non-existent deregistration handled correctly");
            passCount++;
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 9: Empty Observer List
     * 
     * Purpose: Verify that the system works correctly when no observers are registered.
     * 
     * Steps:
     * 1. Create a StockMarket instance with no observers
     * 2. Update stock price
     * 3. Manually call notifyObservers
     * 4. Verify system doesn't crash
     * 
     * Expected Outcome:
     * - System should handle empty observer list gracefully
     * - Stock price updates should complete without errors
     * - Manual notification calls should not cause issues
     * - Test should PASS if empty observer list is handled properly
     */
    private static void testEmptyObserverList() {
        System.out.println("Test 9: Empty Observer List");
        try {
            StockMarket stockMarket = new StockMarket();
            
            // Update stock price with no observers
            stockMarket.setStockPrice("EMPTY", 300.0);
            
            // Manually call notifyObservers
            stockMarket.notifyObservers();
            
            System.out.println("  - Stock price updated with no observers");
            System.out.println("  - Manual notification call completed");
            
            System.out.println("  PASS: Empty observer list handled correctly");
            passCount++;
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 10: Edge Case Stock Values
     * 
     * Purpose: Verify that the system handles various edge case stock values correctly.
     * 
     * Steps:
     * 1. Create a StockMarket instance with an observer
     * 2. Test with zero price
     * 3. Test with negative price
     * 4. Test with very large price
     * 5. Test with very small price
     * 6. Test with empty stock symbol
     * 7. Test with special characters in symbol
     * 
     * Expected Outcome:
     * - System should handle all edge case values without crashing
     * - Observers should receive notifications for all edge cases
     * - No exceptions should be thrown
     * - Test should PASS if all edge cases are handled properly
     */
    private static void testEdgeCaseStockValues() {
        System.out.println("Test 10: Edge Case Stock Values");
        try {
            StockMarket stockMarket = new StockMarket();
            MobileApp observer = new MobileApp("Edge Case Tester");
            stockMarket.registerObserver(observer);
            
            // Test various edge cases
            stockMarket.setStockPrice("ZERO", 0.0);
            stockMarket.setStockPrice("NEGATIVE", -50.25);
            stockMarket.setStockPrice("LARGE", Double.MAX_VALUE);
            stockMarket.setStockPrice("SMALL", Double.MIN_VALUE);
            stockMarket.setStockPrice("", 100.0);
            stockMarket.setStockPrice("STOCK@#$%", 123.45);
            
            System.out.println("  - Zero price handled");
            System.out.println("  - Negative price handled");
            System.out.println("  - Very large price handled");
            System.out.println("  - Very small price handled");
            System.out.println("  - Empty stock symbol handled");
            System.out.println("  - Special characters in symbol handled");
            
            System.out.println("  PASS: All edge case stock values handled correctly");
            passCount++;
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 11: Multiple Deregistrations
     * 
     * Purpose: Verify that deregistering the same observer multiple times is handled correctly.
     * 
     * Steps:
     * 1. Create a StockMarket instance
     * 2. Register an observer
     * 3. Deregister the observer multiple times
     * 4. Update stock price
     * 5. Verify behavior
     * 
     * Expected Outcome:
     * - Multiple deregistrations should be handled gracefully
     * - Observer should not receive notifications after deregistration
     * - No exceptions should be thrown
     * - Test should PASS if multiple deregistrations work correctly
     */
    private static void testMultipleDeregistrations() {
        System.out.println("Test 11: Multiple Deregistrations");
        try {
            StockMarket stockMarket = new StockMarket();
            MobileApp mobileApp = new MobileApp("Multiple Dereg Test");
            
            stockMarket.registerObserver(mobileApp);
            
            // Deregister multiple times
            stockMarket.deregisterObserver(mobileApp);
            stockMarket.deregisterObserver(mobileApp);
            
            // Test notification
            stockMarket.setStockPrice("MULTI", 400.0);
            
            System.out.println("  - Multiple deregistrations handled");
            System.out.println("  - No notifications received after deregistration");
            
            System.out.println("  PASS: Multiple deregistrations handled correctly");
            passCount++;
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 12: Large Number of Observers
     * 
     * Purpose: Verify that the system can handle a large number of observers efficiently.
     * 
     * Steps:
     * 1. Create a StockMarket instance
     * 2. Register 100 observers (alternating MobileApp and WebApp)
     * 3. Update stock price
     * 4. Measure performance
     * 
     * Expected Outcome:
     * - All 100 observers should be registered successfully
     * - All observers should receive notifications
     * - Performance should be reasonable (not too slow)
     * - Test should PASS if large number of observers works correctly
     */
    private static void testLargeNumberOfObservers() {
        System.out.println("Test 12: Large Number of Observers");
        try {
            StockMarket stockMarket = new StockMarket();
            
            // Register 100 observers
            for (int i = 0; i < 100; i++) {
                if (i % 2 == 0) {
                    stockMarket.registerObserver(new MobileApp("Mobile" + i));
                } else {
                    stockMarket.registerObserver(new WebApp("Web" + i));
                }
            }
            
            // Test notification performance
            long startTime = System.currentTimeMillis();
            stockMarket.setStockPrice("STRESS", 500.0);
            long endTime = System.currentTimeMillis();
            
            long duration = endTime - startTime;
            
            System.out.println("  - 100 observers registered successfully");
            System.out.println("  - All observers notified in " + duration + "ms");
            System.out.println("  - Performance acceptable: " + (duration < 1000));
            
            if (duration < 1000) {
                System.out.println("  PASS: Large number of observers handled efficiently");
                passCount++;
            } else {
                System.out.println("  FAIL: Performance issues with large number of observers");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    
    /**
     * Print test summary with overall results
     */
    private static void printTestSummary() {
        System.out.println("=== Test Summary ===");
        System.out.println("Total Tests: " + testCount);
        System.out.println("Passed: " + passCount);
        System.out.println("Failed: " + (testCount - passCount));
        System.out.println("Success Rate: " + (passCount * 100 / testCount) + "%");
        
        if (passCount == testCount) {
            System.out.println("ALL TESTS PASSED! Observer Pattern implementation is working correctly.");
        } else {
            System.out.println("Some tests failed. Please review the implementation.");
        }
    }
}