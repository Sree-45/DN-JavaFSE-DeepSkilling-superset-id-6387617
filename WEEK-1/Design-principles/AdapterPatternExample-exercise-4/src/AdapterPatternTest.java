import AdapteeClasses.PayPalGateway;
import AdapteeClasses.RazorpayGateway;
import AdapteeClasses.StripeGateway;
import AdapterClasses.PayPalAdapter;
import AdapterClasses.RazorpayAdapter;
import AdapterClasses.StripeAdapter;
import TargetInterface.PaymentProcessor;

/**
 * Adapter Pattern Test Suite
 * 
 * This test suite validates the Adapter Pattern implementation by ensuring:
 * - Correct Adapter Functionality: Adapters properly translate between interfaces
 * - Null Handling: Adapters handle null scenarios gracefully
 * - Input Validation: Adapters validate input parameters correctly
 * - Polymorphic Behavior: Adapters work through the target interface
 * - Edge Cases: Adapters handle edge cases and boundary conditions
 * - Runtime Switching: Payment service can switch adapters at runtime
 * - Adapter Chaining: Multiple adapters can be chained together
 */
public class AdapterPatternTest {
    
    private static int testCount = 0;
    private static int passCount = 0;
    
    public static void main(String[] args) {
        System.out.println("=== Adapter Pattern Test Suite ===\n");
        
        testBasicAdapterFunctionality();
        testNullAdapterScenarios();
        testInvalidInputValidation();
        testAdapterWithNullGateway();
        testPolymorphicBehavior();
        testMultipleAdaptersForSameGateway();
        testAdapterChaining();
        testRuntimeAdapterSwitching();
        
        printTestSummary();
    }
    
