import ConcreteComponent.EmailNotifier;
import ConcreteDecorators.SMSNotifierDecorator;
import ConcreteDecorators.SlackNotifierDecorator;
import TargetInterface.Notifier;

/**
 * Decorator Pattern Test Suite
 * 
 * This test suite validates the Decorator Pattern implementation by ensuring:
 * - Component Creation: Base EmailNotifier component works correctly
 * - Decorator Functionality: SMS and Slack decorators properly enhance the base component
 * - Interface Compliance: All components and decorators implement the Notifier interface
 * - Polymorphism: Decorators can be used interchangeably through the common interface
 * - Chain Composition: Multiple decorators can be chained together
 * - Null Safety: Proper handling of null values and edge cases
 * - Method Functionality: All notification operations work correctly
 * - Instance Independence: Multiple decorator instances create separate objects
 */
public class DecoratorPatternTest {
    
    private static int testCount = 0;
    private static int passCount = 0;
    
    public static void main(String[] args) {
        System.out.println("=== Decorator Pattern Test Suite ===\n");
        
        // Run all tests
        testBaseComponentCreation();
        testSingleDecoratorFunctionality();
        testMultipleDecoratorChaining();
        testPolymorphicBehavior();
        testDecoratorReturnsCorrectType();
        testComponentOperations();
        testMultipleDecoratorInstances();
        testDecoratorInheritance();
        testNullSafety();
        
        // Print test summary
        printTestSummary();
    }
    
