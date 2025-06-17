// ComprehensiveSingletonTest.java

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Comprehensive test class to validate ALL edge cases and scenarios 
 * for the Logger Singleton implementation
 */
public class SingletonTest {
    
    private static final int THREAD_COUNT = 100;
    private static final int ITERATIONS_PER_THREAD = 1000;
    
    public static void main(String[] args) {
        System.out.println("=== COMPREHENSIVE SINGLETON PATTERN TESTING ===\n");
        
        // Run all test scenarios
        testBasicSingletonBehavior();
        testHashCodeConsistency();
        testEqualsMethod();
        testToStringConsistency();
        testInstanceComparison();
        testMultipleReferences();
        testThreadSafety();
        testConcurrentAccess();
        testMemoryEfficiency();
        testSingletonIntegrity();
        testFunctionalityConsistency();
        testReflectionProtection();
        
        System.out.println("\n=== ALL SINGLETON TESTS COMPLETED ===");
    }
    
    /**
     * Test 1: Basic Singleton Behavior
     * 
     * This test verifies the fundamental requirement of the Singleton pattern:
     * that multiple calls to getInstance() return the exact same object instance.
     * 
     * Test Steps:
     * 1. Call getInstance() three times and store in different variables
     * 2. Compare all instances using reference equality (==)
     * 3. Display identity hash codes to visually confirm same object
     * 4. Assert that all instances are identical
     * 
     * Expected Result:
     * - All three variables should reference the same object in memory
     * - Identity hash codes should be identical
     * - Reference equality checks should all return true
     * 
     * This is the most basic and critical test for any Singleton implementation.
     */
    private static void testBasicSingletonBehavior() {
        System.out.println("Test 1: Basic Singleton Behavior");
        System.out.println("=" .repeat(50));
        
        // Get three instances of the Logger singleton
        Logger instance1 = Logger.getInstance();
        Logger instance2 = Logger.getInstance();
        Logger instance3 = Logger.getInstance();
        
        // Check if all instances are the same object using reference equality
        boolean sameInstance = (instance1 == instance2) && (instance2 == instance3);
        
        // Display test results
        System.out.println("Same instance check: " + sameInstance);
        System.out.println("Instance1 ID: " + System.identityHashCode(instance1));
        System.out.println("Instance2 ID: " + System.identityHashCode(instance2));
        System.out.println("Instance3 ID: " + System.identityHashCode(instance3));
        
        // Assert that singleton pattern is working correctly
        assert sameInstance : "FAIL: Different instances created!";
        System.out.println("PASS: Basic singleton behavior verified\n");
    }
    
    /**
     * Test 2: HashCode Consistency
     * 
     * This test verifies that the hashCode() method returns consistent values
     * across multiple calls and different references to the same singleton instance.
     * 
     * Test Steps:
     * 1. Get two references to the same singleton instance
     * 2. Call hashCode() on both references multiple times
     * 3. Compare all hash code values for consistency
     * 4. Verify that hash codes remain stable across calls
     * 
     * Expected Result:
     * - All hash code calls should return the same integer value
     * - This demonstrates that the object's identity is consistent
     * 
     */
    private static void testHashCodeConsistency() {
        System.out.println("Test 2: HashCode Consistency");
        System.out.println("=" .repeat(50));
        
        // Get two references to the same singleton instance
        Logger instance1 = Logger.getInstance();
        Logger instance2 = Logger.getInstance();
        
        // Call hashCode() multiple times to test consistency
        int hash1 = instance1.hashCode();
        int hash2 = instance2.hashCode();
        int hash3 = instance1.hashCode(); // Second call to same instance
        
        // Display all hash code values for verification
        System.out.println("HashCode 1: " + hash1);
        System.out.println("HashCode 2: " + hash2);
        System.out.println("HashCode 3 (same as 1): " + hash3);
        
        // Verify all hash codes are identical
        boolean hashConsistent = (hash1 == hash2) && (hash1 == hash3);
        
        // Assert that hash codes are consistent across all calls
        assert hashConsistent : "FAIL: HashCode inconsistent!";
        System.out.println("PASS: HashCode consistency verified\n");
    }
    