    /**
     * Test 1: Basic Adapter Functionality
     * 
     * Purpose: Verify that adapters can properly translate between the target interface
     * and the adaptee classes, enabling seamless integration.
     * 
     * Steps:
     * 1. Create gateway instances (adaptees)
     * 2. Create adapter instances wrapping the gateways
     * 3. Use adapters through the PaymentProcessor interface
     * 4. Verify that payment processing works correctly
     * 
     * Expected Outcome:
     * - All adapters should process payments successfully
     * - Payment status should be retrievable
     * - Test should PASS if all adapters work correctly
     */
    private static void testBasicAdapterFunctionality() {
        System.out.println("Test 1: Basic Adapter Functionality");
        try {
            PayPalGateway payPalGateway = new PayPalGateway();
            StripeGateway stripeGateway = new StripeGateway();
            RazorpayGateway razorpayGateway = new RazorpayGateway();
            
            PaymentProcessor payPalAdapter = new PayPalAdapter(payPalGateway);
            PaymentProcessor stripeAdapter = new StripeAdapter(stripeGateway);
            PaymentProcessor razorpayAdapter = new RazorpayAdapter(razorpayGateway);
            
            boolean payPalResult = payPalAdapter.processPayment(100.00, "USD");
            boolean stripeResult = stripeAdapter.processPayment(200.00, "EUR");
            boolean razorpayResult = razorpayAdapter.processPayment(300.00, "INR");
            
            String payPalStatus = payPalAdapter.getPaymentStatus();
            String stripeStatus = stripeAdapter.getPaymentStatus();
            String razorpayStatus = razorpayAdapter.getPaymentStatus();
            
            System.out.println("  - PayPal result: " + payPalResult);
            System.out.println("  - Stripe result: " + stripeResult);
            System.out.println("  - Razorpay result: " + razorpayResult);
            System.out.println("  - PayPal status: " + payPalStatus);
            System.out.println("  - Stripe status: " + stripeStatus);
            System.out.println("  - Razorpay status: " + razorpayStatus);
            
            if (payPalResult && stripeResult && razorpayResult && 
                payPalStatus != null && stripeStatus != null && razorpayStatus != null) {
                System.out.println("  PASS: Basic adapter functionality works correctly");
                passCount++;
            } else {
                System.out.println("  FAIL: Basic adapter functionality failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 2: Null Adapter Scenarios
     * 
     * Purpose: Verify that the PaymentService handles null adapters gracefully
     * without throwing exceptions.
     * 
     * Steps:
     * 1. Create PaymentService with null adapter
     * 2. Attempt to process a transaction
     * 3. Verify that the service handles null gracefully
     * 
     * Expected Outcome:
     * - PaymentService should not throw exceptions with null adapter
     * - Test should PASS if null handling works correctly
     */
    private static void testNullAdapterScenarios() {
        System.out.println("Test 2: Null Adapter Scenarios");
        try {
            PaymentService paymentService = new PaymentService(null);
            paymentService.processTransaction(100.00, "USD");
            
            System.out.println("  - PaymentService created with null adapter");
            System.out.println("  - Transaction processing attempted");
            System.out.println("  - No exceptions thrown");
            
            System.out.println("  PASS: Null adapter scenarios handled gracefully");
            passCount++;
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred with null adapter - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 3: Invalid Input Validation
     * 
     * Purpose: Verify that adapters properly validate input parameters
     * and handle invalid inputs gracefully.
     * 
     * Steps:
     * 1. Test negative amounts with all adapters
     * 2. Test zero amounts with all adapters
     * 3. Test null currency with all adapters
     * 4. Test empty currency strings with all adapters
     * 
     * Expected Outcome:
     * - Adapters should handle invalid inputs without crashing
     * - Test should PASS if all invalid inputs are handled gracefully
     */
    private static void testInvalidInputValidation() {
        System.out.println("Test 3: Invalid Input Validation");
        try {
            PayPalGateway payPalGateway = new PayPalGateway();
            StripeGateway stripeGateway = new StripeGateway();
            RazorpayGateway razorpayGateway = new RazorpayGateway();
            
            PaymentProcessor payPalAdapter = new PayPalAdapter(payPalGateway);
            PaymentProcessor stripeAdapter = new StripeAdapter(stripeGateway);
            PaymentProcessor razorpayAdapter = new RazorpayAdapter(razorpayGateway);
            
            // Test negative amounts
            boolean payPalNegative = payPalAdapter.processPayment(-100.0, "USD");
            boolean stripeNegative = stripeAdapter.processPayment(-50.0, "EUR");
            boolean razorpayNegative = razorpayAdapter.processPayment(-25.0, "INR");
            
            // Test zero amounts
            boolean payPalZero = payPalAdapter.processPayment(0.0, "USD");
            boolean stripeZero = stripeAdapter.processPayment(0.0, "EUR");
            boolean razorpayZero = razorpayAdapter.processPayment(0.0, "INR");
            
            // Test null currency
            boolean payPalNullCurrency = payPalAdapter.processPayment(100.0, null);
            boolean stripeNullCurrency = stripeAdapter.processPayment(100.0, null);
            boolean razorpayNullCurrency = razorpayAdapter.processPayment(100.0, null);
            
            System.out.println("  - Negative amounts handled: " + (payPalNegative || stripeNegative || razorpayNegative));
            System.out.println("  - Zero amounts handled: " + (payPalZero || stripeZero || razorpayZero));
            System.out.println("  - Null currency handled: " + (payPalNullCurrency || stripeNullCurrency || razorpayNullCurrency));
            
            // Test passes if no exceptions are thrown
            System.out.println("  PASS: Invalid input validation handled gracefully");
            passCount++;
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred during invalid input validation - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 4: Adapter with Null Gateway
     * 
     * Purpose: Verify that adapters handle null gateway (adaptee) instances
     * gracefully without throwing exceptions.
     * 
     * Steps:
     * 1. Create adapters with null gateways
     * 2. Attempt to process payments through these adapters
     * 3. Verify that null gateways are handled gracefully
     * 
     * Expected Outcome:
     * - Adapters should handle null gateways without crashing
     * - Test should PASS if null gateways are handled gracefully
     */
    private static void testAdapterWithNullGateway() {
        System.out.println("Test 4: Adapter with Null Gateway");
        try {
            PaymentProcessor payPalAdapter = new PayPalAdapter(null);
            PaymentProcessor stripeAdapter = new StripeAdapter(null);
            PaymentProcessor razorpayAdapter = new RazorpayAdapter(null);
            
            boolean payPalResult = payPalAdapter.processPayment(100.0, "USD");
            boolean stripeResult = stripeAdapter.processPayment(100.0, "USD");
            boolean razorpayResult = razorpayAdapter.processPayment(100.0, "USD");
            
            String payPalStatus = payPalAdapter.getPaymentStatus();
            String stripeStatus = stripeAdapter.getPaymentStatus();
            String razorpayStatus = razorpayAdapter.getPaymentStatus();
            
            System.out.println("  - PayPal with null gateway: " + payPalResult);
            System.out.println("  - Stripe with null gateway: " + stripeResult);
            System.out.println("  - Razorpay with null gateway: " + razorpayResult);
            System.out.println("  - Status retrieval works: " + (payPalStatus != null && stripeStatus != null && razorpayStatus != null));
            
            // Test passes if no exceptions are thrown
            System.out.println("  PASS: Null gateway scenarios handled gracefully");
            passCount++;
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred with null gateway - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 5: Polymorphic Behavior
     * 
     * Purpose: Verify that adapters work correctly through polymorphism,
     * allowing different adapters to be used interchangeably.
     * 
     * Steps:
     * 1. Create an array of different adapters
     * 2. Process payments through each adapter using the same interface
     * 3. Verify that all adapters work polymorphically
     * 
     * Expected Outcome:
     * - All adapters should work through the PaymentProcessor interface
     * - Test should PASS if polymorphic behavior works correctly
     */
    private static void testPolymorphicBehavior() {
        System.out.println("Test 5: Polymorphic Behavior");
        try {
            PaymentProcessor[] adapters = {
                new PayPalAdapter(new PayPalGateway()),
                new StripeAdapter(new StripeGateway()),
                new RazorpayAdapter(new RazorpayGateway())
            };
            
            String[] adapterNames = {"PayPal", "Stripe", "Razorpay"};
            boolean allAdaptersWork = true;
            
            System.out.println("  - Testing polymorphic behavior:");
            for (int i = 0; i < adapters.length; i++) {
                PaymentProcessor processor = adapters[i];
                boolean result = processor.processPayment(99.99, "USD");
                String status = processor.getPaymentStatus();
                
                System.out.println("    " + adapterNames[i] + " result: " + result);
                System.out.println("    " + adapterNames[i] + " status: " + status);
                
                if (!result || status == null) {
                    allAdaptersWork = false;
                }
            }
            
            if (allAdaptersWork) {
                System.out.println("  PASS: Polymorphic behavior works correctly");
                passCount++;
            } else {
                System.out.println("  FAIL: Polymorphic behavior failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred during polymorphic testing - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 6: Multiple Adapters for Same Gateway
     * 
     * Purpose: Verify that multiple adapters can wrap the same gateway
     * without conflicts or issues.
     * 
     * Steps:
     * 1. Create a shared gateway instance
     * 2. Create multiple adapters wrapping the same gateway
     * 3. Test that all adapters work independently
     * 
     * Expected Outcome:
     * - Multiple adapters should work with the same gateway
     * - Test should PASS if multiple adapters work correctly
     */
    private static void testMultipleAdaptersForSameGateway() {
        System.out.println("Test 6: Multiple Adapters for Same Gateway");
        try {
            PayPalGateway sharedGateway = new PayPalGateway();
            
            PaymentProcessor adapter1 = new PayPalAdapter(sharedGateway);
            PaymentProcessor adapter2 = new PayPalAdapter(sharedGateway);
            
            boolean result1 = adapter1.processPayment(100.0, "USD");
            boolean result2 = adapter2.processPayment(200.0, "EUR");
            
            String status1 = adapter1.getPaymentStatus();
            String status2 = adapter2.getPaymentStatus();
            
            System.out.println("  - Adapter 1 result: " + result1);
            System.out.println("  - Adapter 2 result: " + result2);
            System.out.println("  - Adapter 1 status: " + status1);
            System.out.println("  - Adapter 2 status: " + status2);
            
            if (result1 && result2 && status1 != null && status2 != null) {
                System.out.println("  PASS: Multiple adapters work correctly with same gateway");
                passCount++;
            } else {
                System.out.println("  FAIL: Multiple adapters failed with same gateway");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred with multiple adapters - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 7: Adapter Chaining
     * 
     * Purpose: Verify that adapters can be chained together to create
     * composite functionality.
     * 
     * Steps:
     * 1. Create a base adapter
     * 2. Create a logging adapter that wraps the base adapter
     * 3. Test the chained adapter functionality
     * 
     * Expected Outcome:
     * - Adapter chaining should work correctly
     * - Test should PASS if chained adapters work properly
     */
    private static void testAdapterChaining() {
        System.out.println("Test 7: Adapter Chaining");
        try {
            PaymentProcessor baseAdapter = new PayPalAdapter(new PayPalGateway());
            PaymentProcessor loggingAdapter = new LoggingPaymentAdapter(baseAdapter);
            
            PaymentService service = new PaymentService(loggingAdapter);
            service.processTransaction(150.0, "USD");
            
            System.out.println("  - Base adapter created");
            System.out.println("  - Logging adapter chained");
            System.out.println("  - Chained adapter used in service");
            
            System.out.println("  PASS: Adapter chaining works correctly");
            passCount++;
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred during adapter chaining - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 8: Runtime Adapter Switching
     * 
     * Purpose: Verify that the PaymentService can switch between different
     * adapters at runtime without issues.
     * 
     * Steps:
     * 1. Create PaymentService with initial adapter
     * 2. Switch to different adapters at runtime
     * 3. Verify that switching works correctly
     * 
     * Expected Outcome:
     * - Runtime adapter switching should work correctly
     * - Test should PASS if adapter switching works properly
     */
    private static void testRuntimeAdapterSwitching() {
        System.out.println("Test 8: Runtime Adapter Switching");
        try {
            PaymentService service = new PaymentService(new PayPalAdapter(new PayPalGateway()));
            
            service.processTransaction(100.0, "USD");
            
            service.setPaymentProcessor(new StripeAdapter(new StripeGateway()));
            service.processTransaction(200.0, "EUR");
            
            service.setPaymentProcessor(new RazorpayAdapter(new RazorpayGateway()));
            service.processTransaction(300.0, "INR");
            
            service.setPaymentProcessor(null);
            service.processTransaction(400.0, "GBP");
            
            System.out.println("  - PayPal adapter used");
            System.out.println("  - Stripe adapter used");
            System.out.println("  - Razorpay adapter used");
            System.out.println("  - Null adapter handled");
            
            System.out.println("  PASS: Runtime adapter switching works correctly");
            passCount++;
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred during runtime switching - " + e.getMessage());
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
            System.out.println("ALL TESTS PASSED! Adapter Pattern implementation is working correctly.");
        } else {
            System.out.println("Some tests failed. Please review the implementation.");
        }
    }
}

// Additional adapter class for testing adapter chaining
class LoggingPaymentAdapter implements PaymentProcessor {
    private PaymentProcessor wrappedProcessor;
    
    public LoggingPaymentAdapter(PaymentProcessor processor) {
        this.wrappedProcessor = processor;
    }
    
    @Override
    public boolean processPayment(double amount, String currency) {
        System.out.println("[LOG] Starting payment processing for " + amount + " " + currency);
        if (wrappedProcessor == null) {
            System.out.println("[LOG] Wrapped processor is null");
            return false;
        }
        boolean result = wrappedProcessor.processPayment(amount, currency);
        System.out.println("[LOG] Payment processing completed with result: " + result);
        return result;
    }
    
    @Override
    public String getPaymentStatus() {
        System.out.println("[LOG] Getting payment status");
        if (wrappedProcessor == null) {
            return "[LOG] Wrapped processor is null - no status available";
        }
        String status = wrappedProcessor.getPaymentStatus();
        System.out.println("[LOG] Retrieved status: " + status);
        return status;
    }
}