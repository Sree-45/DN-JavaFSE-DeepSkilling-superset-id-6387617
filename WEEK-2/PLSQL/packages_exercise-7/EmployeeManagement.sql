-- Package Specification
CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(
        p_id NUMBER,
        p_name VARCHAR2,
        p_position VARCHAR2,
        p_salary NUMBER,
        p_department VARCHAR2,
        p_hiredate DATE
    );
    PROCEDURE UpdateEmployee(
        p_id NUMBER,
        p_name VARCHAR2,
        p_position VARCHAR2,
        p_salary NUMBER,
        p_department VARCHAR2
    );
    FUNCTION GetAnnualSalary(p_id NUMBER) RETURN NUMBER;
END EmployeeManagement;
/

-- Package Body
CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee(
        p_id NUMBER,
        p_name VARCHAR2,
        p_position VARCHAR2,
        p_salary NUMBER,
        p_department VARCHAR2,
        p_hiredate DATE
    ) IS
    BEGIN
        INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (p_id, p_name, p_position, p_salary, p_department, p_hiredate);
    END HireEmployee;

    PROCEDURE UpdateEmployee(
        p_id NUMBER,
        p_name VARCHAR2,
        p_position VARCHAR2,
        p_salary NUMBER,
        p_department VARCHAR2
    ) IS
    BEGIN
        UPDATE Employees
        SET Name = p_name,
            Position = p_position,
            Salary = p_salary,
            Department = p_department
        WHERE EmployeeID = p_id;
    END UpdateEmployee;

    FUNCTION GetAnnualSalary(p_id NUMBER) RETURN NUMBER IS
        v_salary NUMBER;
    BEGIN
        SELECT Salary INTO v_salary
        FROM Employees
        WHERE EmployeeID = p_id;
        RETURN v_salary * 12;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
    END GetAnnualSalary;

END EmployeeManagement;
/

-- Hire a new employee
BEGIN
    EmployeeManagement.HireEmployee(10, 'Jane Doe', 'Manager', 5000, 'HR', TO_DATE('2023-01-15', 'YYYY-MM-DD'));
END;
/

-- Update employee details
BEGIN
    EmployeeManagement.UpdateEmployee(10, 'Jane Doe', 'Senior Manager', 6000, 'HR');
END;
/

-- Get annual salary
SET SERVEROUTPUT ON;
DECLARE
    v_annual_salary NUMBER;
BEGIN
    v_annual_salary := EmployeeManagement.GetAnnualSalary(10);
    DBMS_OUTPUT.PUT_LINE('Annual Salary: ' || v_annual_salary);
END;
/