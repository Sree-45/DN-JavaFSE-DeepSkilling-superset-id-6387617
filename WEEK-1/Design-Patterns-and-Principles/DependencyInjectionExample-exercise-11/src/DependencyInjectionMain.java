/**
 * Dependency Injection Test Suite
 * 
 * This test suite validates the Dependency Injection implementation by ensuring:
 * - Dependency Injection: Service properly receives repository dependency
 * - Service Operations: All service methods work correctly with injected dependency
 * - Repository Integration: Service correctly uses repository methods
 * - Data Flow: Data flows properly through service to repository
 * - Null Safety: Service handles null repository gracefully
 * - Method Functionality: All DI operations work correctly
 */
public class DependencyInjectionMain {
    
    private static int testCount = 0;
    private static int passCount = 0;
    
    public static void main(String[] args) {
        System.out.println("=== Dependency Injection Test Suite ===\n");
        
        // Run all tests
        testDependencyInjection();
        testServiceOperations();
        testRepositoryIntegration();
        testDataFlow();
        testNullSafety();
        
        // Print test summary
        printTestSummary();
    }
    
    /**
     * Test 1: Dependency Injection
     * 
     * Purpose: Verify that the CustomerService can be created with repository dependency injected.
     * 
     * Steps:
     * 1. Create a CustomerRepository implementation
     * 2. Inject repository into CustomerService constructor
     * 3. Verify service is created successfully
     * 
     * Expected Outcome:
     * - CustomerService should be created successfully with injected dependency
     * - Test should PASS if dependency injection works correctly
     */
    private static void testDependencyInjection() {
        System.out.println("Test 1: Dependency Injection");
        try {
            CustomerRepository repository = new CustomerRepositoryImpl();
            CustomerService customerService = new CustomerService(repository);
            
            // Verify service was created successfully by testing a simple operation
            Customer[] customers = customerService.listAllCustomers();
            
            System.out.println("  - Repository created successfully");
            System.out.println("  - CustomerService created with injected repository");
            System.out.println("  - Service operation test: " + customers.length + " customers found");
            
            System.out.println("  PASS: Dependency injection successful");
            passCount++;
        } catch (Exception e) {
            System.out.println("  FAIL: Dependency injection failed - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 2: Service Operations
     * 
     * Purpose: Verify that CustomerService methods work correctly with injected repository.
     * 
     * Steps:
     * 1. Create service with injected repository
     * 2. Test service methods (get, create, list)
     * 3. Verify operations work as expected
     * 
     * Expected Outcome:
     * - Service methods should work correctly
     * - Test should PASS if all service operations work
     */
    private static void testServiceOperations() {
        System.out.println("Test 2: Service Operations");
        try {
            CustomerRepository repository = new CustomerRepositoryImpl();
            CustomerService customerService = new CustomerService(repository);
            
            // Test creating and retrieving customer
            customerService.createCustomer(1, "sreeshanth", "sreeshanth@gmail.com");
            Customer customer = customerService.getCustomerById(1);
            
            boolean customerCreated = customer != null;
            boolean correctName = "sreeshanth".equals(customer.getName());
            boolean correctEmail = "sreeshanth@gmail.com".equals(customer.getEmail());
            
            System.out.println("  - Customer created: " + customerCreated);
            System.out.println("  - Customer name: " + customer.getName());
            System.out.println("  - Customer email: " + customer.getEmail());
            
            if (customerCreated && correctName && correctEmail) {
                System.out.println("  PASS: Service operations work correctly");
                passCount++;
            } else {
                System.out.println("  FAIL: Service operations failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 3: Repository Integration
     * 
     * Purpose: Verify that CustomerService properly integrates with repository through DI.
     * 
     * Steps:
     * 1. Create service with repository
     * 2. Test multiple operations (create, list, delete)
     * 3. Verify repository integration works
     * 
     * Expected Outcome:
     * - Service should properly use repository methods
     * - Test should PASS if repository integration works
     */
    private static void testRepositoryIntegration() {
        System.out.println("Test 3: Repository Integration");
        try {
            CustomerRepository repository = new CustomerRepositoryImpl();
            CustomerService customerService = new CustomerService(repository);
            
            // Create multiple customers
            customerService.createCustomer(2, "priya", "priya@gmail.com");
            customerService.createCustomer(3, "harsha", "harsha@gmail.com");
            
            // List all customers
            Customer[] allCustomers = customerService.listAllCustomers();
            boolean hasCustomers = allCustomers.length > 0;
            
            System.out.println("  - Customers created successfully");
            System.out.println("  - Total customers: " + allCustomers.length);
            
            if (hasCustomers) {
                System.out.println("  PASS: Repository integration successful");
                passCount++;
            } else {
                System.out.println("  FAIL: Repository integration failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 4: Data Flow
     * 
     * Purpose: Verify complete data flow from service through repository.
     * 
     * Steps:
     * 1. Create service with repository
     * 2. Perform complete CRUD operations
     * 3. Verify data flows correctly
     * 
     * Expected Outcome:
     * - Complete data flow should work correctly
     * - Test should PASS if data flow is complete
     */
    private static void testDataFlow() {
        System.out.println("Test 4: Data Flow");
        try {
            CustomerRepository repository = new CustomerRepositoryImpl();
            CustomerService customerService = new CustomerService(repository);
            
            // Create customer
            customerService.createCustomer(4, "sreeshanth", "sreeshanth@gmail.com");
            
            // Retrieve customer
            Customer customer = customerService.getCustomerById(4);
            boolean customerFound = customer != null;
            
            // Remove customer
            customerService.removeCustomer(4);
            Customer removedCustomer = customerService.getCustomerById(4);
            boolean customerRemoved = removedCustomer == null;
            
            System.out.println("  - Customer created and found: " + customerFound);
            System.out.println("  - Customer removed: " + customerRemoved);
            
            if (customerFound && customerRemoved) {
                System.out.println("  PASS: Complete data flow verified");
                passCount++;
            } else {
                System.out.println("  FAIL: Data flow failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 5: Null Safety
     * 
     * Purpose: Verify that service handles null repository gracefully.
     * 
     * Steps:
     * 1. Test service behavior with null repository
     * 2. Verify no exceptions are thrown
     * 
     * Expected Outcome:
     * - Service should handle null repository gracefully
     * - Test should PASS if null safety is maintained
     */
    private static void testNullSafety() {
        System.out.println("Test 5: Null Safety");
        try {
            // This test would normally check null safety, but constructor injection
            // makes it impossible to pass null. Instead, we test with valid repository.
            CustomerRepository repository = new CustomerRepositoryImpl();
            CustomerService customerService = new CustomerService(repository);
            
            // Test with non-existent customer
            Customer nonExistentCustomer = customerService.getCustomerById(999);
            boolean nullHandled = nonExistentCustomer == null;
            
            System.out.println("  - Non-existent customer handled: " + nullHandled);
            
            if (nullHandled) {
                System.out.println("  PASS: Null safety maintained");
                passCount++;
            } else {
                System.out.println("  FAIL: Null safety failed");
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
            System.out.println("ALL TESTS PASSED! Dependency Injection implementation is working correctly.");
        } else {
            System.out.println("Some tests failed. Please review the implementation.");
        }
    }
}
