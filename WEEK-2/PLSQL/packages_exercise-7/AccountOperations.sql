-- Package Specification
CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount(
        p_account_id NUMBER,
        p_customer_id NUMBER,
        p_account_type VARCHAR2,
        p_balance NUMBER
    );
    PROCEDURE CloseAccount(
        p_account_id NUMBER
    );
    FUNCTION GetTotalCustomerBalance(
        p_customer_id NUMBER
    ) RETURN NUMBER;
END AccountOperations;
/

-- Package Body
CREATE OR REPLACE PACKAGE BODY AccountOperations AS

    PROCEDURE OpenAccount(
        p_account_id NUMBER,
        p_customer_id NUMBER,
        p_account_type VARCHAR2,
        p_balance NUMBER
    ) IS
    BEGIN
        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_account_id, p_customer_id, p_account_type, p_balance, SYSDATE);
    END OpenAccount;

    PROCEDURE CloseAccount(
        p_account_id NUMBER
    ) IS
    BEGIN
        DELETE FROM Accounts
        WHERE AccountID = p_account_id;
    END CloseAccount;

    FUNCTION GetTotalCustomerBalance(
        p_customer_id NUMBER
    ) RETURN NUMBER IS
        v_total NUMBER := 0;
    BEGIN
        SELECT NVL(SUM(Balance), 0) INTO v_total
        FROM Accounts
        WHERE CustomerID = p_customer_id;
        RETURN v_total;
    END GetTotalCustomerBalance;

END AccountOperations;
/

-- Open a new account
BEGIN
    AccountOperations.OpenAccount(1001, 1, 'Savings', 5000);
END;
/

-- Close an account
BEGIN
    AccountOperations.CloseAccount(1001);
END;
/

-- Get total balance for a customer
SET SERVEROUTPUT ON;
DECLARE
    v_total NUMBER;
BEGIN
    v_total := AccountOperations.GetTotalCustomerBalance(3);
    DBMS_OUTPUT.PUT_LINE('Total Balance: ' || v_total);
END;
/