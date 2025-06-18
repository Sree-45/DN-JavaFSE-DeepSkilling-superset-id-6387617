import AbstractDocumentFactory.DocumentFactory;
import ConceteDocumentFactory.WordDocumentFactory;
import ConceteDocumentFactory.PdfDocumentFactory;
import ConceteDocumentFactory.ExcelDocumentFactory;
import DocumentInterfaces.WordDocument;
import DocumentInterfaces.PdfDocument;
import DocumentInterfaces.ExcelDocument;
import ConcreteDocumentClasses.WordDocumentImpl;
import ConcreteDocumentClasses.PdfDocumentImpl;
import ConcreteDocumentClasses.ExcelDocumentImpl;

/**
 * Factory Method Pattern Test Suite
 * 
 * This test suite validates the Factory Method Pattern implementation by ensuring:
 * - Correct Object Creation: Each factory creates the right type of document
 * - Interface Compliance: Created objects properly implement their interfaces
 * - Polymorphism: All factories work through the common abstract factory interface
 * - Inheritance: Proper class hierarchy is maintained
 * - Null Safety: No null objects or values are returned
 * - Method Functionality: All document operations work correctly
 * - Instance Independence: Multiple factory instances create separate objects
 */
public class FactoryMethodPatternTest {
    
    private static int testCount = 0;
    private static int passCount = 0;
    
    public static void main(String[] args) {
        System.out.println("=== Factory Method Pattern Test Suite ===\n");
        
        // Run all tests
        testWordDocumentCreation();
        testPdfDocumentCreation();
        testExcelDocumentCreation();
        testPolymorphicBehavior();
        testFactoryReturnsCorrectType();
        testDocumentOperations();
        testMultipleFactoryInstances();
        testFactoryInheritance();
        testNullSafety();
        
        // Print test summary
        printTestSummary();
    }
    