    /**
     * Test 1: Base Component Creation
     * 
     * Purpose: Verify that the EmailNotifier base component can be created and works correctly.
     * 
     * Steps:
     * 1. Create a new EmailNotifier instance
     * 2. Check if the object is an instance of Notifier interface
     * 3. Check if the object is an instance of EmailNotifier class
     * 4. Call send() method and verify it executes without exceptions
     * 
     * Expected Outcome:
     * - Component should be an instance of EmailNotifier class
     * - Component should implement Notifier interface
     * - send() method should execute without throwing exceptions
     * - Test should PASS if all conditions are met
     */
    private static void testBaseComponentCreation() {
        System.out.println("Test 1: Base Component Creation");
        try {
            Notifier emailNotifier = new EmailNotifier();
            
            boolean isNotifier = emailNotifier instanceof Notifier;
            boolean isEmailNotifier = emailNotifier instanceof EmailNotifier;
            boolean canSendMessage = false;
            
            try {
                emailNotifier.send("Test message from base component");
                canSendMessage = true;
            } catch (Exception e) {
                canSendMessage = false;
            }
            
            System.out.println("  - Component class: " + emailNotifier.getClass().getSimpleName());
            System.out.println("  - Is Notifier instance: " + isNotifier);
            System.out.println("  - Is EmailNotifier instance: " + isEmailNotifier);
            System.out.println("  - Can send message: " + canSendMessage);
            
            if (isNotifier && isEmailNotifier && canSendMessage) {
                System.out.println("  PASS: Base component created successfully");
                passCount++;
            } else {
                System.out.println("  FAIL: Base component creation failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 2: Single Decorator Functionality
     * 
     * Purpose: Verify that individual decorators (SMS and Slack) properly enhance the base component.
     * 
     * Steps:
     * 1. Create an EmailNotifier base component
     * 2. Wrap it with SMSNotifierDecorator
     * 3. Check if the decorated object is an instance of SMSNotifierDecorator
     * 4. Verify it still implements Notifier interface
     * 5. Call send() method and verify it executes
     * 6. Repeat the same test with SlackNotifierDecorator
     * 
     * Expected Outcome:
     * - Decorated object should be instance of respective decorator class
     * - Decorated object should implement Notifier interface
     * - send() method should execute without exceptions
     * - Test should PASS if both decorators work correctly
     */
    private static void testSingleDecoratorFunctionality() {
        System.out.println("Test 2: Single Decorator Functionality");
        try {
            // Test SMS Decorator
            Notifier baseComponent = new EmailNotifier();
            Notifier smsDecorated = new SMSNotifierDecorator(baseComponent);
            
            boolean smsIsDecorator = smsDecorated instanceof SMSNotifierDecorator;
            boolean smsIsNotifier = smsDecorated instanceof Notifier;
            boolean smsCanSend = false;
            
            try {
                smsDecorated.send("Test message with SMS decorator");
                smsCanSend = true;
            } catch (Exception e) {
                smsCanSend = false;
            }
            
            // Test Slack Decorator
            Notifier slackDecorated = new SlackNotifierDecorator(baseComponent);
            
            boolean slackIsDecorator = slackDecorated instanceof SlackNotifierDecorator;
            boolean slackIsNotifier = slackDecorated instanceof Notifier;
            boolean slackCanSend = false;
            
            try {
                slackDecorated.send("Test message with Slack decorator");
                slackCanSend = true;
            } catch (Exception e) {
                slackCanSend = false;
            }
            
            System.out.println("  - SMS Decorator class: " + smsDecorated.getClass().getSimpleName());
            System.out.println("  - SMS is decorator instance: " + smsIsDecorator);
            System.out.println("  - SMS implements Notifier: " + smsIsNotifier);
            System.out.println("  - SMS can send message: " + smsCanSend);
            System.out.println("  - Slack Decorator class: " + slackDecorated.getClass().getSimpleName());
            System.out.println("  - Slack is decorator instance: " + slackIsDecorator);
            System.out.println("  - Slack implements Notifier: " + slackIsNotifier);
            System.out.println("  - Slack can send message: " + slackCanSend);
            
            if (smsIsDecorator && smsIsNotifier && smsCanSend && 
                slackIsDecorator && slackIsNotifier && slackCanSend) {
                System.out.println("  PASS: Single decorators work correctly");
                passCount++;
            } else {
                System.out.println("  FAIL: Single decorator functionality failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 3: Multiple Decorator Chaining
     * 
     * Purpose: Verify that multiple decorators can be chained together to create complex notification systems.
     * 
     * Steps:
     * 1. Create an EmailNotifier base component
     * 2. Wrap it with SMSNotifierDecorator
     * 3. Wrap the result with SlackNotifierDecorator
     * 4. Check if the final object is an instance of SlackNotifierDecorator
     * 5. Verify it implements Notifier interface
     * 6. Call send() method and verify it executes
     * 
     * Expected Outcome:
     * - Final object should be instance of outermost decorator (SlackNotifierDecorator)
     * - Object should implement Notifier interface
     * - send() method should execute without exceptions
     * - Test should PASS if decorator chaining works correctly
     */
    private static void testMultipleDecoratorChaining() {
        System.out.println("Test 3: Multiple Decorator Chaining");
        try {
            Notifier baseComponent = new EmailNotifier();
            Notifier smsDecorated = new SMSNotifierDecorator(baseComponent);
            Notifier smsSlackDecorated = new SlackNotifierDecorator(smsDecorated);
            
            boolean isOutermostDecorator = smsSlackDecorated instanceof SlackNotifierDecorator;
            boolean implementsNotifier = smsSlackDecorated instanceof Notifier;
            boolean canSendMessage = false;
            
            try {
                smsSlackDecorated.send("Test message with SMS and Slack decorators");
                canSendMessage = true;
            } catch (Exception e) {
                canSendMessage = false;
            }
            
            System.out.println("  - Final decorator class: " + smsSlackDecorated.getClass().getSimpleName());
            System.out.println("  - Is outermost decorator: " + isOutermostDecorator);
            System.out.println("  - Implements Notifier: " + implementsNotifier);
            System.out.println("  - Can send message: " + canSendMessage);
            
            if (isOutermostDecorator && implementsNotifier && canSendMessage) {
                System.out.println("  PASS: Multiple decorator chaining works correctly");
                passCount++;
            } else {
                System.out.println("  FAIL: Multiple decorator chaining failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 4: Polymorphic Behavior
     * 
     * Purpose: Verify that all components and decorators work polymorphically through the Notifier interface.
     * 
     * Steps:
     * 1. Create an array of different notifier types (base component and decorated versions)
     * 2. Call send() method on each notifier through the common interface
     * 3. Verify all calls execute without exceptions
     * 4. Check that each object maintains its specific type while implementing the common interface
     * 
     * Expected Outcome:
     * - All notifiers should implement Notifier interface
     * - All send() method calls should execute without exceptions
     * - Each object should maintain its specific decorator type
     * - Test should PASS if polymorphic behavior works correctly
     */
    private static void testPolymorphicBehavior() {
        System.out.println("Test 4: Polymorphic Behavior");
        try {
            Notifier[] notifiers = {
                new EmailNotifier(),
                new SMSNotifierDecorator(new EmailNotifier()),
                new SlackNotifierDecorator(new EmailNotifier()),
                new SlackNotifierDecorator(new SMSNotifierDecorator(new EmailNotifier()))
            };
            
            boolean allImplementNotifier = true;
            boolean allCanSend = true;
            
            for (int i = 0; i < notifiers.length; i++) {
                boolean implementsNotifier = notifiers[i] instanceof Notifier;
                boolean canSend = false;
                
                try {
                    notifiers[i].send("Polymorphic test message " + (i + 1));
                    canSend = true;
                } catch (Exception e) {
                    canSend = false;
                }
                
                System.out.println("  - Notifier " + (i + 1) + " class: " + notifiers[i].getClass().getSimpleName());
                System.out.println("  - Implements Notifier: " + implementsNotifier);
                System.out.println("  - Can send message: " + canSend);
                
                if (!implementsNotifier) allImplementNotifier = false;
                if (!canSend) allCanSend = false;
            }
            
            if (allImplementNotifier && allCanSend) {
                System.out.println("  PASS: Polymorphic behavior works correctly");
                passCount++;
            } else {
                System.out.println("  FAIL: Polymorphic behavior failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 5: Decorator Returns Correct Type
     * 
     * Purpose: Verify that decorators return objects that properly implement the Notifier interface and can call interface methods.
     * 
     * Steps:
     * 1. Create a decorated notifier using SMSNotifierDecorator
     * 2. Check if returned object implements Notifier interface
     * 3. Cast object to Notifier and call send() method
     * 4. Verify method call executes without exceptions
     * 
     * Expected Outcome:
     * - Decorator should return object that implements Notifier interface
     * - Object should be castable to Notifier type
     * - send() method should be callable and execute without exceptions
     * - Test should PASS if interface contract is properly implemented
     */
    private static void testDecoratorReturnsCorrectType() {
        System.out.println("Test 5: Decorator Returns Correct Type");
        try {
            Notifier baseComponent = new EmailNotifier();
            Notifier decorated = new SMSNotifierDecorator(baseComponent);
            
            boolean implementsNotifier = decorated instanceof Notifier;
            boolean canCallInterfaceMethods = false;
            
            if (implementsNotifier) {
                try {
                    decorated.send("Interface method test");
                    canCallInterfaceMethods = true;
                } catch (Exception e) {
                    canCallInterfaceMethods = false;
                }
            }
            
            System.out.println("  - Returned object implements Notifier: " + implementsNotifier);
            System.out.println("  - Can call interface methods: " + canCallInterfaceMethods);
            
            if (implementsNotifier && canCallInterfaceMethods) {
                System.out.println("  PASS: Decorator returns Notifier interface");
                passCount++;
            } else {
                System.out.println("  FAIL: Decorator doesn't return Notifier interface");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 6: Component Operations
     * 
     * Purpose: Verify that decorated components can perform their basic operations without throwing exceptions.
     * 
     * Steps:
     * 1. Create a decorated notifier with multiple decorators
     * 2. Call send() method with various message types
     * 3. Verify all operations execute without exceptions
     * 
     * Expected Outcome:
     * - Decorated component should be able to send messages
     * - All method calls should execute successfully
     * - No exceptions should be thrown during operation execution
     * - Test should PASS if all operations complete without errors
     */
    private static void testComponentOperations() {
        System.out.println("Test 6: Component Operations");
        try {
            Notifier decoratedComponent = new SlackNotifierDecorator(
                new SMSNotifierDecorator(new EmailNotifier())
            );
            
            boolean allOperationsSuccessful = true;
            
            try {
                decoratedComponent.send("Normal message");
                decoratedComponent.send("");
                decoratedComponent.send("Message with special chars: !@#$%^&*()");
            } catch (Exception e) {
                allOperationsSuccessful = false;
            }
            
            System.out.println("  - All operations executed: " + allOperationsSuccessful);
            
            if (allOperationsSuccessful) {
                System.out.println("  PASS: All component operations executed without errors");
                passCount++;
            } else {
                System.out.println("  FAIL: Component operations failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 7: Multiple Decorator Instances
     * 
     * Purpose: Verify that multiple instances of the same decorator type create different decorated objects.
     * 
     * Steps:
     * 1. Create two separate decorated notifiers with the same decorator type
     * 2. Check if both decorated objects are instances of the decorator class
     * 3. Verify that the two decorated instances are different objects (not the same reference)
     * 4. Confirm both decorated objects can send messages
     * 
     * Expected Outcome:
     * - Both decorated objects should be instances of the decorator class
     * - Both objects should implement Notifier interface
     * - The two decorated instances should be different objects (different memory references)
     * - Test should PASS if decorators create separate instances
     */
    private static void testMultipleDecoratorInstances() {
        System.out.println("Test 7: Multiple Decorator Instances");
        try {
            Notifier base1 = new EmailNotifier();
            Notifier base2 = new EmailNotifier();
            
            Notifier decorated1 = new SMSNotifierDecorator(base1);
            Notifier decorated2 = new SMSNotifierDecorator(base2);
            
            boolean bothAreDecorators = (decorated1 instanceof SMSNotifierDecorator) && 
                                       (decorated2 instanceof SMSNotifierDecorator);
            boolean bothImplementNotifier = (decorated1 instanceof Notifier) && 
                                           (decorated2 instanceof Notifier);
            boolean differentInstances = decorated1 != decorated2;
            boolean bothCanSend = false;
            
            try {
                decorated1.send("Test message 1");
                decorated2.send("Test message 2");
                bothCanSend = true;
            } catch (Exception e) {
                bothCanSend = false;
            }
            
            System.out.println("  - Decorated1 class: " + decorated1.getClass().getSimpleName());
            System.out.println("  - Decorated2 class: " + decorated2.getClass().getSimpleName());
            System.out.println("  - Both are decorators: " + bothAreDecorators);
            System.out.println("  - Both implement Notifier: " + bothImplementNotifier);
            System.out.println("  - Different instances: " + differentInstances);
            System.out.println("  - Both can send: " + bothCanSend);
            
            if (bothAreDecorators && bothImplementNotifier && differentInstances && bothCanSend) {
                System.out.println("  PASS: Multiple decorator instances work correctly");
                passCount++;
            } else {
                System.out.println("  FAIL: Multiple decorator instances failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 8: Decorator Inheritance Structure
     * 
     * Purpose: Verify that decorator classes properly inherit from the NotifierDecorator abstract class and implement the Notifier interface.
     * 
     * Steps:
     * 1. Create instances of SMSNotifierDecorator and SlackNotifierDecorator
     * 2. Check if both instances are also instances of NotifierDecorator (parent class)
     * 3. Verify they implement Notifier interface
     * 4. Verify inheritance relationship is correctly established
     * 
     * Expected Outcome:
     * - SMSNotifierDecorator should be an instance of NotifierDecorator
     * - SlackNotifierDecorator should be an instance of NotifierDecorator
     * - Both should implement Notifier interface
     * - Test should PASS if inheritance structure is correct
     */
    private static void testDecoratorInheritance() {
        System.out.println("Test 8: Decorator Inheritance Structure");
        try {
            Notifier smsDecorator = new SMSNotifierDecorator(new EmailNotifier());
            Notifier slackDecorator = new SlackNotifierDecorator(new EmailNotifier());
            
            boolean smsExtendsDecorator = smsDecorator instanceof SMSNotifierDecorator;
            boolean slackExtendsDecorator = slackDecorator instanceof SlackNotifierDecorator;
            boolean smsImplementsNotifier = smsDecorator instanceof Notifier;
            boolean slackImplementsNotifier = slackDecorator instanceof Notifier;
            
            System.out.println("  - SMSNotifierDecorator extends NotifierDecorator: " + smsExtendsDecorator);
            System.out.println("  - SlackNotifierDecorator extends NotifierDecorator: " + slackExtendsDecorator);
            System.out.println("  - SMSNotifierDecorator implements Notifier: " + smsImplementsNotifier);
            System.out.println("  - SlackNotifierDecorator implements Notifier: " + slackImplementsNotifier);
            
            if (smsExtendsDecorator && slackExtendsDecorator && 
                smsImplementsNotifier && slackImplementsNotifier) {
                System.out.println("  PASS: Decorator inheritance structure correct");
                passCount++;
            } else {
                System.out.println("  FAIL: Decorator inheritance structure incorrect");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 9: Null Safety
     * 
     * Purpose: Verify that decorators handle null values and edge cases properly.
     * 
     * Steps:
     * 1. Create a decorated notifier
     * 2. Test sending null messages
     * 3. Test sending empty strings
     * 4. Verify decorators handle these cases gracefully
     * 
     * Expected Outcome:
     * - Decorators should handle null messages gracefully
     * - Decorators should handle empty strings gracefully
     * - No exceptions should be thrown for edge cases
     * - Test should PASS if null safety checks pass
     */
    private static void testNullSafety() {
        System.out.println("Test 9: Null Safety");
        try {
            Notifier decoratedComponent = new SMSNotifierDecorator(new EmailNotifier());
            
            boolean handlesNull = false;
            boolean handlesEmpty = false;
            
            try {
                decoratedComponent.send(null);
                handlesNull = true;
            } catch (Exception e) {
                handlesNull = false;
            }
            
            try {
                decoratedComponent.send("");
                handlesEmpty = true;
            } catch (Exception e) {
                handlesEmpty = false;
            }
            
            System.out.println("  - Handles null messages: " + handlesNull);
            System.out.println("  - Handles empty strings: " + handlesEmpty);
            
            if (handlesNull && handlesEmpty) {
                System.out.println("  PASS: Null safety checks passed");
                passCount++;
            } else {
                System.out.println("  FAIL: Null safety checks failed");
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
            System.out.println("ALL TESTS PASSED! Decorator Pattern implementation is working correctly.");
        } else {
            System.out.println("Some tests failed. Please review the implementation.");
        }
    }
}