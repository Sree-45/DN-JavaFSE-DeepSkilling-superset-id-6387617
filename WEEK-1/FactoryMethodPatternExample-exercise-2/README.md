
# Factory Method Pattern Implementation

## Overview
This project demonstrates the Factory Method Pattern implementation for creating different types of documents (Word, PDF, Excel). The pattern provides an abstraction layer for object creation, allowing the system to create objects without specifying their exact classes.

## Package Structure and Abstraction Layers

### 1. DocumentInterfaces Package
**Location:** `src/DocumentInterfaces/`
**Purpose:** Defines the product interfaces (abstraction layer for products)

**Files:**
- `WordDocument.java` - Interface for Word document operations
- `PdfDocument.java` - Interface for PDF document operations  
- `ExcelDocument.java` - Interface for Excel document operations

**Abstraction Level:** Product Interface Layer
- Each interface defines the contract for a specific document type
- Provides abstraction over concrete document implementations
- Defines common operations (open, save, close) plus type-specific operations

### 2. AbstractDocumentFactory Package
**Location:** `src/AbstractDocumentFactory/`
**Purpose:** Defines the abstract factory (abstraction layer for creators)

**Files:**
- `DocumentFactory.java` - Abstract factory class with factory method

**Abstraction Level:** Creator Interface Layer
- Contains the abstract `createDocument()` method
- Defines the factory method pattern structure
- Provides abstraction over concrete factory implementations

### 3. ConceteDocumentFactory Package
**Location:** `src/ConceteDocumentFactory/`
**Purpose:** Implements concrete factories (concrete creators)

**Files:**
- `WordDocumentFactory.java` - Creates Word documents
- `PdfDocumentFactory.java` - Creates PDF documents
- `ExcelDocumentFactory.java` - Creates Excel documents

**Abstraction Level:** Concrete Creator Layer
- Each factory extends the abstract DocumentFactory
- Implements the `createDocument()` method to return specific document types
- Encapsulates the logic for creating specific document instances

### 4. ConcreteDocumentClasses Package
**Location:** `src/ConcreteDocumentClasses/`
**Purpose:** Implements concrete products (concrete implementations)

**Files:**
- `WordDocumentImpl.java` - Concrete Word document implementation
- `PdfDocumentImpl.java` - Concrete PDF document implementation
- `ExcelDocumentImpl.java` - Concrete Excel document implementation

**Abstraction Level:** Concrete Product Layer
- Each class implements its respective document interface
- Provides actual implementation of document operations
- Contains the real business logic for document handling

## Factory Method Pattern Abstraction Points

### 1. Product Abstraction
- **Where:** DocumentInterfaces package
- **What:** Interfaces define contracts without implementation details
- **Why:** Allows client code to work with any document type through common interface

### 2. Creator Abstraction
- **Where:** AbstractDocumentFactory package
- **What:** Abstract factory defines creation method without specifying concrete classes
- **Why:** Decouples client code from specific product implementations

### 3. Creation Logic Abstraction
- **Where:** ConceteDocumentFactory package
- **What:** Each concrete factory encapsulates creation logic for specific products
- **Why:** Centralizes object creation logic and makes it easy to modify/extend

### 4. Implementation Abstraction
- **Where:** ConcreteDocumentClasses package
- **What:** Concrete implementations are hidden behind interfaces
- **Why:** Allows changing implementations without affecting client code

## Pattern Benefits Demonstrated

1. **Loose Coupling:** Client code depends on abstractions, not concrete classes
2. **Extensibility:** Easy to add new document types by creating new interfaces, factories, and implementations
3. **Single Responsibility:** Each factory is responsible for creating one type of document
4. **Open/Closed Principle:** Open for extension (new document types), closed for modification (existing code)

## Usage Example
```java
// Client code works with abstractions
DocumentFactory factory = new WordDocumentFactory();
WordDocument document = (WordDocument) factory.createDocument();
document.open();
document.save();
document.close();
```

## Test Coverage
The `FactoryMethodPatternTest.java` file provides comprehensive testing of:
- Object creation correctness
- Interface compliance
- Polymorphic behavior
- Inheritance structure
- Null safety
- Method functionality
- Instance independence
