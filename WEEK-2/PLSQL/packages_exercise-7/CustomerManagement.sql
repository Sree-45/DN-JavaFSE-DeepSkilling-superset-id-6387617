-- Package Specification
CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer(p_id NUMBER, p_name VARCHAR2, p_dob DATE, p_balance NUMBER);
    PROCEDURE UpdateCustomer(p_id NUMBER, p_name VARCHAR2, p_dob DATE);
    FUNCTION GetCustomerBalance(p_id NUMBER) RETURN NUMBER;
END CustomerManagement;
/

-- Package Body
CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddCustomer(p_id NUMBER, p_name VARCHAR2, p_dob DATE, p_balance NUMBER) IS
    BEGIN
        INSERT INTO customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_id, p_name, p_dob, p_balance, SYSDATE);
    END AddCustomer;

    PROCEDURE UpdateCustomer(p_id NUMBER, p_name VARCHAR2, p_dob DATE) IS
    BEGIN
        UPDATE customers
        SET Name = p_name,
            DOB = p_dob,
            LastModified = SYSDATE
        WHERE CustomerID = p_id;
    END UpdateCustomer;

    FUNCTION GetCustomerBalance(p_id NUMBER) RETURN NUMBER IS
        v_balance NUMBER;
    BEGIN
        SELECT Balance INTO v_balance
        FROM customers
        WHERE CustomerID = p_id;
        RETURN v_balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
    END GetCustomerBalance;

END CustomerManagement;
/

-- Test AddCustomer
BEGIN
    CustomerManagement.AddCustomer(15, 'Smith', TO_DATE('1990-05-20', 'YYYY-MM-DD'), 10000);
END;
/

-- Test UpdateCustomer
BEGIN
    CustomerManagement.UpdateCustomer(11, 'Alice Johnson', TO_DATE('1990-12-19', 'YYYY-MM-DD'));
END;
/

-- Test GetCustomerBalance
SET SERVEROUTPUT ON;
DECLARE
    v_balance NUMBER;
BEGIN
    v_balance := CustomerManagement.GetCustomerBalance(1);
    DBMS_OUTPUT.PUT_LINE('Customer Balance: ' || v_balance);
END;
/