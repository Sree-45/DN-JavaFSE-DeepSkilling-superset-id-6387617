--Scenario 1: Calculate the age of customers for eligibility checks.
CREATE OR REPLACE FUNCTION CalculateAge(
    p_dob IN DATE
) RETURN NUMBER IS
    v_age NUMBER;
BEGIN
    v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
    RETURN v_age;
END CalculateAge;
/

SELECT Name, CalculateAge(DOB) AS Age
FROM Customers;


--Scenario 2: The bank needs to compute the monthly installment for a loan.
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_loan_amount      IN NUMBER,
    p_interest_rate    IN NUMBER,
    p_duration_years   IN NUMBER
) RETURN NUMBER IS
    v_monthly_rate   NUMBER;
    v_num_payments   NUMBER;
    v_emi            NUMBER;
BEGIN
    v_monthly_rate := p_interest_rate / (12 * 100); 
    v_num_payments := p_duration_years * 12;

    IF v_monthly_rate = 0 THEN
        v_emi := p_loan_amount / v_num_payments;
    ELSE
        v_emi := p_loan_amount * v_monthly_rate * POWER(1 + v_monthly_rate, v_num_payments) /
                 (POWER(1 + v_monthly_rate, v_num_payments) - 1);
    END IF;

    RETURN ROUND(v_emi, 2);
END CalculateMonthlyInstallment;
/

SELECT 
    LoanID,
    CalculateMonthlyInstallment(
        LoanAmount,
        InterestRate,
        (MONTHS_BETWEEN(EndDate, StartDate) / 12)
    ) AS Monthly_Installment
FROM Loans;

--Scenario 3: Check if a customer has sufficient balance before making a transaction.
CREATE OR REPLACE FUNCTION HasSufficientBalance(
    p_account_id IN NUMBER,
    p_amount     IN NUMBER
) RETURN BOOLEAN IS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance
    FROM Accounts
    WHERE AccountID = p_account_id;

    RETURN v_balance >= p_amount;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
END HasSufficientBalance;
/

DECLARE
    result BOOLEAN;
BEGIN
    result := HasSufficientBalance(4, 5000);
    IF result THEN
        DBMS_OUTPUT.PUT_LINE('Sufficient balance');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Insufficient balance');
    END IF;
END;
/