# Hibernate Annotation Configuration Implementation Walkthrough

## Overview
This hands-on session provides a comprehensive explanation of Hibernate's annotation-based configuration approach for Object-Relational Mapping (ORM). We'll explore how to use JPA annotations to map Java objects to database tables, eliminating the need for XML mapping files and keeping mapping metadata directly in the Java code.

## Table of Contents
1. [Object-Relational Mapping with Annotations](#object-relational-mapping-with-annotations)
2. [Core Hibernate Annotations](#core-hibernate-annotations)
3. [Hibernate Configuration (hibernate.cfg.xml)](#hibernate-configuration-hibernatecfgxml)
4. [Complete Code Example](#complete-code-example)
5. [Setup and Execution](#setup-and-execution)

## Object-Relational Mapping with Annotations

### Advantages of Annotation-Based Mapping
- **Code and Metadata Unity**: All mapping information is contained within the Java class itself
- **Better Maintainability**: Developers can see both the code structure and database mapping simultaneously
- **Less Configuration**: No need for separate XML mapping files
- **IDE Support**: Better IDE assistance and refactoring capabilities
- **JPA Standard**: Uses standard JPA annotations, making code portable across different ORM frameworks

### Database Table Structure
The Employee table that we'll be mapping to:
```sql
CREATE TABLE EMPLOYEE (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(20) DEFAULT NULL,
    last_name VARCHAR(20) DEFAULT NULL,
    salary INT DEFAULT NULL,
    PRIMARY KEY (id)
);
```

### Annotated Employee Entity Class
```java
import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "salary")
    private int salary;
    
    // Default constructor (required by JPA)
    public Employee() {}
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public int getSalary() { return salary; }
    public void setSalary(int salary) { this.salary = salary; }
}
```

## Core Hibernate Annotations

### 1. @Entity Annotation
```java
@Entity
public class Employee {
    // class body
}
```

**Purpose**: Marks a Java class as a JPA entity (persistent class)

**Key Features**:
- **Entity Bean Requirement**: The class becomes an entity bean that will be managed by the persistence context
- **No-Argument Constructor**: Must have a default constructor with at least protected visibility
- **JPA Standard**: Part of the `javax.persistence` package (JPA specification)
- **Persistence Context**: Objects of this class can be persisted, updated, and retrieved from the database

**What it does**:
- Tells Hibernate that this class represents a database entity
- Enables the class to be used with EntityManager operations
- Makes the class eligible for automatic table creation (with `hbm2ddl.auto` property)

### 2. @Table Annotation
```java
@Table(name = "EMPLOYEE")
public class Employee {
    // class body
}
```

**Purpose**: Specifies the database table details for the entity

**Key Attributes**:
- **name**: Specifies the table name in the database
- **catalog**: Specifies the catalog name
- **schema**: Specifies the schema name
- **uniqueConstraints**: Defines unique constraints on table columns

**Example with additional attributes**:
```java
@Table(name = "EMPLOYEE", 
       catalog = "HR_CATALOG",
       schema = "HR_SCHEMA",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"first_name", "last_name"})
       })
```

**What it does**:
- Maps the Java class to a specific database table
- If not specified, Hibernate uses the class name as the table name
- Provides fine-grained control over table-level constraints

### 3. @Id Annotation
```java
@Id
@Column(name = "id")
private int id;
```

**Purpose**: Marks a field as the primary key of the entity

**Key Features**:
- **Primary Key Mapping**: Identifies the unique identifier for the entity
- **Required**: Every entity must have exactly one @Id field
- **Access Strategy**: Can be placed on field or getter method
- **Composite Keys**: Can be used with @EmbeddedId for composite primary keys

**Access Strategy Impact**:
- **Field Access**: When @Id is on a field, Hibernate accesses all properties directly through fields
- **Property Access**: When @Id is on a getter method, Hibernate accesses properties through getter/setter methods

### 4. @GeneratedValue Annotation
```java
@Id
@GeneratedValue
@Column(name = "id")
private int id;
```

**Purpose**: Specifies how the primary key should be generated automatically

**Generation Strategies**:
```java
@GeneratedValue(strategy = GenerationType.AUTO)     // Default - lets Hibernate choose
@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
@GeneratedValue(strategy = GenerationType.SEQUENCE) // Database sequence
@GeneratedValue(strategy = GenerationType.TABLE)    // Table-based generation
```

**Key Features**:
- **Database Portability**: AUTO strategy makes code portable across different databases
- **Performance**: Different strategies have different performance characteristics
- **Custom Generators**: Can specify custom generator classes

**What it does**:
- Automatically generates primary key values
- Eliminates need to manually assign IDs
- Ensures unique primary key values

### 5. @Column Annotation
```java
@Column(name = "first_name")
private String firstName;
```

**Purpose**: Specifies the mapping between a Java field and a database column

**Key Attributes**:
- **name**: Database column name
- **length**: Column size (especially for String fields)
- **nullable**: Whether column can contain NULL values
- **unique**: Whether column values must be unique
- **insertable**: Whether column is included in INSERT statements
- **updatable**: Whether column is included in UPDATE statements

**Detailed Examples**:
```java
@Column(name = "first_name", 
        length = 50, 
        nullable = false)
private String firstName;

@Column(name = "email", 
        unique = true, 
        nullable = false)
private String email;

@Column(name = "salary", 
        precision = 10, 
        scale = 2)
private BigDecimal salary;
```

**What it does**:
- Maps Java fields to database columns
- Provides fine-grained control over column characteristics
- If not specified, Hibernate uses the field name as the column name

## Hibernate Configuration (hibernate.cfg.xml)

### Configuration File Structure
```xml
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE hibernate-configuration SYSTEM 
    "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">

<hibernate-configuration>
    <session-factory>
        <!-- Database connection properties -->
        <property name="hibernate.dialect">
            org.hibernate.dialect.MySQLDialect
        </property>
        <property name="hibernate.connection.driver_class">
            com.mysql.jdbc.Driver
        </property>
        <property name="hibernate.connection.url">
            jdbc:mysql://localhost/test
        </property>
        <property name="hibernate.connection.username">
            root
        </property>
        <property name="hibernate.connection.password">
            password
        </property>
        
        <!-- Optional: Additional Hibernate properties -->
        <property name="hibernate.hbm2ddl.auto">update</property>
        <property name="hibernate.show_sql">true</property>
        <property name="hibernate.format_sql">true</property>
    </session-factory>
</hibernate-configuration>
```

### Key Configuration Properties Explained

#### 1. Dialect
```xml
<property name="hibernate.dialect">
    org.hibernate.dialect.MySQLDialect
</property>
```
**Purpose**: Specifies the SQL dialect for the target database

**Common Dialects**:
- MySQL: `org.hibernate.dialect.MySQLDialect`
- PostgreSQL: `org.hibernate.dialect.PostgreSQLDialect`
- Oracle: `org.hibernate.dialect.OracleDialect`
- SQL Server: `org.hibernate.dialect.SQLServerDialect`
- H2: `org.hibernate.dialect.H2Dialect`

**What it does**:
- Generates database-specific SQL
- Handles database-specific data types
- Optimizes queries for the target database

#### 2. Driver
```xml
<property name="hibernate.connection.driver_class">
    com.mysql.jdbc.Driver
</property>
```
**Purpose**: Specifies the JDBC driver class for database connectivity

**Common Drivers**:
- MySQL: `com.mysql.jdbc.Driver` or `com.mysql.cj.jdbc.Driver` (newer versions)
- PostgreSQL: `org.postgresql.Driver`
- Oracle: `oracle.jdbc.driver.OracleDriver`
- SQL Server: `com.microsoft.sqlserver.jdbc.SQLServerDriver`

#### 3. Connection URL
```xml
<property name="hibernate.connection.url">
    jdbc:mysql://localhost:3306/test
</property>
```
**Purpose**: Specifies the database connection URL

**URL Format Examples**:
- MySQL: `jdbc:mysql://hostname:port/database_name`
- PostgreSQL: `jdbc:postgresql://hostname:port/database_name`
- Oracle: `jdbc:oracle:thin:@hostname:port:SID`
- SQL Server: `jdbc:sqlserver://hostname:port;databaseName=database_name`

#### 4. Username
```xml
<property name="hibernate.connection.username">
    root
</property>
```
**Purpose**: Database username for authentication

#### 5. Password
```xml
<property name="hibernate.connection.password">
    password
</property>
```
**Purpose**: Database password for authentication

### Additional Important Properties
```xml
<!-- Automatically create/update database schema -->
<property name="hibernate.hbm2ddl.auto">update</property>

<!-- Show SQL statements in console -->
<property name="hibernate.show_sql">true</property>

<!-- Format SQL statements for readability -->
<property name="hibernate.format_sql">true</property>

<!-- Connection pool settings -->
<property name="hibernate.connection.pool_size">10</property>
```

## Complete Code Example

### SessionFactory Configuration for Annotations
```java
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

public class ManageEmployee {
    private static SessionFactory factory;
    
    public static void main(String[] args) {
        try {
            factory = new AnnotationConfiguration()
                .configure()  // reads hibernate.cfg.xml
                .addAnnotatedClass(Employee.class)  // registers annotated class
                .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        
        ManageEmployee ME = new ManageEmployee();
        
        // Perform CRUD operations
        Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
        Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
        Integer empID3 = ME.addEmployee("John", "Paul", 10000);
        
        ME.listEmployees();
        ME.updateEmployee(empID1, 5000);
        ME.deleteEmployee(empID2);
        ME.listEmployees();
    }
    
    // CRUD method implementations...
}
```

### CRUD Operations Implementation
```java
// CREATE - Adding a new employee
public Integer addEmployee(String fname, String lname, int salary) {
    Session session = factory.openSession();
    Transaction tx = null;
    Integer employeeID = null;
    
    try {
        tx = session.beginTransaction();
        Employee employee = new Employee();
        employee.setFirstName(fname);
        employee.setLastName(lname);
        employee.setSalary(salary);
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

// READ - Listing all employees
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

// UPDATE - Updating employee salary
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

// DELETE - Deleting an employee
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

## Setup and Execution

### Prerequisites
1. **Java Development Kit (JDK) 5.0 or higher** (required for annotation support)
2. **Hibernate 3.x or higher** with annotation support
3. **Database server** (MySQL, PostgreSQL, etc.)
4. **JDBC driver** for your database

### Required JAR Files
- `hibernate-core.jar`
- `hibernate-annotations.jar`
- `hibernate-commons-annotations.jar`
- `ejb3-persistence.jar`
- Database-specific JDBC driver (e.g., `mysql-connector-java.jar`)

### Compilation Steps
1. **Create the database table**:
   ```sql
   CREATE TABLE EMPLOYEE (
       id INT NOT NULL AUTO_INCREMENT,
       first_name VARCHAR(20) DEFAULT NULL,
       last_name VARCHAR(20) DEFAULT NULL,
       salary INT DEFAULT NULL,
       PRIMARY KEY (id)
   );
   ```

2. **Configure hibernate.cfg.xml** with your database settings

3. **Compile the Java files**:
   ```bash
   javac -cp ".:hibernate-jars/*:mysql-connector.jar" *.java
   ```

4. **Run the application**:
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

## Key Differences from XML Configuration

### Annotation-Based Approach
- **No XML mapping files required**
- **Metadata embedded in Java code**
- **Better IDE support and refactoring**
- **JPA standard compliance**

### Configuration Changes
- Uses `AnnotationConfiguration` instead of `Configuration`
- Calls `addAnnotatedClass()` to register entity classes
- No need to specify mapping files in hibernate.cfg.xml

### Benefits
1. **Reduced Configuration**: No separate XML files to maintain
2. **Better Maintainability**: Code and mapping in one place
3. **IDE Support**: Better autocomplete and refactoring
4. **Standard Compliance**: Uses JPA annotations
5. **Type Safety**: Compile-time checking of annotations

## Best Practices

1. **Use JPA Annotations**: Prefer `javax.persistence` annotations over Hibernate-specific ones
2. **Access Strategy Consistency**: Be consistent with field vs. property access
3. **Meaningful Names**: Use descriptive names for tables and columns
4. **Validation**: Add appropriate constraints (nullable, unique, etc.)
5. **Performance**: Consider lazy loading and fetch strategies for relationships

This annotation-based approach provides a modern, maintainable way to implement Hibernate ORM with reduced configuration overhead and better integration with development tools.