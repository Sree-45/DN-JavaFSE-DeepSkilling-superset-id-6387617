# Hibernate XML Configuration Implementation Walkthrough

## Overview
This hands-on session provides a comprehensive explanation of Hibernate's XML-based configuration approach for Object-Relational Mapping (ORM). We'll walk through a complete Employee management system implementation that demonstrates core Hibernate concepts and operations.

## Table of Contents
1. [Object-Relational Mapping with XML Configuration](#object-relational-mapping-with-xml-configuration)
2. [Core Hibernate Components](#core-hibernate-components)
3. [CRUD Operations Implementation](#crud-operations-implementation)
4. [Complete Code Example](#complete-code-example)
5. [Setup and Execution](#setup-and-execution)

## Object-Relational Mapping with XML Configuration

### 1. POJO Class (Employee.java)
The foundation of Hibernate mapping starts with a Plain Old Java Object (POJO) that represents the data structure:

```java
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private int salary;
    
    // Default constructor (required by Hibernate)
    public Employee() {}
    
    // Parameterized constructor
    public Employee(String fname, String lname, int salary) {
        this.firstName = fname;
        this.lastName = lname;
        this.salary = salary;
    }
    
    // Getters and Setters (JavaBeans compliant)
    // ... getter/setter methods
}
```

**Key Points:**
- Must have a default constructor
- Should follow JavaBeans convention (getters/setters)
- Requires a unique identifier field (`id`)

### 2. Database Table Structure
The corresponding database table:
```sql
CREATE TABLE EMPLOYEE (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(20) DEFAULT NULL,
    last_name VARCHAR(20) DEFAULT NULL,
    salary INT DEFAULT NULL,
    PRIMARY KEY (id)
);
```

### 3. Hibernate Mapping File (Employee.hbm.xml)
The XML mapping file defines how Java objects map to database tables:

```xml
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE hibernate-mapping PUBLIC 
    "-//Hibernate/Hibernate Mapping DTD//EN"
    "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">

<hibernate-mapping>
    <class name="Employee" table="EMPLOYEE">
        <meta attribute="class-description">
            This class contains the employee detail.
        </meta>
        
        <!-- Primary Key Mapping -->
        <id name="id" type="int" column="id">
            <generator class="native"/>
        </id>
        
        <!-- Property Mappings -->
        <property name="firstName" column="first_name" type="string"/>
        <property name="lastName" column="last_name" type="string"/>
        <property name="salary" column="salary" type="int"/>
    </class>
</hibernate-mapping>
```

**XML Mapping Elements Explained:**
- `<hibernate-mapping>`: Root element containing all class mappings
- `<class>`: Maps Java class to database table
  - `name`: Fully qualified Java class name
  - `table`: Database table name
- `<id>`: Maps primary key property
  - `<generator class="native">`: Auto-generates primary key values
- `<property>`: Maps regular class properties to table columns
  - `name`: Java property name
  - `column`: Database column name
  - `type`: Hibernate data type

## Core Hibernate Components

### 1. SessionFactory
The `SessionFactory` is a thread-safe factory for creating `Session` instances. It's expensive to create and should be built once per application.

```java
private static SessionFactory factory;

// Initialize SessionFactory
try {
    factory = new Configuration().configure().buildSessionFactory();
} catch (Throwable ex) {
    System.err.println("Failed to create sessionFactory object." + ex);
    throw new ExceptionInInitializerError(ex);
}
```

**Key Characteristics:**
- Thread-safe and immutable
- Built from Configuration object
- Expensive to create (should be singleton)
- Reads hibernate.cfg.xml configuration file

### 2. Session
The `Session` represents a single database connection and is the primary interface for database operations.

```java
Session session = factory.openSession();
```

**Key Characteristics:**
- Not thread-safe
- Lightweight and short-lived
- Represents a single unit of work
- Should be closed after use

### 3. Transaction
Represents a database transaction and provides transaction boundaries.

```java
Transaction tx = null;
try {
    tx = session.beginTransaction();
    // Database operations
    tx.commit();
} catch (HibernateException e) {
    if (tx != null) tx.rollback();
    e.printStackTrace();
}
```

## CRUD Operations Implementation

### 1. beginTransaction()
Starts a new database transaction:
```java
Transaction tx = session.beginTransaction();
```

### 2. commit()
Commits the current transaction, making changes permanent:
```java
tx.commit();
```

### 3. rollback()
Rolls back the current transaction, undoing all changes:
```java
if (tx != null) tx.rollback();
```

### 4. session.save()
Persists a new object to the database:
```java
public Integer addEmployee(String fname, String lname, int salary) {
    Session session = factory.openSession();
    Transaction tx = null;
    Integer employeeID = null;
    
    try {
        tx = session.beginTransaction();
        Employee employee = new Employee(fname, lname, salary);
        employeeID = (Integer) session.save(employee);
        tx.commit();
    } catch (HibernateException e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
    } finally {
        session.close();
    }
    return employeeID;
}
```

### 5. session.createQuery().list()
Executes HQL queries and returns a list of results:
```java
public void listEmployees() {
    Session session = factory.openSession();
    Transaction tx = null;
    
    try {
        tx = session.beginTransaction();
        List employees = session.createQuery("FROM Employee").list();
        
        for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
            Employee employee = (Employee) iterator.next();
            System.out.print("First Name: " + employee.getFirstName());
            System.out.print(" Last Name: " + employee.getLastName());
            System.out.println(" Salary: " + employee.getSalary());
        }
        tx.commit();
    } catch (HibernateException e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
    } finally {
        session.close();
    }
}
```

### 6. session.get()
Retrieves an object by its primary key:
```java
public void updateEmployee(Integer employeeID, int salary) {
    Session session = factory.openSession();
    Transaction tx = null;
    
    try {
        tx = session.beginTransaction();
        Employee employee = (Employee) session.get(Employee.class, employeeID);
        employee.setSalary(salary);
        session.update(employee);
        tx.commit();
    } catch (HibernateException e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
    } finally {
        session.close();
    }
}
```

### 7. session.delete()
Removes an object from the database:
```java
public void deleteEmployee(Integer employeeID) {
    Session session = factory.openSession();
    Transaction tx = null;
    
    try {
        tx = session.beginTransaction();
        Employee employee = (Employee) session.get(Employee.class, employeeID);
        session.delete(employee);
        tx.commit();
    } catch (HibernateException e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
    } finally {
        session.close();
    }
}
```

## Complete Code Example

### ManageEmployee.java (Main Application)
```java
import java.util.List;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageEmployee {
    private static SessionFactory factory;
    
    public static void main(String[] args) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        
        ManageEmployee ME = new ManageEmployee();
        
        // Add employee records
        Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
        Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
        Integer empID3 = ME.addEmployee("John", "Paul", 10000);
        
        // List all employees
        ME.listEmployees();
        
        // Update employee record
        ME.updateEmployee(empID1, 5000);
        
        // Delete employee record
        ME.deleteEmployee(empID2);
        
        // List employees again
        ME.listEmployees();
    }
    
    // CRUD method implementations go here...
}
```

## Setup and Execution

### Prerequisites
1. Java Development Kit (JDK) 8 or higher
2. MySQL or any compatible database
3. Hibernate JAR files
4. Database connector JAR (e.g., mysql-connector-java)

### Configuration Files

#### hibernate.cfg.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
    "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
    "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">

<hibernate-configuration>
    <session-factory>
        <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
        <property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/test</property>
        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password">password</property>
        <property name="hibernate.hbm2ddl.auto">update</property>
        <property name="hibernate.show_sql">true</property>
        <mapping resource="Employee.hbm.xml"/>
    </session-factory>
</hibernate-configuration>
```

### Compilation and Execution Steps
1. Create the database table using the provided SQL script
2. Configure hibernate.cfg.xml with your database settings
3. Compile the Java files:
   ```bash
   javac -cp ".:hibernate-jars/*:mysql-connector.jar" *.java
   ```
4. Run the application:
   ```bash
   java -cp ".:hibernate-jars/*:mysql-connector.jar" ManageEmployee
   ```

### Expected Output
```
First Name: Zara Last Name: Ali Salary: 1000
First Name: Daisy Last Name: Das Salary: 5000
First Name: John Last Name: Paul Salary: 10000
First Name: Zara Last Name: Ali Salary: 5000
First Name: John Last Name: Paul Salary: 10000
```

## Key Learning Points

1. **XML Configuration**: Hibernate uses XML mapping files to define object-relational mappings
2. **SessionFactory**: Central configuration point and factory for Session objects
3. **Session Management**: Proper session lifecycle management with try-catch-finally blocks
4. **Transaction Management**: Always use transactions for database operations
5. **CRUD Operations**: Complete implementation of Create, Read, Update, Delete operations
6. **Error Handling**: Proper exception handling with transaction rollback
7. **Resource Management**: Always close sessions to prevent resource leaks

This implementation demonstrates the traditional XML-based approach to Hibernate configuration, which provides explicit and detailed control over object-relational mapping while maintaining separation between Java code and mapping configuration.