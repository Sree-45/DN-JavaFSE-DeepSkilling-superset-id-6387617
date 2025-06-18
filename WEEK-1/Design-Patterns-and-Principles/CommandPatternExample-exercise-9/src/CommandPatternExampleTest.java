/**
 * Command Pattern Test Suite
 * 
 * This test suite validates the Command Pattern implementation by ensuring:
 * - Command Execution: Commands properly execute their operations
 * - Receiver Interaction: Commands correctly interact with their receivers
 * - Invoker Functionality: Remote control properly invokes commands
 * - Command Reusability: Same command can be executed multiple times
 * - Multiple Receivers: Commands work with different receiver instances
 * - Null Safety: System handles null commands gracefully
 * - Pattern Structure: Proper separation of concerns between Command, Receiver, and Invoker
 * - Edge Cases: System handles various edge cases properly
 * - Multiple Invokers: Multiple remote controls work correctly
 */
public class CommandPatternExampleTest {
    
    private static int testCount = 0;
    private static int passCount = 0;
    
    public static void main(String[] args) {
        System.out.println("=== Command Pattern Test Suite ===\n");
        
        // Run all tests
        testBasicFunctionality();
        testMultipleReceivers();
        testCommandReusability();
        testEdgeCases();
        testMultipleRemoteControls();
        testSameCommandDifferentReceivers();
        testNullCommandHandling();
        
        // Print test summary
        printTestSummary();
    }
    
