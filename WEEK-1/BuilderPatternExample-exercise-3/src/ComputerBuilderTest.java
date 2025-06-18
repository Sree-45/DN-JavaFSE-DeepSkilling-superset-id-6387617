/**
 * Builder Pattern Test Suite
 * 
 * This test suite validates the Builder Pattern implementation by ensuring:
 * - Correct Object Creation: Builder creates Computer objects with proper attributes
 * - Method Chaining: Builder methods can be chained together
 * - Immutability: Created Computer objects are immutable
 * - Edge Cases: Builder handles edge cases gracefully
 * - Validation: All computer attributes are properly set and retrieved
 * - Reusability: Builder can create multiple different computer configurations
 * - Boundary Values: Builder works with boundary values and special characters
 */
public class ComputerBuilderTest {
    
    private static int testCount = 0;
    private static int passCount = 0;
    
    public static void main(String[] args) {
        System.out.println("=== Builder Pattern Test Suite ===\n");
        
        testBasicFunctionality();
        testAllParametersSet();
        testEdgeCases();
        testMethodChaining();
        testImmutability();
        testBuilderReusability();
        testBoundaryValues();
        testValidationScenarios();
        
        printTestSummary();
    }
    
    /**
     * Test 1: Basic Functionality - Minimum Required Parameters
     * 
     * Purpose: Verify that the Builder can create a Computer object with only the required parameters (CPU and RAM).
     * 
     * Steps:
     * 1. Create a Computer.Builder instance with CPU and RAM parameters
     * 2. Call build() method to create Computer object
     * 3. Check if CPU and RAM are correctly set
     * 4. Verify that optional parameters are null by default
     * 
     * Expected Outcome:
     * - Computer object should be created successfully
     * - getCPU() should return the provided CPU value
     * - getRAM() should return the provided RAM value
     * - getStorage() should return null (not set)
     * - Test should PASS if all conditions are met
     */
    private static void testBasicFunctionality() {
        System.out.println("Test 1: Basic Functionality - Minimum Required Parameters");
        try {
            Computer computer = new Computer.Builder("Intel Core i3", "8GB DDR4").build();
            
            boolean cpuCorrect = computer.getCPU().equals("Intel Core i3");
            boolean ramCorrect = computer.getRAM().equals("8GB DDR4");
            boolean storageNull = computer.getStorage() == null;
            
            System.out.println("  - CPU: " + computer.getCPU());
            System.out.println("  - RAM: " + computer.getRAM());
            System.out.println("  - Storage: " + computer.getStorage());
            System.out.println("  - CPU correct: " + cpuCorrect);
            System.out.println("  - RAM correct: " + ramCorrect);
            System.out.println("  - Storage null: " + storageNull);
            
            if (cpuCorrect && ramCorrect && storageNull) {
                System.out.println("  PASS: Basic functionality works correctly");
                passCount++;
            } else {
                System.out.println("  FAIL: Basic functionality failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 2: All Parameters Set
     * 
     * Purpose: Verify that the Builder can set all optional parameters and create a fully configured Computer object.
     * 
     * Steps:
     * 1. Create a Computer.Builder instance with CPU and RAM
     * 2. Chain all setter methods for optional parameters
     * 3. Call build() to create Computer object
     * 4. Verify all parameters are correctly set
     * 
     * Expected Outcome:
     * - All optional parameters should be set correctly
     * - Boolean parameters (WiFi, Bluetooth) should work properly
     * - Test should PASS if all parameters are set as expected
     */
    private static void testAllParametersSet() {
        System.out.println("Test 2: All Parameters Set");
        try {
            Computer computer = new Computer.Builder("Intel Core i7", "32GB DDR4")
                    .setStorage("1TB NVMe SSD")
                    .setGraphicsCard("NVIDIA RTX 4070")
                    .setMotherboard("ASUS ROG Strix")
                    .setPowerSupply("750W 80+ Gold")
                    .setCoolingSystem("AIO Liquid Cooler")
                    .setWiFi(true)
                    .setBluetooth(true)
                    .build();
            
            boolean cpuCorrect = computer.getCPU().equals("Intel Core i7");
            boolean storageCorrect = computer.getStorage().equals("1TB NVMe SSD");
            boolean graphicsCorrect = computer.getGraphicsCard().equals("NVIDIA RTX 4070");
            boolean wifiEnabled = computer.hasWiFi();
            boolean bluetoothEnabled = computer.hasBluetooth();
            
            System.out.println("  - CPU: " + computer.getCPU());
            System.out.println("  - Storage: " + computer.getStorage());
            System.out.println("  - Graphics: " + computer.getGraphicsCard());
            System.out.println("  - WiFi enabled: " + wifiEnabled);
            System.out.println("  - Bluetooth enabled: " + bluetoothEnabled);
            
            if (cpuCorrect && storageCorrect && graphicsCorrect && wifiEnabled && bluetoothEnabled) {
                System.out.println("  PASS: All parameters set correctly");
                passCount++;
            } else {
                System.out.println("  FAIL: All parameters not set correctly");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 3: Edge Cases - Empty Strings
     * 
     * Purpose: Verify that the Builder handles empty strings for optional parameters gracefully.
     * 
     * Steps:
     * 1. Create a Computer.Builder with valid CPU and RAM
     * 2. Set empty strings for optional parameters
     * 3. Build the Computer object
     * 4. Check if empty strings are handled properly
     * 
     * Expected Outcome:
     * - Builder should accept empty strings for optional parameters
     * - getStorage() and getGraphicsCard() should return empty strings
     * - Test should PASS if empty strings are handled gracefully
     */
    private static void testEdgeCases() {
        System.out.println("Test 3: Edge Cases - Empty Strings");
        try {
            Computer computer = new Computer.Builder("Intel i5", "16GB DDR4")
                    .setStorage("")
                    .setGraphicsCard("")
                    .setMotherboard("")
                    .build();
            
            boolean storageEmpty = computer.getStorage().isEmpty();
            boolean graphicsEmpty = computer.getGraphicsCard().isEmpty();
            boolean motherboardEmpty = computer.getMotherboard().isEmpty();
            
            System.out.println("  - Storage empty: " + storageEmpty);
            System.out.println("  - Graphics empty: " + graphicsEmpty);
            System.out.println("  - Motherboard empty: " + motherboardEmpty);
            
            if (storageEmpty && graphicsEmpty && motherboardEmpty) {
                System.out.println("  PASS: Empty strings handled correctly");
                passCount++;
            } else {
                System.out.println("  FAIL: Empty strings not handled correctly");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 4: Method Chaining
     * 
     * Purpose: Verify that Builder methods can be chained together and return the same builder instance.
     * 
     * Steps:
     * 1. Create a Computer.Builder instance
     * 2. Chain multiple setter methods
     * 3. Verify that each method returns the same builder instance
     * 4. Build the Computer object and verify all values are set
     * 
     * Expected Outcome:
     * - Method chaining should work correctly
     * - Each setter should return the same builder instance
     * - All chained values should be set in the final object
     * - Test should PASS if method chaining works properly
     */
    private static void testMethodChaining() {
        System.out.println("Test 4: Method Chaining");
        try {
            Computer.Builder builder = new Computer.Builder("Intel i5", "16GB");
            Computer.Builder result1 = builder.setStorage("512GB SSD");
            Computer.Builder result2 = result1.setWiFi(true);
            
            boolean sameInstance = builder == result1 && result1 == result2;
            
            Computer computer = result2.build();
            boolean storageSet = computer.getStorage().equals("512GB SSD");
            boolean wifiSet = computer.hasWiFi();
            
            System.out.println("  - Same builder instance: " + sameInstance);
            System.out.println("  - Storage set correctly: " + storageSet);
            System.out.println("  - WiFi set correctly: " + wifiSet);
            
            if (sameInstance && storageSet && wifiSet) {
                System.out.println("  PASS: Method chaining works correctly");
                passCount++;
            } else {
                System.out.println("  FAIL: Method chaining failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 5: Immutability
     * 
     * Purpose: Verify that created Computer objects are immutable and their values cannot be changed after creation.
     * 
     * Steps:
     * 1. Create a Computer object using the Builder
     * 2. Store original values of all attributes
     * 3. Create another Computer object with same parameters
     * 4. Verify that original object's values haven't changed
     * 
     * Expected Outcome:
     * - Computer object should be immutable
     * - Original values should remain unchanged
     * - Test should PASS if object is properly immutable
     */
    private static void testImmutability() {
        System.out.println("Test 5: Immutability");
        try {
            Computer computer = new Computer.Builder("Intel i7", "16GB")
                    .setStorage("512GB SSD")
                    .build();
            
            String originalCPU = computer.getCPU();
            String originalRAM = computer.getRAM();
            String originalStorage = computer.getStorage();
            
            Computer computer2 = new Computer.Builder("Intel i7", "16GB")
                    .setStorage("512GB SSD")
                    .build();
            
            boolean cpuUnchanged = computer.getCPU().equals(originalCPU);
            boolean ramUnchanged = computer.getRAM().equals(originalRAM);
            boolean storageUnchanged = computer.getStorage().equals(originalStorage);
            boolean objectsEqual = computer.getCPU().equals(computer2.getCPU()) &&
                                 computer.getStorage().equals(computer2.getStorage());
            
            System.out.println("  - CPU unchanged: " + cpuUnchanged);
            System.out.println("  - RAM unchanged: " + ramUnchanged);
            System.out.println("  - Storage unchanged: " + storageUnchanged);
            System.out.println("  - Objects have same values: " + objectsEqual);
            
            if (cpuUnchanged && ramUnchanged && storageUnchanged && objectsEqual) {
                System.out.println("  PASS: Object immutability verified");
                passCount++;
            } else {
                System.out.println("  FAIL: Object immutability failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 6: Builder Reusability
     * 
     * Purpose: Verify that the Builder can create multiple different Computer objects with different configurations.
     * 
     * Steps:
     * 1. Create a base Builder with common parameters
     * 2. Create two different Computer objects with different configurations
     * 3. Verify that each object has its specific configuration
     * 
     * Expected Outcome:
     * - Builder should create different Computer objects
     * - Each object should have its specific configuration
     * - Test should PASS if multiple objects can be created
     */
    private static void testBuilderReusability() {
        System.out.println("Test 6: Builder Reusability");
        try {
            Computer.Builder baseBuilder = new Computer.Builder("Intel i5", "16GB DDR4");
            
            Computer gamingPC = baseBuilder
                    .setGraphicsCard("RTX 3070")
                    .setStorage("1TB SSD")
                    .build();
            
            Computer officePC = new Computer.Builder("Intel i5", "16GB DDR4")
                    .setStorage("512GB SSD")
                    .setWiFi(true)
                    .build();
            
            boolean differentConfigs = !gamingPC.getGraphicsCard().equals(officePC.getGraphicsCard());
            boolean gamingHasGraphics = gamingPC.getGraphicsCard() != null;
            boolean officeNoGraphics = officePC.getGraphicsCard() == null;
            
            System.out.println("  - Different configurations: " + differentConfigs);
            System.out.println("  - Gaming PC has graphics: " + gamingHasGraphics);
            System.out.println("  - Office PC no graphics: " + officeNoGraphics);
            
            if (differentConfigs && gamingHasGraphics && officeNoGraphics) {
                System.out.println("  PASS: Builder reusability works correctly");
                passCount++;
            } else {
                System.out.println("  FAIL: Builder reusability failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 7: Boundary Values
     * 
     * Purpose: Verify that the Builder works correctly with boundary values and special characters.
     * 
     * Steps:
     * 1. Create Computer objects with boolean boundary values
     * 2. Create Computer objects with single character strings
     * 3. Verify that boundary values are handled correctly
     * 
     * Expected Outcome:
     * - Boolean values should work correctly
     * - Single character strings should be accepted
     * - Test should PASS if boundary values are handled properly
     */
    private static void testBoundaryValues() {
        System.out.println("Test 7: Boundary Values");
        try {
            Computer wifiTrue = new Computer.Builder("Intel i3", "8GB")
                    .setWiFi(true)
                    .setBluetooth(false)
                    .build();
            
            Computer wifiFalse = new Computer.Builder("Intel i3", "8GB")
                    .setWiFi(false)
                    .setBluetooth(true)
                    .build();
            
            boolean wifiTrueCorrect = wifiTrue.hasWiFi() && !wifiTrue.hasBluetooth();
            boolean wifiFalseCorrect = !wifiFalse.hasWiFi() && wifiFalse.hasBluetooth();
            
            Computer singleChar = new Computer.Builder("A", "B")
                    .setStorage("C")
                    .setGraphicsCard("D")
                    .build();
            
            boolean singleCharCorrect = singleChar.getCPU().equals("A") && 
                                      singleChar.getRAM().equals("B") &&
                                      singleChar.getStorage().equals("C");
            
            System.out.println("  - WiFi true correct: " + wifiTrueCorrect);
            System.out.println("  - WiFi false correct: " + wifiFalseCorrect);
            System.out.println("  - Single char correct: " + singleCharCorrect);
            
            if (wifiTrueCorrect && wifiFalseCorrect && singleCharCorrect) {
                System.out.println("  PASS: Boundary values handled correctly");
                passCount++;
            } else {
                System.out.println("  FAIL: Boundary values not handled correctly");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 8: Validation Scenarios
     * 
     * Purpose: Verify that the Computer object's toString() method works correctly and provides meaningful output.
     * 
     * Steps:
     * 1. Create a Computer object with various parameters
     * 2. Call toString() method
     * 3. Verify that the output contains expected information
     * 
     * Expected Outcome:
     * - toString() should return a meaningful string representation
     * - String should contain all set parameters
     * - Test should PASS if toString() works correctly
     */
    private static void testValidationScenarios() {
        System.out.println("Test 8: Validation Scenarios");
        try {
            Computer computer = new Computer.Builder("Intel i7", "32GB")
                    .setStorage("1TB SSD")
                    .build();
            
            String output = computer.toString();
            boolean containsCPU = output.contains("Intel i7");
            boolean containsRAM = output.contains("32GB");
            boolean containsStorage = output.contains("1TB SSD");
            
            System.out.println("  - Contains CPU: " + containsCPU);
            System.out.println("  - Contains RAM: " + containsRAM);
            System.out.println("  - Contains Storage: " + containsStorage);
            System.out.println("  - toString output: " + output.substring(0, Math.min(50, output.length())) + "...");
            
            if (containsCPU && containsRAM && containsStorage) {
                System.out.println("  PASS: toString() method works correctly");
                passCount++;
            } else {
                System.out.println("  FAIL: toString() method failed");
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
            System.out.println("ALL TESTS PASSED! Builder Pattern implementation is working correctly.");
        } else {
            System.out.println("Some tests failed. Please review the implementation.");
        }
    }
}