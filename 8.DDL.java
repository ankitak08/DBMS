// Create Dept table with primary key constraint

CREATE TABLE Dept (
    DeptID INT PRIMARY KEY,
    DeptName VARCHAR(50) NOT NULL
);

//Create Emp table with primary key, foreign key, and unique constraints
CREATE TABLE Emp (
    EmpID INT PRIMARY KEY,
    EmpName VARCHAR(100) NOT NULL,
    DeptID INT,
    Salary DECIMAL(10, 2) NOT NULL CHECK (Salary > 0),
    CONSTRAINT FK_Dept FOREIGN KEY (DeptID) REFERENCES Dept(DeptID)
);


//Simple view to show employees and their departments
CREATE VIEW EmpDeptView AS
SELECT Emp.EmpID, Emp.EmpName, Dept.DeptName
FROM Emp
JOIN Dept ON Emp.DeptID = Dept.DeptID;

//Complex view to show employees with salary above a certain threshold
CREATE VIEW HighSalaryEmp AS
SELECT EmpID, EmpName, Salary
FROM Emp
WHERE Salary > 5000
WITH CHECK OPTION;

INSERT INTO HighSalaryEmp (EmpID, EmpName, Salary)
VALUES (1, 'John Doe', 6000);

UPDATE EmpDeptView
SET EmpName = 'Jane Doe'
WHERE EmpID = 1;

DELETE FROM HighSalaryEmp
WHERE EmpID = 1;

CREATE INDEX EmployeeId ON Emp (DeptID ASC, EmpID ASC);

CREATE SEQUENCE EmpID_Seq
START WITH 1
INCREMENT BY 1
NOCACHE;

DROP INDEX EmployeeId;







