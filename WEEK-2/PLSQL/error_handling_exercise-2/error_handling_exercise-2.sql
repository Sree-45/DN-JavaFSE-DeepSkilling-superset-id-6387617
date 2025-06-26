--Scenario 1: Handle exceptions during fund transfers between accounts
CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    p_from_acct   IN NUMBER,
    p_to_acct     IN NUMBER,
    p_amount      IN NUMBER
) AS
    v_from_balance NUMBER;
BEGIN
    SELECT Balance INTO v_from_balance
    FROM Accounts
    WHERE AccountID = p_from_acct
    FOR UPDATE;

    IF v_from_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds in source account.');
    END IF;

    UPDATE Accounts
    SET Balance = Balance - p_amount
    WHERE AccountID = p_from_acct;

    UPDATE Accounts
    SET Balance = Balance + p_amount
    WHERE AccountID = p_to_acct;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transfer successful.');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        ROLLBACK;
END SafeTransferFunds;
/

SET SERVEROUTPUT ON;
BEGIN
    SafeTransferFunds(3, 4, 100);
    SafeTransferFunds(5, 6, 100000);
END;
/


--Scenario 2: Manage errors when updating employee salaries.
CREATE OR REPLACE PROCEDURE UpdateSalary(
    p_employee_id IN NUMBER,
    p_percent     IN NUMBER
) AS
    v_old_salary Employees.Salary%TYPE;
BEGIN
    SELECT Salary INTO v_old_salary
    FROM Employees
    WHERE EmployeeID = p_employee_id
    FOR UPDATE;

    UPDATE Employees
    SET Salary = Salary + (Salary * p_percent / 100)
    WHERE EmployeeID = p_employee_id;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Salary updated for EmployeeID ' || p_employee_id);
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: EmployeeID ' || p_employee_id || ' does not exist.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Unexpected error: ' || SQLERRM);
        ROLLBACK;
END UpdateSalary;
/

BEGIN
    UpdateSalary(4, 10);
    UpdateSalary(999, 5); 
END;
/

--Scenario 3: Ensure data integrity when adding a new customer.

CREATE OR REPLACE PROCEDURE AddNewCustomer(
    p_customer_id IN NUMBER,
    p_name        IN VARCHAR2,
    p_dob         IN DATE,
    p_balance     IN NUMBER
) AS
BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Customer added successfully. ID: ' || p_customer_id);
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        DBMS_OUTPUT.PUT_LINE('Error: CustomerID ' || p_customer_id || ' already exists. Insertion prevented.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Unexpected error: ' || SQLERRM);
        ROLLBACK;
END AddNewCustomer;
/

BEGIN
    AddNewCustomer(1, 'John Doe', TO_DATE('1990-01-01', 'YYYY-MM-DD'), 5000);
    AddNewCustomer(1, 'Jane Smith', TO_DATE('1985-05-05', 'YYYY-MM-DD'), 7000); 
END;
/