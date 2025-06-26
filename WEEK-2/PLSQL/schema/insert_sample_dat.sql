-- Customers
INSERT ALL
  INTO Customers (CustomerID, Name, DOB, Balance, LastModified) VALUES (3, 'Emily Clark', TO_DATE('1950-11-30', 'YYYY-MM-DD'), 12000, SYSDATE)
  INTO Customers (CustomerID, Name, DOB, Balance, LastModified) VALUES (4, 'Michael Lee', TO_DATE('1962-03-10', 'YYYY-MM-DD'), 8000, SYSDATE)
  INTO Customers (CustomerID, Name, DOB, Balance, LastModified) VALUES (5, 'Priya Patel', TO_DATE('2000-09-25', 'YYYY-MM-DD'), 500, SYSDATE)
  INTO Customers (CustomerID, Name, DOB, Balance, LastModified) VALUES (6, 'Carlos Gomez', TO_DATE('1958-01-05', 'YYYY-MM-DD'), 20000, SYSDATE)
SELECT * FROM dual;

-- Accounts
INSERT ALL
  INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) VALUES (3, 3, 'Savings', 12000, SYSDATE)
  INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) VALUES (4, 4, 'Checking', 8000, SYSDATE)
  INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) VALUES (5, 5, 'Savings', 500, SYSDATE)
  INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) VALUES (6, 6, 'Savings', 20000, SYSDATE)
SELECT * FROM dual;

-- Transactions
INSERT ALL
  INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType) VALUES (3, 3, SYSDATE, 1000, 'Deposit')
  INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType) VALUES (4, 4, SYSDATE, 500, 'Withdrawal')
  INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType) VALUES (5, 5, SYSDATE, 200, 'Deposit')
  INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType) VALUES (6, 6, SYSDATE, 1500, 'Deposit')
SELECT * FROM dual;

-- Loans
INSERT ALL
  INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) VALUES (2, 3, 10000, 4.5, SYSDATE, ADD_MONTHS(SYSDATE, 36))
  INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) VALUES (3, 4, 7000, 6, SYSDATE, ADD_MONTHS(SYSDATE, 48))
  INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) VALUES (4, 5, 2000, 7, SYSDATE, ADD_MONTHS(SYSDATE, 24))
  INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) VALUES (5, 6, 15000, 5.5, SYSDATE, SYSDATE + 15)
SELECT * FROM dual;

-- Employees
INSERT ALL
  INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate) VALUES (3, 'Sara Kim', 'Analyst', 55000, 'Finance', TO_DATE('2018-01-10', 'YYYY-MM-DD'))
  INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate) VALUES (4, 'David Miller', 'Clerk', 40000, 'Operations', TO_DATE('2019-09-01', 'YYYY-MM-DD'))
  INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate) VALUES (5, 'Linda Wang', 'Consultant', 65000, 'Consulting', TO_DATE('2020-04-18', 'YYYY-MM-DD'))
  INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate) VALUES (6, 'Ahmed Hassan', 'Support', 42000, 'IT', TO_DATE('2021-12-05', 'YYYY-MM-DD'))
SELECT * FROM dual;


commit;