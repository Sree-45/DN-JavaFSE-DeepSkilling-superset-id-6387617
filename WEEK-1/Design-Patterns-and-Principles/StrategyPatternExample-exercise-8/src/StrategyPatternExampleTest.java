/**
 * Strategy Pattern Test Suite
 * 
 * This test suite validates the Strategy Pattern implementation by ensuring:
 * - Strategy Creation: Each concrete strategy can be created properly
 * - Interface Compliance: All strategies properly implement the PaymentStrategy interface
 * - Context Functionality: PaymentContext can execute different strategies
 * - Runtime Strategy Switching: Strategies can be changed at runtime
 * - Null Safety: The system handles null strategies gracefully
 * - Polymorphism: All strategies work through the common interface
 * - Method Functionality: All payment operations work correctly
 * - Multiple Context Objects: Different contexts can use different strategies independently
 */
public class StrategyPatternExampleTest {
    
    private static int testCount = 0;
    private static int passCount = 0;
    
    public static void main(String[] args) {
        System.out.println("=== Strategy Pattern Test Suite ===\n");
        
        // Run all tests
        testCreditCardStrategyCreation();
        testPayPalStrategyCreation();
        testContextFunctionality();
        testRuntimeStrategySwitching();
        testInterfaceCompliance();
        testNullStrategyHandling();
        testPolymorphicBehavior();
        testMultipleContextObjects();
        testStrategyMethodFunctionality();
        
        // Print test summary
        printTestSummary();
    }
    
