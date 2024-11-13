-- Table: Deposit
CREATE TABLE Deposit (
    actno INT PRIMARY KEY,
    cname VARCHAR(50),
    bname VARCHAR(50),
    amount DECIMAL(10, 2),
    adate DATE,
    FOREIGN KEY (cname) REFERENCES Customers(cname),
    FOREIGN KEY (bname) REFERENCES Branch(bname)
);

-- Table: Branch
CREATE TABLE Branch (
    bname VARCHAR(50) PRIMARY KEY,
    city VARCHAR(50)
);

-- Table: Customers
CREATE TABLE Customers (
    cname VARCHAR(50) PRIMARY KEY,
    city VARCHAR(50)
);

-- Table: Borrow
CREATE TABLE Borrow (
    loanno INT PRIMARY KEY,
    cname VARCHAR(50),
    bname VARCHAR(50),
    amount DECIMAL(10, 2),
    FOREIGN KEY (cname) REFERENCES Customers(cname),
    FOREIGN KEY (bname) REFERENCES Branch(bname)
);

-- Insert data into Branch
INSERT INTO Branch (bname, city) VALUES 
('MainBranch', 'Bombay'),
('NorthBranch', 'Delhi'),
('SouthBranch', 'Bombay'),
('WestBranch', 'Pune');

-- Insert data into Customers
INSERT INTO Customers (cname, city) VALUES 
('Anil', 'Pune'),
('Ravi', 'Bombay'),
('Sita', 'Delhi'),
('Priya', 'Bombay');

-- Insert data into Deposit
INSERT INTO Deposit (actno, cname, bname, amount, adate) VALUES 
(1001, 'Anil', 'MainBranch', 15000, '2023-05-20'),
(1002, 'Ravi', 'SouthBranch', 25000, '2023-06-15'),
(1003, 'Sita', 'NorthBranch', 30000, '2023-07-22'),
(1004, 'Priya', 'SouthBranch', 5000, '2023-08-10');

-- Insert data into Borrow
INSERT INTO Borrow (loanno, cname, bname, amount) VALUES 
(5001, 'Anil', 'MainBranch', 10000),
(5002, 'Ravi', 'SouthBranch', 20000),
(5003, 'Priya', 'SouthBranch', 5000);

a. Display names of all branches located in the city Bombay.


SELECT bname FROM Branch WHERE city = 'Bombay';
b. Display account no. and amount of depositors.


SELECT actno, amount FROM Deposit;
c. Update the city of customer Anil from Pune to Mumbai.


UPDATE Customers SET city = 'Mumbai' WHERE cname = 'Anil';
d. Find the number of depositors in the bank.


SELECT COUNT(*) AS num_of_depositors FROM Deposit;
e. Calculate the minimum and maximum amount of customers.


SELECT MIN(amount) AS min_amount, MAX(amount) AS max_amount FROM Deposit;
f. Create an index on the Deposit table.

CREATE INDEX idx_deposit_actno ON Deposit (actno);
g. Create a view on the Borrow table.

CREATE VIEW BorrowView AS
SELECT loanno, cname, bname, amount FROM Borrow;