    /**
     * Test 1: Word Document Creation
     * 
     * Purpose: Verify that the WordDocumentFactory correctly creates WordDocument instances with proper implementation.
     * 
     * Steps:
     * 1. Create a new WordDocumentFactory instance
     * 2. Call createDocument() method on the factory
     * 3. Check if the returned object is an instance of WordDocument interface
     * 4. Check if the returned object is an instance of WordDocumentImpl class
     * 5. Verify the document version matches expected value "Microsoft Word 2023 (.docx)"
     * 
     * Expected Outcome:
     * - Factory should create an object of type WordDocumentImpl
     * - Object should implement WordDocument interface
     * - getWordVersion() should return "Microsoft Word 2023 (.docx)"
     * - Test should PASS if all conditions are met
     */
    private static void testWordDocumentCreation() {
        System.out.println("Test 1: Word Document Creation");
        try {
            DocumentFactory factory = new WordDocumentFactory();
            Object doc = factory.createDocument();
            
            boolean isWordDoc = doc instanceof WordDocument;
            boolean isWordDocImpl = doc instanceof WordDocumentImpl;
            String version = isWordDoc ? ((WordDocument) doc).getWordVersion() : null;
            boolean hasCorrectVersion = "Microsoft Word 2023 (.docx)".equals(version);
            
            System.out.println("  - Factory created: " + doc.getClass().getSimpleName());
            System.out.println("  - Document version: " + version);
            System.out.println("  - Is WordDocument instance: " + isWordDoc);
            System.out.println("  - Is WordDocumentImpl instance: " + isWordDocImpl);
            
            if (isWordDoc && isWordDocImpl && hasCorrectVersion) {
                System.out.println("  PASS: Word document created successfully");
                passCount++;
            } else {
                System.out.println("  FAIL: Word document creation failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 2: PDF Document Creation
     * 
     * Purpose: Verify that the PdfDocumentFactory correctly creates PdfDocument instances with proper implementation.
     * 
     * Steps:
     * 1. Create a new PdfDocumentFactory instance
     * 2. Call createDocument() method on the factory
     * 3. Check if the returned object is an instance of PdfDocument interface
     * 4. Check if the returned object is an instance of PdfDocumentImpl class
     * 5. Verify the document version matches expected value "Adobe PDF (.pdf)"
     * 
     * Expected Outcome:
     * - Factory should create an object of type PdfDocumentImpl
     * - Object should implement PdfDocument interface
     * - getPdfVersion() should return "Adobe PDF (.pdf)"
     * - Test should PASS if all conditions are met
     */
    private static void testPdfDocumentCreation() {
        System.out.println("Test 2: PDF Document Creation");
        try {
            DocumentFactory factory = new PdfDocumentFactory();
            Object doc = factory.createDocument();
            
            boolean isPdfDoc = doc instanceof PdfDocument;
            boolean isPdfDocImpl = doc instanceof PdfDocumentImpl;
            String version = isPdfDoc ? ((PdfDocument) doc).getPdfVersion() : null;
            boolean hasCorrectVersion = "Adobe PDF (.pdf)".equals(version);
            
            System.out.println("  - Factory created: " + doc.getClass().getSimpleName());
            System.out.println("  - Document version: " + version);
            System.out.println("  - Is PdfDocument instance: " + isPdfDoc);
            System.out.println("  - Is PdfDocumentImpl instance: " + isPdfDocImpl);
            
            if (isPdfDoc && isPdfDocImpl && hasCorrectVersion) {
                System.out.println("  PASS: PDF document created successfully");
                passCount++;
            } else {
                System.out.println("  FAIL: PDF document creation failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 3: Excel Document Creation
     * 
     * Purpose: Verify that the ExcelDocumentFactory correctly creates ExcelDocument instances with proper implementation.
     * 
     * Steps:
     * 1. Create a new ExcelDocumentFactory instance
     * 2. Call createDocument() method on the factory
     * 3. Check if the returned object is an instance of ExcelDocument interface
     * 4. Check if the returned object is an instance of ExcelDocumentImpl class
     * 5. Verify the document version matches expected value "Microsoft Excel 2023 (.xlsx)"
     * 
     * Expected Outcome:
     * - Factory should create an object of type ExcelDocumentImpl
     * - Object should implement ExcelDocument interface
     * - getExcelVersion() should return "Microsoft Excel 2023 (.xlsx)"
     * - Test should PASS if all conditions are met
     */
    private static void testExcelDocumentCreation() {
        System.out.println("Test 3: Excel Document Creation");
        try {
            DocumentFactory factory = new ExcelDocumentFactory();
            Object doc = factory.createDocument();
            
            boolean isExcelDoc = doc instanceof ExcelDocument;
            boolean isExcelDocImpl = doc instanceof ExcelDocumentImpl;
            String version = isExcelDoc ? ((ExcelDocument) doc).getExcelVersion() : null;
            boolean hasCorrectVersion = "Microsoft Excel 2023 (.xlsx)".equals(version);
            
            System.out.println("  - Factory created: " + doc.getClass().getSimpleName());
            System.out.println("  - Document version: " + version);
            System.out.println("  - Is ExcelDocument instance: " + isExcelDoc);
            System.out.println("  - Is ExcelDocumentImpl instance: " + isExcelDocImpl);
            
            if (isExcelDoc && isExcelDocImpl && hasCorrectVersion) {
                System.out.println("  PASS: Excel document created successfully");
                passCount++;
            } else {
                System.out.println("  FAIL: Excel document creation failed");
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
     * Purpose: Verify that all factories work polymorphically through the DocumentFactory interface and return correct document types.
     * 
     * Steps:
     * 1. Create an array of different factory types (WordDocumentFactory, PdfDocumentFactory, ExcelDocumentFactory)
     * 2. Create documents using each factory
     * 3. Check the type of each created document using instanceof
     * 4. Extract version information from each document using appropriate methods
     * 5. Compare actual versions with expected versions
     * 
     * Expected Outcome:
     * - WordDocumentFactory should create document with version "Microsoft Word 2023 (.docx)"
     * - PdfDocumentFactory should create document with version "Adobe PDF (.pdf)"
     * - ExcelDocumentFactory should create document with version "Microsoft Excel 2023 (.xlsx)"
     * - All factories should work through the common DocumentFactory interface
     * - Test should PASS if all versions match expected values
     */
    private static void testPolymorphicBehavior() {
        System.out.println("Test 4: Polymorphic Behavior");
        try {
            DocumentFactory[] factories = {
                new WordDocumentFactory(),
                new PdfDocumentFactory(),
                new ExcelDocumentFactory()
            };
            
            String[] expectedVersions = {
                "Microsoft Word 2023 (.docx)",
                "Adobe PDF (.pdf)",
                "Microsoft Excel 2023 (.xlsx)"
            };
            
            boolean allCorrect = true;
            for (int i = 0; i < factories.length; i++) {
                Object doc = factories[i].createDocument();
                String actualVersion = "";
                
                if (doc instanceof WordDocument) {
                    actualVersion = ((WordDocument) doc).getWordVersion();
                } else if (doc instanceof PdfDocument) {
                    actualVersion = ((PdfDocument) doc).getPdfVersion();
                } else if (doc instanceof ExcelDocument) {
                    actualVersion = ((ExcelDocument) doc).getExcelVersion();
                }
                
                System.out.println("  - Factory " + (i+1) + " created: " + actualVersion);
                
                if (!expectedVersions[i].equals(actualVersion)) {
                    allCorrect = false;
                }
            }
            
            if (allCorrect) {
                System.out.println("  PASS: All factories work polymorphically");
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
     * Test 5: Factory Returns Document Interface
     * 
     * Purpose: Verify that factories return objects that properly implement their respective document interfaces and can call interface methods.
     * 
     * Steps:
     * 1. Create a WordDocumentFactory instance
     * 2. Call createDocument() method
     * 3. Check if returned object implements WordDocument interface
     * 4. Cast object to WordDocument and call getWordVersion() method
     * 5. Verify method call returns non-null value
     * 
     * Expected Outcome:
     * - Factory should return object that implements WordDocument interface
     * - Object should be castable to WordDocument type
     * - getWordVersion() method should be callable and return non-null value
     * - Test should PASS if interface contract is properly implemented
     */
    private static void testFactoryReturnsCorrectType() {
        System.out.println("Test 5: Factory Returns Document Interface");
        try {
            DocumentFactory factory = new WordDocumentFactory();
            Object doc = factory.createDocument();
            
            boolean implementsWordDocument = doc instanceof WordDocument;
            boolean canCallInterfaceMethods = false;
            
            if (implementsWordDocument) {
                WordDocument wordDoc = (WordDocument) doc;
                canCallInterfaceMethods = wordDoc.getWordVersion() != null;
            }
            
            System.out.println("  - Returned object implements WordDocument: " + implementsWordDocument);
            System.out.println("  - Can call interface methods: " + canCallInterfaceMethods);
            
            if (implementsWordDocument && canCallInterfaceMethods) {
                System.out.println("  PASS: Factory returns WordDocument interface");
                passCount++;
            } else {
                System.out.println("  FAIL: Factory doesn't return WordDocument interface");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 6: Document Operations
     * 
     * Purpose: Verify that created documents can perform their basic operations without throwing exceptions.
     * 
     * Steps:
     * 1. Create a PdfDocumentFactory instance
     * 2. Create a document using the factory
     * 3. Cast the document to PdfDocument interface
     * 4. Call basic operations: open(), save(), close(), addWatermark()
     * 5. Verify all operations execute without exceptions
     * 
     * Expected Outcome:
     * - Document should be castable to PdfDocument interface
     * - All method calls (open, save, close, addWatermark) should execute successfully
     * - No exceptions should be thrown during operation execution
     * - Test should PASS if all operations complete without errors
     */
    private static void testDocumentOperations() {
        System.out.println("Test 6: Document Operations");
        try {
            DocumentFactory factory = new PdfDocumentFactory();
            Object doc = factory.createDocument();
            
            if (doc instanceof PdfDocument) {
                PdfDocument pdfDoc = (PdfDocument) doc;
                System.out.println("  - Testing document operations:");
                pdfDoc.open();
                pdfDoc.save();
                pdfDoc.close();
                pdfDoc.addWatermark();
                
                System.out.println("  PASS: All document operations executed without errors");
                passCount++;
            } else {
                System.out.println("  FAIL: Document is not a PdfDocument instance");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Document operations failed - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 7: Multiple Factory Instances
     * 
     * Purpose: Verify that multiple instances of the same factory type create different document instances.
     * 
     * Steps:
     * 1. Create two separate WordDocumentFactory instances
     * 2. Create documents using both factories
     * 3. Check if both documents are instances of WordDocument
     * 4. Verify that the two document instances are different objects (not the same reference)
     * 5. Confirm both documents have correct type
     * 
     * Expected Outcome:
     * - Both factories should create WordDocument instances
     * - Both documents should be of type WordDocumentImpl
     * - The two document instances should be different objects (different memory references)
     * - Test should PASS if factories create separate instances
     */
    private static void testMultipleFactoryInstances() {
        System.out.println("Test 7: Multiple Factory Instances");
        try {
            DocumentFactory factory1 = new WordDocumentFactory();
            DocumentFactory factory2 = new WordDocumentFactory();
            
            Object doc1 = factory1.createDocument();
            Object doc2 = factory2.createDocument();
            
            boolean bothAreWordDocs = (doc1 instanceof WordDocument) && (doc2 instanceof WordDocument);
            boolean differentInstances = doc1 != doc2;
            
            System.out.println("  - Factory1 created: " + doc1.getClass().getSimpleName());
            System.out.println("  - Factory2 created: " + doc2.getClass().getSimpleName());
            System.out.println("  - Different document instances: " + differentInstances);
            
            if (bothAreWordDocs && differentInstances) {
                System.out.println("  PASS: Multiple factory instances work correctly");
                passCount++;
            } else {
                System.out.println("  FAIL: Multiple factory instances failed");
            }
        } catch (Exception e) {
            System.out.println("  FAIL: Exception occurred - " + e.getMessage());
        }
        testCount++;
        System.out.println();
    }
    
    /**
     * Test 8: Factory Inheritance Structure
     * 
     * Purpose: Verify that concrete factory classes properly inherit from the abstract DocumentFactory class.
     * 
     * Steps:
     * 1. Create instances of WordDocumentFactory and PdfDocumentFactory
     * 2. Check if both instances are also instances of DocumentFactory (parent class)
     * 3. Verify inheritance relationship is correctly established
     * 
     * Expected Outcome:
     * - WordDocumentFactory should be an instance of DocumentFactory
     * - PdfDocumentFactory should be an instance of DocumentFactory
     * - Both should demonstrate proper inheritance from abstract factory class
     * - Test should PASS if inheritance structure is correct
     */
    private static void testFactoryInheritance() {
        System.out.println("Test 8: Factory Inheritance Structure");
        try {
            DocumentFactory wordFactory = new WordDocumentFactory();
            DocumentFactory pdfFactory = new PdfDocumentFactory();
            
            boolean wordExtendsFactory = wordFactory instanceof DocumentFactory;
            boolean pdfExtendsFactory = pdfFactory instanceof DocumentFactory;
            
            System.out.println("  - WordDocumentFactory extends DocumentFactory: " + wordExtendsFactory);
            System.out.println("  - PdfDocumentFactory extends DocumentFactory: " + pdfExtendsFactory);
            
            if (wordExtendsFactory && pdfExtendsFactory) {
                System.out.println("  PASS: Factory inheritance structure correct");
                passCount++;
            } else {
                System.out.println("  FAIL: Factory inheritance structure incorrect");
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
     * Purpose: Verify that factories create non-null documents with valid, non-empty version information.
     * 
     * Steps:
     * 1. Create an ExcelDocumentFactory instance
     * 2. Create a document using the factory
     * 3. Check if document is not null
     * 4. Verify document is instance of ExcelDocument interface
     * 5. Call getExcelVersion() and check if result is not null and not empty
     * 
     * Expected Outcome:
     * - Factory should return non-null document object
     * - Document should implement ExcelDocument interface
     * - getExcelVersion() should return non-null string
     * - Version string should not be empty
     * - Test should PASS if all null safety checks pass
     */
    private static void testNullSafety() {
        System.out.println("Test 9: Null Safety");
        try {
            DocumentFactory factory = new ExcelDocumentFactory();
            Object doc = factory.createDocument();
            
            boolean docNotNull = doc != null;
            boolean isExcelDoc = doc instanceof ExcelDocument;
            boolean versionNotNull = false;
            boolean versionNotEmpty = false;
            
            if (isExcelDoc) {
                String version = ((ExcelDocument) doc).getExcelVersion();
                versionNotNull = version != null;
                versionNotEmpty = !version.isEmpty();
            }
            
            System.out.println("  - Document is not null: " + docNotNull);
            System.out.println("  - Document is ExcelDocument: " + isExcelDoc);
            System.out.println("  - Document version is not null: " + versionNotNull);
            System.out.println("  - Document version is not empty: " + versionNotEmpty);
            
            if (docNotNull && isExcelDoc && versionNotNull && versionNotEmpty) {
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
            System.out.println("ALL TESTS PASSED! Factory Method Pattern implementation is working correctly.");
        } else {
            System.out.println("Some tests failed. Please review the implementation.");
        }
    }
}