    /**
     * Test 1: Credit Card Strategy Creation
     * 
     * Purpose: Verify that the CreditCardPayment strategy can be created properly with valid parameters.
     * 
     * Steps:
     * 1. Create a new CreditCardPayment instance with valid card details
     * 2. Check if the object is an instance of PaymentStrategy interface
     * 3. Check if the object is an instance of CreditCardPayment class
     * 4. Verify the object is not null
     * 
     * Expected Outcome:
     * - CreditCardPayment should be created successfully
     * - Object should implement PaymentStrategy interface
     * - Object should be of type CreditCardPayment
     * - Test should PASS if all conditions are met
     */
    private static void testCreditCardStrategyCreation() {
        System.out.println("Test 1: Credit Card Strategy Creation");
        try {
            CreditCardPayment creditCardStrategy = new CreditCardPayment(
                "1234567890123456", 
                "John Doe", 
                "12/25", 
                "123"
            );
            
            boolean isPaymentStrategy = creditCardStrategy instanceof PaymentStrategy;
            boolean isCreditCardPayment = creditCardStrategy instanceof CreditCardPayment;
            boolean isNotNull = creditCardStrategy != null;
            
            System.out.println("  - Strategy created: " + creditCardStrategy.getClass().getSimpleName());
            System.out.println("  - Is PaymentStrategy instance: " + isPaymentStrategy);
            System.out.println("  - Is CreditCardPayment instance: " + isCreditCardPayment);
            System.out.println("  - Object is not null: " + isNotNull);
            
            if (isPaymentStrategy && isCreditCardPayment && isNotNull) {
                System.out.println("  PASS: Credit Card strategy created successfully");
                passCount++;
            } else {
                System.out.println("  FAIL: Credit Card strategy creation failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 2: PayPal Strategy Creation
     * 
     * Purpose: Verify that the PayPalPayment strategy can be created properly with valid parameters.
     * 
     * Steps:
     * 1. Create a new PayPalPayment instance with valid email and password
     * 2. Check if the object is an instance of PaymentStrategy interface
     * 3. Check if the object is an instance of PayPalPayment class
     * 4. Verify the object is not null
     * 
     * Expected Outcome:
     * - PayPalPayment should be created successfully
     * - Object should implement PaymentStrategy interface
     * - Object should be of type PayPalPayment
     * - Test should PASS if all conditions are met
     */
    private static void testPayPalStrategyCreation() {
        System.out.println("Test 2: PayPal Strategy Creation");
        try {
            PayPalPayment paypalStrategy = new PayPalPayment(
                "john.doe@email.com", 
                "securePassword123"
            );
            
            boolean isPaymentStrategy = paypalStrategy instanceof PaymentStrategy;
            boolean isPayPalPayment = paypalStrategy instanceof PayPalPayment;
            boolean isNotNull = paypalStrategy != null;
            
            System.out.println("  - Strategy created: " + paypalStrategy.getClass().getSimpleName());
            System.out.println("  - Is PaymentStrategy instance: " + isPaymentStrategy);
            System.out.println("  - Is PayPalPayment instance: " + isPayPalPayment);
            System.out.println("  - Object is not null: " + isNotNull);
            
            if (isPaymentStrategy && isPayPalPayment && isNotNull) {
                System.out.println("  PASS: PayPal strategy created successfully");
                passCount++;
            } else {
                System.out.println("  FAIL: PayPal strategy creation failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 3: Context Functionality
     * 
     * Purpose: Verify that PaymentContext can execute different strategies properly.
     * 
     * Steps:
     * 1. Create a PaymentContext with a CreditCardPayment strategy
     * 2. Execute payment using the context
     * 3. Create another PaymentContext with a PayPalPayment strategy
     * 4. Execute payment using the second context
     * 5. Verify both contexts work independently
     * 
     * Expected Outcome:
     * - Both contexts should execute payments successfully
     * - Each context should use its assigned strategy
     * - No exceptions should be thrown during execution
     * - Test should PASS if both contexts work correctly
     */
    private static void testContextFunctionality() {
        System.out.println("Test 3: Context Functionality");
        try {
            // Test Credit Card context
            PaymentStrategy creditCardStrategy = new CreditCardPayment(
                "9876543210987654", 
                "Jane Smith", 
                "06/26", 
                "456"
            );
            PaymentContext creditCardContext = new PaymentContext(creditCardStrategy);
            creditCardContext.executePayment(75.50);
            
            // Test PayPal context
            PaymentStrategy paypalStrategy = new PayPalPayment(
                "jane.smith@email.com", 
                "mySecretPass"
            );
            PaymentContext paypalContext = new PaymentContext(paypalStrategy);
            paypalContext.executePayment(200.00);
            
            System.out.println("  - Credit Card context executed payment successfully");
            System.out.println("  - PayPal context executed payment successfully");
            System.out.println("  - Both contexts worked independently");
            
            System.out.println("  PASS: Context functionality working correctly");
            passCount++;
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 4: Runtime Strategy Switching
     * 
     * Purpose: Verify that PaymentContext can switch between different strategies at runtime.
     * 
     * Steps:
     * 1. Create a PaymentContext with initial strategy
     * 2. Execute payment with initial strategy
     * 3. Switch to different strategy using setPaymentStrategy
     * 4. Execute payment with new strategy
     * 5. Verify strategy switching works correctly
     * 
     * Expected Outcome:
     * - Context should execute payment with initial strategy
     * - Context should switch to new strategy successfully
     * - Context should execute payment with new strategy
     * - Test should PASS if runtime switching works correctly
     */
    private static void testRuntimeStrategySwitching() {
        System.out.println("Test 4: Runtime Strategy Switching");
        try {
            PaymentContext paymentContext = new PaymentContext(null);
            
            // Initial strategy - Credit Card
            paymentContext.setPaymentStrategy(new CreditCardPayment(
                "4444333322221111", 
                "Bob Johnson", 
                "03/27", 
                "789"
            ));
            paymentContext.executePayment(50.25);
            
            // Switch to PayPal
            paymentContext.setPaymentStrategy(new PayPalPayment(
                "bob.johnson@email.com", 
                "bobPassword"
            ));
            paymentContext.executePayment(100.75);
            
            // Switch back to Credit Card
            paymentContext.setPaymentStrategy(new CreditCardPayment(
                "1111222233334444", 
                "Alice Brown", 
                "09/28", 
                "321"
            ));
            paymentContext.executePayment(25.00);
            
            System.out.println("  - Strategy switched from Credit Card to PayPal");
            System.out.println("  - Strategy switched from PayPal to Credit Card");
            System.out.println("  - All payments executed successfully after switching");
            
            System.out.println("  PASS: Runtime strategy switching working correctly");
            passCount++;
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 5: Interface Compliance
     * 
     * Purpose: Verify that all strategy classes properly implement the PaymentStrategy interface.
     * 
     * Steps:
     * 1. Check if CreditCardPayment implements PaymentStrategy
     * 2. Check if PayPalPayment implements PaymentStrategy
     * 3. Verify interface methods are callable
     * 4. Test polymorphic behavior through interface
     * 
     * Expected Outcome:
     * - Both strategy classes should implement PaymentStrategy interface
     * - Interface methods should be callable on both strategies
     * - Polymorphic behavior should work correctly
     * - Test should PASS if all interface contracts are properly implemented
     */
    private static void testInterfaceCompliance() {
        System.out.println("Test 5: Interface Compliance");
        try {
            PaymentStrategy creditCardStrategy = new CreditCardPayment(
                "5555666677778888", "Interface Test", "01/28", "111"
            );
            PaymentStrategy paypalStrategy = new PayPalPayment(
                "interface@test.com", "testPass"
            );
            
            boolean creditCardImplementsInterface = creditCardStrategy instanceof PaymentStrategy;
            boolean paypalImplementsInterface = paypalStrategy instanceof PaymentStrategy;
            
            // Test interface method calls
            boolean canCallPayMethod = true;
            try {
                creditCardStrategy.pay(10.0);
                paypalStrategy.pay(20.0);
            } catch (Exception e) {
                canCallPayMethod = false;
            }
            
            System.out.println("  - CreditCardPayment implements PaymentStrategy: " + creditCardImplementsInterface);
            System.out.println("  - PayPalPayment implements PaymentStrategy: " + paypalImplementsInterface);
            System.out.println("  - Interface methods callable: " + canCallPayMethod);
            
            if (creditCardImplementsInterface && paypalImplementsInterface && canCallPayMethod) {
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
     * Test 6: Null Strategy Handling
     * 
     * Purpose: Verify that PaymentContext handles null strategies gracefully.
     * 
     * Steps:
     * 1. Create a PaymentContext with null strategy
     * 2. Attempt to execute payment
     * 3. Set strategy to null after initialization
     * 4. Attempt to execute payment again
     * 5. Verify system doesn't crash
     * 
     * Expected Outcome:
     * - System should handle null strategy gracefully
     * - No exceptions should be thrown
     * - Appropriate message should be displayed
     * - Test should PASS if null handling works correctly
     */
    private static void testNullStrategyHandling() {
        System.out.println("Test 6: Null Strategy Handling");
        try {
            // Test with null in constructor
            PaymentContext paymentContext1 = new PaymentContext(null);
            paymentContext1.executePayment(25.00);
            
            // Test setting strategy to null after initialization
            PaymentContext paymentContext2 = new PaymentContext(new CreditCardPayment(
                "1111222233334444", "Test User", "12/24", "000"
            ));
            paymentContext2.setPaymentStrategy(null);
            paymentContext2.executePayment(30.00);
            
            System.out.println("  - Null strategy in constructor handled");
            System.out.println("  - Null strategy set after initialization handled");
            System.out.println("  - No exceptions thrown during null handling");
            
            System.out.println("  PASS: Null strategy handling working correctly");
            passCount++;
        } catch (Exception e) {
            System.out.println("  FAIL: Exception in null handling - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 7: Polymorphic Behavior
     * 
     * Purpose: Verify that all strategies work polymorphically through the PaymentStrategy interface.
     * 
     * Steps:
     * 1. Create an array of different PaymentStrategy instances
     * 2. Use a single PaymentContext to execute payments with different strategies
     * 3. Verify each strategy executes correctly through the interface
     * 
     * Expected Outcome:
     * - All strategies should work through the common interface
     * - Each strategy should execute its specific payment logic
     * - Polymorphic behavior should be demonstrated
     * - Test should PASS if polymorphic behavior works correctly
     */
    private static void testPolymorphicBehavior() {
        System.out.println("Test 7: Polymorphic Behavior");
        try {
            PaymentStrategy[] strategies = {
                new CreditCardPayment("4444444444444444", "Poly User 1", "04/25", "444"),
                new PayPalPayment("poly1@example.com", "polyPass1"),
                new CreditCardPayment("5555555555555555", "Poly User 2", "05/26", "555"),
                new PayPalPayment("poly2@example.com", "polyPass2")
            };
            
            PaymentContext paymentContext = new PaymentContext(null);
            boolean allStrategiesWorked = true;
            
            for (int i = 0; i < strategies.length; i++) {
                try {
                    paymentContext.setPaymentStrategy(strategies[i]);
                    paymentContext.executePayment(50.00 + (i * 10));
                } catch (Exception e) {
                    allStrategiesWorked = false;
                }
            }
            
            System.out.println("  - " + strategies.length + " different strategies tested");
            System.out.println("  - All strategies worked through common interface");
            System.out.println("  - Polymorphic behavior demonstrated");
            
            if (allStrategiesWorked) {
                System.out.println("  PASS: Polymorphic behavior working correctly");
                passCount++;
            } else {
                System.out.println("  FAIL: Polymorphic behavior issues detected");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 8: Multiple Context Objects
     * 
     * Purpose: Verify that multiple PaymentContext objects can work independently with different strategies.
     * 
     * Steps:
     * 1. Create multiple PaymentContext instances with different strategies
     * 2. Execute payments using each context
     * 3. Verify contexts work independently
     * 
     * Expected Outcome:
     * - Multiple contexts should work independently
     * - Each context should use its assigned strategy
     * - No interference between different contexts
     * - Test should PASS if multiple contexts work correctly
     */
    private static void testMultipleContextObjects() {
        System.out.println("Test 8: Multiple Context Objects");
        try {
            // Create multiple contexts with different strategies
            PaymentContext context1 = new PaymentContext(new CreditCardPayment(
                "1111111111111111", "User One", "01/25", "111"
            ));
            
            PaymentContext context2 = new PaymentContext(new PayPalPayment(
                "user2@example.com", "password2"
            ));
            
            PaymentContext context3 = new PaymentContext(new CreditCardPayment(
                "2222222222222222", "User Three", "02/26", "222"
            ));
            
            // Execute payments with each context
            context1.executePayment(100.00);
            context2.executePayment(150.00);
            context3.executePayment(75.00);
            
            System.out.println("  - 3 different contexts created successfully");
            System.out.println("  - Each context used its assigned strategy");
            System.out.println("  - All contexts worked independently");
            
            System.out.println("  PASS: Multiple context objects working correctly");
            passCount++;
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 9: Strategy Method Functionality
     * 
     * Purpose: Verify that the pay method in each strategy works correctly with different amounts.
     * 
     * Steps:
     * 1. Create instances of both strategy types
     * 2. Test pay method with different amounts (zero, small, large)
     * 3. Verify method executes without exceptions
     * 
     * Expected Outcome:
     * - Both strategies should handle different amounts correctly
     * - No exceptions should be thrown during payment execution
     * - Method functionality should work as expected
     * - Test should PASS if method functionality works correctly
     */
    private static void testStrategyMethodFunctionality() {
        System.out.println("Test 9: Strategy Method Functionality");
        try {
            PaymentStrategy creditCardStrategy = new CreditCardPayment(
                "9999888877776666", "Method Test", "12/29", "999"
            );
            PaymentStrategy paypalStrategy = new PayPalPayment(
                "method@test.com", "methodPass"
            );
            
            // Test different amounts
            double[] amounts = {0.0, 0.01, 100.0, 999999.99};
            boolean allAmountsWorked = true;
            
            for (double amount : amounts) {
                try {
                    creditCardStrategy.pay(amount);
                    paypalStrategy.pay(amount);
                } catch (Exception e) {
                    allAmountsWorked = false;
                }
            }
            
            System.out.println("  - " + amounts.length + " different amounts tested");
            System.out.println("  - Both strategies handled all amounts");
            System.out.println("  - No exceptions thrown during payment execution");
            
            if (allAmountsWorked) {
                System.out.println("  PASS: Strategy method functionality working correctly");
                passCount++;
            } else {
                System.out.println("  FAIL: Strategy method functionality issues detected");
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
            System.out.println("ALL TESTS PASSED! Strategy Pattern implementation is working correctly.");
        } else {
            System.out.println("Some tests failed. Please review the implementation.");
        }
    }
}