    /**
     * Test 3: Equals Method Testing
     * 
     * This test verifies that the equals() method behaves correctly for singleton instances.
     * Since all singleton references point to the same object, they should be equal to each other.
     * 
     * Test Steps:
     * 1. Get two references to the same singleton instance
     * 2. Test reflexive property: instance.equals(instance) should be true
     * 3. Test symmetric property: a.equals(b) should equal b.equals(a)
     * 4. Test null handling: instance.equals(null) should be false
     * 5. Verify all equality conditions are met
     * 
     * Expected Results:
     * - Two singleton references should be equal to each other
     * - An instance should equal itself (reflexive)
     * - Equality should be symmetric (a.equals(b) == b.equals(a))
     * - No instance should equal null
     * 
     */
    private static void testEqualsMethod() {
        System.out.println("Test 3: Equals Method Testing");
        System.out.println("=" .repeat(50));
        
        // Get two references to the same singleton instance
        Logger instance1 = Logger.getInstance();
        Logger instance2 = Logger.getInstance();
        
        // Test various equality scenarios
        boolean equals1 = instance1.equals(instance2);  // Symmetric test 1
        boolean equals2 = instance2.equals(instance1);  // Symmetric test 2
        boolean equalsNull = instance1.equals(null);    // Null handling
        boolean equalsSelf = instance1.equals(instance1); // Reflexive test
        
        // Display all equality test results
        System.out.println("instance1.equals(instance2): " + equals1);
        System.out.println("instance2.equals(instance1): " + equals2);
        System.out.println("instance1.equals(null): " + equalsNull);
        System.out.println("instance1.equals(instance1): " + equalsSelf);
        
        // Verify all equality properties are satisfied
        assert equals1 && equals2 : "FAIL: Equals method inconsistent!";
        assert !equalsNull : "FAIL: Should not equal null!";
        assert equalsSelf : "FAIL: Should equal itself!";
        System.out.println("PASS: Equals method verified\n");
    }
    
    /**
     * Test 4: ToString Consistency
     * 
     * This test verifies that the toString() method returns consistent string representations
     * across different references to the same singleton instance. Since all references point
     * to the same object, their string representations should be identical.
     * 
     * Test Steps:
     * 1. Get two references to the same singleton instance
     * 2. Call toString() on both references
     * 3. Compare the string representations for equality
     * 4. Verify that both toString() calls return identical strings
     * 
     * Expected Results:
     * - Both toString() calls should return exactly the same string
     * - This demonstrates that the object's string representation is consistent
     * - Important for debugging, logging, and object identification
     * 
     */
    private static void testToStringConsistency() {
        System.out.println("Test 4: ToString Consistency");
        System.out.println("=" .repeat(50));
        
        // Get two references to the same singleton instance
        Logger instance1 = Logger.getInstance();
        Logger instance2 = Logger.getInstance();
        
        // Get string representations of both references
        String str1 = instance1.toString();
        String str2 = instance2.toString();
        
        // Display and compare the string representations
        System.out.println("ToString 1: " + str1);
        System.out.println("ToString 2: " + str2);
        System.out.println("Same toString: " + str1.equals(str2));
        
        // Verify that both toString() calls return identical strings
        assert str1.equals(str2) : "FAIL: ToString inconsistent!";
        System.out.println("PASS: ToString consistency verified\n");
    }
    
