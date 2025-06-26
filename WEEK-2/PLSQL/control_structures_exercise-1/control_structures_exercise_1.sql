-- Scenario 1: Apply 1% discount to loan interest rates for customers above 60 years old

CREATE OR REPLACE PROCEDURE ApplyInterestDiscountForSeniors AS
    CURSOR cur_customers IS
        SELECT CustomerID, DOB FROM Customers;
    v_customer_id Customers.CustomerID%TYPE;
    v_dob Customers.DOB%TYPE;
    v_age NUMBER;
BEGIN
    FOR rec IN cur_customers LOOP
        v_customer_id := rec.CustomerID;
        v_dob := rec.DOB;
        -- Calculate age
        v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, v_dob) / 12);
        IF v_age > 60 THEN
            -- Apply 1% discount to all loans of this customer
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = v_customer_id;
        END IF;
    END LOOP;
    COMMIT;
END ApplyInterestDiscountForSeniors;
/

EXEC ApplyInterestDiscountForSeniors;



-- Scenario 2: Promote customers to VIP status if balance > $10,000
ALTER TABLE Customers ADD (IsVIP VARCHAR2(5));

CREATE OR REPLACE PROCEDURE PromoteCustomersToVIP AS
BEGIN
    FOR rec IN (SELECT CustomerID, Balance FROM Customers) LOOP
        IF rec.Balance > 10000 THEN
            UPDATE Customers SET IsVIP = 'TRUE' WHERE CustomerID = rec.CustomerID;
        ELSE
            UPDATE Customers SET IsVIP = 'FALSE' WHERE CustomerID = rec.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
END PromoteCustomersToVIP;
/

EXEC PromoteCustomersToVIP;


-- Scenario 3: Send reminders to customers whose loans are due within the next 30 days
CREATE OR REPLACE PROCEDURE SendLoanDueReminders AS
    CURSOR cur_due_loans IS
        SELECT l.LoanID, l.CustomerID, l.EndDate, c.Name
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30;
BEGIN
    FOR rec IN cur_due_loans LOOP
        DBMS_OUTPUT.PUT_LINE(
            'Reminder: Dear ' || rec.Name ||
            ', your loan (LoanID: ' || rec.LoanID ||
            ') is due on ' || TO_CHAR(rec.EndDate, 'YYYY-MM-DD') ||
            '. Please ensure timely payment.'
        );
    END LOOP;
END SendLoanDueReminders;
/

EXEC SendLoanDueReminders;