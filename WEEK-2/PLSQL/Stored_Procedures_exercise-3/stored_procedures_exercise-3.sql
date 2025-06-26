--Scenario 1: Process monthly interest for all savings accounts

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
    UPDATE Accounts
    SET Balance = Balance + (Balance * 0.01),
        LastModified = SYSDATE
    WHERE AccountType = 'Savings';

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Monthly interest processed for all savings accounts.');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error processing monthly interest: ' || SQLERRM);
        ROLLBACK;
END ProcessMonthlyInterest;
/

BEGIN
    ProcessMonthlyInterest;
END;
/

--Scenario 2: Update employee bonus by department

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department IN VARCHAR2,
    p_bonus_pct  IN NUMBER
) AS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * p_bonus_pct / 100)
    WHERE Department = p_department;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Bonus applied to employees in department: ' || p_department);
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error updating employee bonus: ' || SQLERRM);
        ROLLBACK;
END UpdateEmployeeBonus;
/
BEGIN
    UpdateEmployeeBonus('Finance', 5); 
    UpdateEmployeeBonus('IT', 7);
END;


--Scenario 3: Customers should be able to transfer funds between their accounts.
CREATE OR REPLACE PROCEDURE TransferFunds(
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
END TransferFunds;
/

SET SERVEROUTPUT ON;
BEGIN
    TransferFunds(3, 4, 100);
    TransferFunds(5, 6, 100000);
END;
/