    /**
     * Test 5: Instance Comparison Edge Cases
     * 
     * This test performs an exhaustive comparison of multiple singleton instances to ensure
     * that the singleton pattern holds true even under stress conditions with many references.
     * It creates an array of 10 references and compares each one against every other.
     * 
     * Test Steps:
     * 1. Create an array to hold 10 Logger references
     * 2. Call getInstance() 10 times and store each reference
     * 3. Perform pairwise comparison of all references using reference equality (==)
     * 4. Verify that every reference points to the exact same object
     * 5. Display identity hash codes to confirm same memory location
     * 
     * Expected Results:
     * - All 10 references should point to the same object in memory
     * - All pairwise comparisons should return true
     * - Identity hash codes should be identical for all references
     * - No exceptions or inconsistencies should occur
     * 
     */
    private static void testInstanceComparison() {
        System.out.println("Test 5: Instance Comparison Edge Cases");
        System.out.println("=" .repeat(50));
        
        // Create array to hold multiple Logger references
        Logger[] instances = new Logger[10];
        
        // Get 10 instances and store them in the array
        for (int i = 0; i < 10; i++) {
            instances[i] = Logger.getInstance();
        }
        
        // Perform exhaustive pairwise comparison of all instances
        boolean allSame = true;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (instances[i] != instances[j]) {
                    allSame = false;
                    break;
                }
            }
        }
        
        // Display results and verify singleton integrity
        System.out.println("All 10 instances are same: " + allSame);
        System.out.println("First instance ID: " + System.identityHashCode(instances[0]));
        System.out.println("Last instance ID: " + System.identityHashCode(instances[9]));
        System.out.println(Arrays.toString(instances));        
        // Assert that all references point to the same singleton instance
        assert allSame : "FAIL: Not all instances are the same!";
        System.out.println("PASS: Instance comparison verified\n");
    }
    
    /**
     * Test 6: Multiple References Test
     * 
     * This test verifies that multiple reference variables with different names all point
     * to the same singleton instance. This simulates real-world scenarios where different
     * parts of an application might use different variable names to access the logger.
     * 
     * Test Steps:
     * 1. Create four different reference variables with descriptive names
     * 2. Call getInstance() for each reference variable
     * 3. Compare all references using reference equality (==)
     * 4. Display identity hash codes to confirm same memory location
     * 5. Verify that all references point to the exact same object
     * 
     * Expected Results:
     * - All four reference variables should point to the same object in memory
     * - All pairwise comparisons should return true
     * - Identity hash codes should be identical for all references
     * - Variable names don't affect the singleton behavior
     * 
     */
    private static void testMultipleReferences() {
        System.out.println("Test 6: Multiple References Test");
        System.out.println("=" .repeat(50));
        
        // Create different reference variables with descriptive names
        Logger log = Logger.getInstance();
        Logger logger = Logger.getInstance();
        Logger loggerInstance = Logger.getInstance();
        Logger singletonLogger = Logger.getInstance();
        
        // Test all references point to same object using chained comparison
        boolean allSame = (log == logger) && 
                         (logger == loggerInstance) && 
                         (loggerInstance == singletonLogger);
        
        // Display results showing all references have same identity
        System.out.println("All references same: " + allSame);
        System.out.println("Reference 1 ID: " + System.identityHashCode(log));
        System.out.println("Reference 2 ID: " + System.identityHashCode(logger));
        System.out.println("Reference 3 ID: " + System.identityHashCode(loggerInstance));
        System.out.println("Reference 4 ID: " + System.identityHashCode(singletonLogger));
        
        // Assert that all reference variables point to the same singleton instance
        assert allSame : "FAIL: References don't point to same object!";
        System.out.println("PASS: Multiple references verified\n");
    }
    
    /**
     * Test 7: Thread Safety Basic Test
     * 
     * This test verifies that the singleton pattern is thread-safe by creating multiple
     * threads that simultaneously call getInstance(). In a multi-threaded environment,
     * the singleton must ensure that only one instance is created even when accessed
     * concurrently from different threads.
     * 
     * Test Steps:
     * 1. Create a thread-safe Set to collect Logger instances from different threads
     * 2. Create a CountDownLatch to synchronize thread completion
     * 3. Spawn 10 threads, each calling Logger.getInstance()
     * 4. Each thread adds its Logger instance to the synchronized Set
     * 5. Wait for all threads to complete using the latch
     * 6. Count unique instances in the Set (should be exactly 1)
     * 
     * Expected Results:
     * - Only one unique Logger instance should be created across all threads
     * - The Set should contain exactly one element
     * - All threads should receive the same instance reference
     * - No race conditions or multiple instance creation should occur
     */
    private static void testThreadSafety() {
        System.out.println("Test 7: Thread Safety Basic Test");
        System.out.println("=" .repeat(50));
        
        // Thread-safe collection to store instances from different threads
        final Set<Logger> instances = new HashSet<>();
        // Synchronization mechanism to wait for all threads to complete
        final CountDownLatch latch = new CountDownLatch(10);
        
        // Create 10 threads that simultaneously call getInstance()
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                // Each thread gets its own Logger instance
                Logger instance = Logger.getInstance();
                // Thread-safe addition to the Set
                synchronized (instances) {
                    instances.add(instance);
                }
                // Signal that this thread has completed
                latch.countDown();
            }).start();
        }
        
        // Wait for all threads to complete their execution
        try {
            latch.await(); // Wait for all threads to complete
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted: " + e.getMessage());
        }
        
        // Count unique instances (should be exactly 1 for singleton)
        int uniqueInstances = instances.size();
        System.out.println("Unique instances created: " + uniqueInstances);
        System.out.println("Expected: 1, Actual: " + uniqueInstances);
        
        // Verify that only one instance was created across all threads
        assert uniqueInstances == 1 : "FAIL: Multiple instances in threads!";
        System.out.println("PASS: Thread safety verified\n");
    }
    
    /**
     * Test 8: Concurrent Access Stress Test
     * 
     * This test performs an intensive stress test of the singleton pattern under extreme
     * concurrent conditions. It creates a large number of threads (100) that each perform
     * multiple iterations (1000) of calling getInstance(), simulating high-frequency
     * concurrent access patterns that might occur in production environments.
     * 
     * Test Steps:
     * 1. Create thread-safe collections for tracking instances and thread completion
     * 2. Set up two CountDownLatches for synchronized start and completion
     * 3. Create a thread pool with THREAD_COUNT (100) threads
     * 4. Each thread waits for start signal, then performs ITERATIONS_PER_THREAD (1000) calls
     * 5. Collect identity hash codes of all instances to verify uniqueness
     * 6. Wait for all threads to complete and verify results
     * 
     * Expected Results:
     * - Only one unique Logger instance should be created across all operations
     * - All threads should complete successfully (100,000 total operations)
     * - No race conditions, deadlocks, or multiple instance creation
     * - Performance should remain stable under high concurrent load
     * 
     */
    private static void testConcurrentAccess() {
        System.out.println("Test 8: Concurrent Access Stress Test");
        System.out.println("=" .repeat(50));
        
        // Thread-safe collections for tracking test results
        final Set<Integer> instanceHashCodes = new HashSet<>();
        final AtomicInteger completedThreads = new AtomicInteger(0);
        // Synchronization mechanisms for coordinated thread execution
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch endLatch = new CountDownLatch(THREAD_COUNT);
        
        // Create thread pool for managing concurrent execution
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        
        // Submit tasks to all threads in the pool
        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                try {
                    startLatch.await(); // Wait for all threads to be ready
                    
                    // Perform multiple getInstance() calls per thread
                    for (int j = 0; j < ITERATIONS_PER_THREAD; j++) {
                        Logger instance = Logger.getInstance();
                        // Thread-safe collection of instance hash codes
                        synchronized (instanceHashCodes) {
                            instanceHashCodes.add(System.identityHashCode(instance));
                        }
                    }
                    
                    completedThreads.incrementAndGet();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    endLatch.countDown();
                }
            });
        }
        
        startLatch.countDown(); // Start all threads simultaneously
        
        // Wait for all threads to complete their execution
        try {
            endLatch.await(); // Wait for all threads to complete
        } catch (InterruptedException e) {
            System.err.println("Interrupted: " + e.getMessage());
        }
        
        executor.shutdown();
        
        // Calculate and display test results
        int uniqueInstances = instanceHashCodes.size();
        int totalOperations = THREAD_COUNT * ITERATIONS_PER_THREAD;
        
        System.out.println("Total threads: " + THREAD_COUNT);
        System.out.println("Iterations per thread: " + ITERATIONS_PER_THREAD);
        System.out.println("Total operations: " + totalOperations);
        System.out.println("Completed threads: " + completedThreads.get());
        System.out.println("Unique instances: " + uniqueInstances);
        
        // Verify singleton integrity under extreme concurrent stress
        assert uniqueInstances == 1 : "FAIL: Multiple instances under stress!";
        assert completedThreads.get() == THREAD_COUNT : "FAIL: Not all threads completed!";
        System.out.println("PASS: Concurrent access stress test verified\n");
    }
    
    /**
     * Test 9: Memory Efficiency Test
     * 
     * This test verifies that the singleton pattern is memory-efficient by creating a large
     * number of references (1000) and ensuring they all point to the same object instance.
     * It also monitors memory usage to demonstrate that multiple references don't consume
     * additional memory for the actual Logger object.
     * 
     * Test Steps:
     * 1. Create an ArrayList to hold 1000 Logger references
     * 2. Call getInstance() 1000 times and store each reference
     * 3. Verify that all references point to the same object using reference equality
     * 4. Monitor JVM memory usage before and after reference creation
     * 5. Display memory statistics and object identity information
     * 
     * Expected Results:
     * - All 1000 references should point to the same Logger instance
     * - Memory usage should remain minimal despite many references
     * - Only one Logger object should exist in memory
     * - Reference creation should not impact memory significantly
     * 
     */
    private static void testMemoryEfficiency() {
        System.out.println("Test 9: Memory Efficiency Test");
        System.out.println("=" .repeat(50));
        
        // Collection to hold multiple Logger references
        List<Logger> references = new ArrayList<>();
        
        // Create 1000 references to test memory efficiency
        for (int i = 0; i < 1000; i++) {
            references.add(Logger.getInstance());
        }
        
        // Verify all references point to the same singleton instance
        Logger firstInstance = references.get(0);
        boolean allSameObject = references.stream()
                .allMatch(instance -> instance == firstInstance);
        
        // Monitor JVM memory usage to demonstrate efficiency
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        
        // Display test results and memory statistics
        System.out.println("Created 1000 references");
        System.out.println("All point to same object: " + allSameObject);
        System.out.println("Used memory: " + usedMemory + " bytes");
        System.out.println("Single instance ID: " + System.identityHashCode(firstInstance));
        
        // Verify memory efficiency by ensuring all references point to same object
        assert allSameObject : "FAIL: References don't point to same object!";
        System.out.println("PASS: Memory efficiency verified\n");
    }
    
    /**
     * Test 10: Singleton Integrity Test
     * 
     * This test verifies the long-term integrity and persistence of the singleton instance
     * by making multiple consecutive calls to getInstance() and ensuring the same object
     * is returned consistently. This validates that the singleton pattern maintains its
     * integrity over time and multiple access patterns.
     * 
     * Test Steps:
     * 1. Get a base Logger instance as the reference point
     * 2. Perform 100 consecutive calls to getInstance()
     * 3. Compare each returned instance with the base instance using reference equality
     * 4. Track if any instance differs from the base instance
     * 5. Display persistence status and base instance identity
     * 
     * Expected Results:
     * - All 100 calls should return the exact same object instance
     * - No variation or inconsistency in returned instances
     * - The singleton should maintain integrity across multiple access cycles
     * - Base instance identity should remain constant throughout the test
     * 
     */
    private static void testSingletonIntegrity() {
        System.out.println("Test 10: Singleton Integrity Test");
        System.out.println("=" .repeat(50));
        
        // Test instance persistence across multiple calls
        boolean persistent = true;
        // Get base instance as reference for comparison
        Logger baseInstance = Logger.getInstance();
        
        // Perform 100 consecutive getInstance() calls to test persistence
        for (int i = 0; i < 100; i++) {
            Logger currentInstance = Logger.getInstance();
            // Check if current instance differs from base instance
            if (currentInstance != baseInstance) {
                persistent = false;
                break;
            }
        }
        
        // Display test results showing persistence verification
        System.out.println("Instance persistent across 100 calls: " + persistent);
        System.out.println("Base instance: " + System.identityHashCode(baseInstance));
        
        // Verify that singleton maintains integrity across multiple accesses
        assert persistent : "FAIL: Instance not persistent!";
        System.out.println("PASS: Singleton integrity verified\n");
    }
    
    /**
     * Test 11: Functionality Consistency Test
     * 
     * This test verifies that different references to the same singleton instance provide
     * consistent functionality and behavior. It tests both the informational methods
     * (getLoggerInfo) and the core logging methods (logInfo, logWarning) to ensure
     * that all references behave identically and contain the same instance information.
     * 
     * Test Steps:
     * 1. Get two references to the same Logger singleton instance
     * 2. Call getLoggerInfo() on both references and compare the output
     * 3. Verify that both info strings contain the same hash code
     * 4. Confirm that both references have identical hash codes
     * 5. Test actual logging functionality on both references
     * 6. Verify that all functionality is consistent across references
     * 
     * Expected Results:
     * - Both references should return identical getLoggerInfo() strings
     * - Both info strings should contain the same instance hash code
     * - Both references should have identical hash codes
     * - Logging methods should work consistently on both references
     * - No functional differences between different references to the same instance
     * 
     */
    private static void testFunctionalityConsistency() {
        System.out.println("Test 11: Functionality Consistency Test");
        System.out.println("=" .repeat(50));
        
        // Get two references to the same singleton instance
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        
        // Test that both references provide same functionality
        String info1 = logger1.getLoggerInfo();
        String info2 = logger2.getLoggerInfo();
        
        // Verify that both info strings contain the same instance hash code
        boolean sameInfo = info1.contains(String.valueOf(logger1.hashCode())) &&
                          info2.contains(String.valueOf(logger2.hashCode())) &&
                          logger1.hashCode() == logger2.hashCode();
        
        // Display detailed verification results
        System.out.println("Logger1 info contains correct hash: " + info1.contains(String.valueOf(logger1.hashCode())));
        System.out.println("Logger2 info contains correct hash: " + info2.contains(String.valueOf(logger2.hashCode())));
        System.out.println("Both have same hash code: " + (logger1.hashCode() == logger2.hashCode()));
        
        // Test actual logging functionality on both references
        System.out.println("Testing logging methods...");
        logger1.logInfo("Test from logger1");
        logger2.logWarning("Test from logger2");
        
        // Verify that all functionality is consistent across references
        assert sameInfo : "FAIL: Functionality inconsistent!";
        System.out.println("PASS: Functionality consistency verified\n");
    }
    
    /**
     * Test 12: Reflection Protection Test
     * 
     * This test attempts to break the singleton pattern using Java reflection by accessing
     * the private constructor and creating additional instances. This is an advanced test
     * that checks whether the singleton implementation is vulnerable to reflection-based
     * attacks, which could potentially create multiple instances and violate the singleton
     * contract.
     * 
     * Test Steps:
     * 1. Get a normal Logger instance using getInstance()
     * 2. Use reflection to access the private constructor
     * 3. Attempt to make the constructor accessible and create a new instance
     * 4. Compare the reflection-created instance with the normal instance
     * 5. Check if reflection was successfully blocked or if it created a new instance
     * 6. Provide warnings if the singleton can be broken via reflection
     * 
     * Expected Results:
     * - Reflection should either be blocked (ideal) or create the same instance
     * - If reflection succeeds, it should create the same singleton instance
     * - If reflection creates a different instance, it indicates a vulnerability
     * - The test provides guidance on adding reflection protection if needed
     * 
     */
    private static void testReflectionProtection() {
        System.out.println("Test 12: Reflection Protection Test");
        System.out.println("=" .repeat(50));
        
        try {
            // Get normal singleton instance for comparison
            Logger normalInstance = Logger.getInstance();
            
            // Try to access private constructor via reflection
            java.lang.reflect.Constructor<Logger> constructor = 
                Logger.class.getDeclaredConstructor();
            
            System.out.println("Found private constructor via reflection");
            System.out.println("Constructor accessible: " + constructor.canAccess(null));
            
            // Attempt to create instance via reflection
            boolean reflectionBlocked = false;
            try {
                constructor.setAccessible(true);
                Logger reflectionInstance = constructor.newInstance();
                
                // Check if reflection created the same or different instance
                boolean sameAsNormal = (reflectionInstance == normalInstance);
                System.out.println("Reflection created instance: " + !sameAsNormal);
                System.out.println("Normal instance: " + System.identityHashCode(normalInstance));
                System.out.println("Reflection instance: " + System.identityHashCode(reflectionInstance));
                
            } catch (Exception e) {
                // Reflection was blocked (good for singleton integrity)
                reflectionBlocked = true;
                System.out.println("Reflection blocked: " + e.getClass().getSimpleName());
            }
            
            // Provide guidance if singleton can be broken via reflection
            if (!reflectionBlocked) {
                System.out.println("WARNING: Singleton can be broken via reflection!");
                System.out.println("Consider adding reflection protection in constructor");
            }
            
        } catch (Exception e) {
            // Could not access constructor at all
            System.out.println("Could not access constructor: " + e.getClass().getSimpleName());
        }
        
        System.out.println("PASS: Reflection test completed\n");
    }
}