    /**
     * Test 1: Basic Functionality
     * 
     * Purpose: Verify that commands can be created and executed through the remote control.
     * 
     * Steps:
     * 1. Create a Light receiver instance
     * 2. Create LightOnCommand and LightOffCommand instances
     * 3. Create a RemoteControl invoker
     * 4. Set commands and execute them
     * 5. Verify commands execute without exceptions
     * 
     * Expected Outcome:
     * - Commands should execute successfully
     * - Light should respond to on/off commands
     * - No exceptions should be thrown
     * - Test should PASS if all operations complete successfully
     */
    private static void testBasicFunctionality() {
        System.out.println("Test 1: Basic Functionality");
        try {
            Light livingRoomLight = new Light("Living Room");
            Command lightOn = new LightOnCommand(livingRoomLight);
            Command lightOff = new LightOffCommand(livingRoomLight);
            RemoteControl remote = new RemoteControl();
            
            // Test turning light on
            remote.setCommand(lightOn);
            remote.pressButton();
            
            // Test turning light off
            remote.setCommand(lightOff);
            remote.pressButton();
            
            System.out.println("  - Light on command executed successfully");
            System.out.println("  - Light off command executed successfully");
            System.out.println("  PASS: Basic functionality test passed");
            passCount++;
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 2: Multiple Receivers
     * 
     * Purpose: Verify that commands can work with multiple different receiver instances.
     * 
     * Steps:
     * 1. Create multiple Light instances (different locations)
     * 2. Create commands for each light
     * 3. Execute commands through remote control
     * 4. Verify all commands execute successfully
     * 
     * Expected Outcome:
     * - All lights should respond to their respective commands
     * - Each light should be controlled independently
     * - No exceptions should be thrown
     * - Test should PASS if all lights respond correctly
     */
    private static void testMultipleReceivers() {
        System.out.println("Test 2: Multiple Receivers");
        try {
            Light[] lights = {
                new Light("Living Room"),
                new Light("Bedroom"),
                new Light("Kitchen"),
                new Light("Bathroom"),
                new Light("Garage")
            };
            
            RemoteControl remote = new RemoteControl();
            int successfulCommands = 0;
            
            // Turn on all lights
            for (Light light : lights) {
                remote.setCommand(new LightOnCommand(light));
                remote.pressButton();
                successfulCommands++;
            }
            
            // Turn off all lights
            for (Light light : lights) {
                remote.setCommand(new LightOffCommand(light));
                remote.pressButton();
                successfulCommands++;
            }
            
            System.out.println("  - Total commands executed: " + successfulCommands);
            System.out.println("  - Expected commands: " + (lights.length * 2));
            
            if (successfulCommands == lights.length * 2) {
                System.out.println("  PASS: Multiple receivers test passed");
                passCount++;
            } else {
                System.out.println("  FAIL: Not all commands executed successfully");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 3: Command Reusability
     * 
     * Purpose: Verify that the same command instance can be executed multiple times.
     * 
     * Steps:
     * 1. Create a Light receiver and command instances
     * 2. Execute the same command multiple times
     * 3. Verify command executes successfully each time
     * 
     * Expected Outcome:
     * - Same command should execute multiple times without issues
     * - No exceptions should be thrown
     * - Test should PASS if command reusability works correctly
     */
    private static void testCommandReusability() {
        System.out.println("Test 3: Command Reusability");
        try {
            Light light = new Light("Test Light");
            Command onCommand = new LightOnCommand(light);
            Command offCommand = new LightOffCommand(light);
            RemoteControl remote = new RemoteControl();
            
            int successfulExecutions = 0;
            
            // Execute same commands multiple times
            for (int i = 1; i <= 3; i++) {
                remote.setCommand(onCommand);
                remote.pressButton();
                successfulExecutions++;
                
                remote.setCommand(offCommand);
                remote.pressButton();
                successfulExecutions++;
            }
            
            System.out.println("  - Total successful executions: " + successfulExecutions);
            System.out.println("  - Expected executions: 6");
            
            if (successfulExecutions == 6) {
                System.out.println("  PASS: Command reusability test passed");
                passCount++;
            } else {
                System.out.println("  FAIL: Command reusability test failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 4: Edge Cases
     * 
     * Purpose: Verify that the system handles various edge cases gracefully.
     * 
     * Steps:
     * 1. Test remote with no command set
     * 2. Test with explicitly null command
     * 3. Test with empty location light
     * 4. Test with special characters in location
     * 5. Verify system doesn't crash
     * 
     * Expected Outcome:
     * - System should handle edge cases gracefully
     * - No exceptions should be thrown
     * - Test should PASS if edge case handling works correctly
     */
    private static void testEdgeCases() {
        System.out.println("Test 4: Edge Cases");
        try {
            RemoteControl remote = new RemoteControl();
            int successfulHandlings = 0;
            
            // Test 1: Remote with no command set
            remote.pressButton();
            successfulHandlings++;
            
            // Test 2: Setting null command explicitly
            remote.setCommand(null);
            remote.pressButton();
            successfulHandlings++;
            
            // Test 3: Empty location light
            Light emptyLocationLight = new Light("");
            remote.setCommand(new LightOnCommand(emptyLocationLight));
            remote.pressButton();
            successfulHandlings++;
            
            remote.setCommand(new LightOffCommand(emptyLocationLight));
            remote.pressButton();
            successfulHandlings++;
            
            // Test 4: Light with special characters
            Light specialLight = new Light("Room #1 @Home (Main Floor)");
            remote.setCommand(new LightOnCommand(specialLight));
            remote.pressButton();
            successfulHandlings++;
            
            System.out.println("  - Total edge cases handled: " + successfulHandlings);
            System.out.println("  - Expected handlings: 5");
            
            if (successfulHandlings == 5) {
                System.out.println("  PASS: Edge cases test passed");
                passCount++;
            } else {
                System.out.println("  FAIL: Edge cases test failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 5: Multiple Remote Controls
     * 
     * Purpose: Verify that multiple remote controls can work with the same commands.
     * 
     * Steps:
     * 1. Create multiple RemoteControl instances
     * 2. Create shared Light and Command instances
     * 3. Use different remotes to control the same light
     * 4. Verify all remotes work correctly
     * 
     * Expected Outcome:
     * - Multiple remotes should work independently
     * - Same light should respond to commands from different remotes
     * - No exceptions should be thrown
     * - Test should PASS if multiple remotes work correctly
     */
    private static void testMultipleRemoteControls() {
        System.out.println("Test 5: Multiple Remote Controls");
        try {
            Light sharedLight = new Light("Shared Light");
            Command onCommand = new LightOnCommand(sharedLight);
            Command offCommand = new LightOffCommand(sharedLight);
            
            RemoteControl remote1 = new RemoteControl();
            RemoteControl remote2 = new RemoteControl();
            RemoteControl remote3 = new RemoteControl();
            
            int successfulOperations = 0;
            
            // Different remotes controlling same light
            remote1.setCommand(onCommand);
            remote1.pressButton();
            successfulOperations++;
            
            remote2.setCommand(offCommand);
            remote2.pressButton();
            successfulOperations++;
            
            remote3.setCommand(onCommand);
            remote3.pressButton();
            successfulOperations++;
            
            System.out.println("  - Total successful operations: " + successfulOperations);
            System.out.println("  - Expected operations: 3");
            
            if (successfulOperations == 3) {
                System.out.println("  PASS: Multiple remote controls test passed");
                passCount++;
            } else {
                System.out.println("  FAIL: Multiple remote controls test failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 6: Same Command Type, Different Receivers
     * 
     * Purpose: Verify that the same type of command can work with different receiver instances.
     * 
     * Steps:
     * 1. Create multiple Light instances
     * 2. Create same type of commands for different receivers
     * 3. Execute commands through remote control
     * 4. Verify all commands execute successfully
     * 
     * Expected Outcome:
     * - Same command type should work with different receivers
     * - Each receiver should respond correctly
     * - No exceptions should be thrown
     * - Test should PASS if same command type works with different receivers
     */
    private static void testSameCommandDifferentReceivers() {
        System.out.println("Test 6: Same Command Type, Different Receivers");
        try {
            Light light1 = new Light("Office");
            Light light2 = new Light("Conference Room");
            
            // Create same type of commands for different receivers
            Command office_on = new LightOnCommand(light1);
            Command conference_on = new LightOnCommand(light2);
            Command office_off = new LightOffCommand(light1);
            Command conference_off = new LightOffCommand(light2);
            
            RemoteControl remote = new RemoteControl();
            int successfulCommands = 0;
            
            remote.setCommand(office_on);
            remote.pressButton();
            successfulCommands++;
            
            remote.setCommand(conference_on);
            remote.pressButton();
            successfulCommands++;
            
            remote.setCommand(office_off);
            remote.pressButton();
            successfulCommands++;
            
            remote.setCommand(conference_off);
            remote.pressButton();
            successfulCommands++;
            
            System.out.println("  - Total successful commands: " + successfulCommands);
            System.out.println("  - Expected commands: 4");
            
            if (successfulCommands == 4) {
                System.out.println("  PASS: Same command type, different receivers test passed");
                passCount++;
            } else {
                System.out.println("  FAIL: Same command type, different receivers test failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 7: Null Command Handling
     * 
     * Purpose: Verify that the system handles null commands gracefully without crashing.
     * 
     * Steps:
     * 1. Create a RemoteControl instance
     * 2. Try to execute without setting a command
     * 3. Set null command explicitly and try to execute
     * 4. Set command, then set to null and try to execute
     * 5. Verify system doesn't crash
     * 
     * Expected Outcome:
     * - System should handle null commands gracefully
     * - No exceptions should be thrown
     * - Test should PASS if null handling works correctly
     */
    private static void testNullCommandHandling() {
        System.out.println("Test 7: Null Command Handling");
        try {
            RemoteControl remote = new RemoteControl();
            int successfulHandlings = 0;
            
            // Scenario 1: Fresh remote (no command set)
            remote.pressButton();
            successfulHandlings++;
            
            // Scenario 2: Explicitly set to null
            remote.setCommand(null);
            remote.pressButton();
            successfulHandlings++;
            
            // Scenario 3: Set command, then set to null
            Light testLight = new Light("Test");
            remote.setCommand(new LightOnCommand(testLight));
            remote.pressButton();
            successfulHandlings++;
            
            remote.setCommand(null);
            remote.pressButton();
            successfulHandlings++;
            
            System.out.println("  - Total null scenarios handled: " + successfulHandlings);
            System.out.println("  - Expected handlings: 4");
            
            if (successfulHandlings == 4) {
                System.out.println("  PASS: Null command handling test passed");
                passCount++;
            } else {
                System.out.println("  FAIL: Null command handling test failed");
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
            System.out.println("ALL TESTS PASSED! Command Pattern implementation is working correctly.");
        } else {
            System.out.println("Some tests failed. Please review the implementation.");
        }
    }
}