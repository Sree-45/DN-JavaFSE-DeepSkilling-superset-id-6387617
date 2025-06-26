---- Scenario 1: Generate monthly statements for all customers

SET SERVEROUTPUT ON;

DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT
            c.CustomerID,
            c.Name AS CustomerName,
            t.TransactionID,
            t.TransactionDate,
            t.Amount,
            t.TransactionType
        FROM Customers c
        JOIN Accounts a ON c.CustomerID = a.CustomerID
        JOIN Transactions t ON a.AccountID = t.AccountID
        WHERE EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
          AND EXTRACT(YEAR FROM t.TransactionDate) = EXTRACT(YEAR FROM SYSDATE)
        ORDER BY c.CustomerID, t.TransactionDate;

    v_last_customer_id Customers.CustomerID%TYPE := NULL;
BEGIN
    FOR rec IN GenerateMonthlyStatements LOOP
        IF v_last_customer_id IS NULL OR v_last_customer_id != rec.CustomerID THEN
            DBMS_OUTPUT.PUT_LINE('----------------------------------------');
            DBMS_OUTPUT.PUT_LINE('Monthly Statement for Customer: ' || rec.CustomerName || ' (ID: ' || rec.CustomerID || ')');
            DBMS_OUTPUT.PUT_LINE('Date        | Type       | Amount');
            DBMS_OUTPUT.PUT_LINE('----------------------------------------');
            v_last_customer_id := rec.CustomerID;
        END IF;
        DBMS_OUTPUT.PUT_LINE(
            TO_CHAR(rec.TransactionDate, 'YYYY-MM-DD') || ' | ' ||
            RPAD(rec.TransactionType, 10) || ' | ' ||
            rec.Amount
        );
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('----------------------------------------');
END;
/

--Scenario 2: Apply annual fee to all accounts.

DECLARE
    CURSOR ApplyAnnualFee IS
        SELECT AccountID, Balance
        FROM Accounts
        FOR UPDATE;

    v_fee CONSTANT NUMBER := 200; 
BEGIN
    FOR rec IN ApplyAnnualFee LOOP
        UPDATE Accounts
        SET Balance = Balance - v_fee,
            LastModified = SYSDATE
        WHERE CURRENT OF ApplyAnnualFee;

        DBMS_OUTPUT.PUT_LINE('Annual fee of ' || v_fee || ' deducted from AccountID: ' || rec.AccountID);
    END LOOP;
END;
/

--Scenario 3: Update the interest rate for all loans based on a new policy.

DECLARE
    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID, LoanAmount, InterestRate
        FROM Loans
        FOR UPDATE;

    v_new_rate NUMBER;
BEGIN
    FOR rec IN UpdateLoanInterestRates LOOP
        IF rec.LoanAmount < 100000 THEN
            v_new_rate := 9;
        ELSIF rec.LoanAmount BETWEEN 100000 AND 500000 THEN
            v_new_rate := 8;
        ELSE
            v_new_rate := 7.5;
        END IF;

        UPDATE Loans
        SET InterestRate = v_new_rate
        WHERE CURRENT OF UpdateLoanInterestRates;

        DBMS_OUTPUT.PUT_LINE('LoanID: ' || rec.LoanID ||
                             ' | Previous Rate: ' || rec.InterestRate ||
                             ' | New Rate: ' || v_new_rate);
    END LOOP;
END;
/