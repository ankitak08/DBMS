 CREATE TABLE Employee (     EmpID INT ,     Ename VARCHAR(50),     Salary DECIMAL(10, 2),     Contactno VARCHAR(1
5),     City VARCHAR(50),     DeptID INT primary key );
Query OK, 0 rows affected (0.06 sec)

INSERT INTO Employee (EmpID, Ename, Salary, Contactno, City, DeptID) VALUES (1, 'Alice', 55000.00, '1234567890',
'New York', 101), (2, 'Bob', 62000.00, '0987654321', 'Los Angeles', 102), (3, 'Charlie', 47000.00, '1231231234', 'Chicago', 105), (4, 'Diana', 58000.00, '3213213210', 'Houston', 103);

mysql> CREATE TABLE Department (
    ->     DeptID INT PRIMARY KEY,
    ->     Dname VARCHAR(50),
    ->     Location VARCHAR(50)
    -> );
Query OK, 0 rows affected (0.07 sec)

mysql> INSERT INTO Department (DeptID, Dname, Location) VALUES
    -> (101, 'Human Resources', 'New York'),
    -> (102, 'Engineering', 'Los Angeles'),
    -> (103, 'Marketing', 'Houston'),
    -> (104, 'Sales', 'Chicago');
Query OK, 4 rows affected (0.01 sec)
Records: 4  Duplicates: 0  Warnings: 0

//1.
mysql> select Ename,Dname
-> from Employee join Department
-> on Employee.DeptID = Department.DeptID;
+-------+-----------------+
| Ename | Dname           |
+-------+-----------------+
| Alice | Human Resources |
| Bob   | Engineering     |
| Diana | Marketing       |
+-------+-----------------+
3 rows in set (0.01 sec)

//2.
mysql> select Ename,Dname,Location from Employee Inner join Department on Em
ployee.DeptID = Department.DeptID;
+-------+-----------------+-------------+
| Ename | Dname           | Location    |
+-------+-----------------+-------------+
| Alice | Human Resources | New York    |
| Bob   | Engineering     | Los Angeles |
| Diana | Marketing       | Houston     |
+-------+-----------------+-------------+
3 rows in set (0.00 sec)

//3.
mysql> SELECT Ename, Location, Dname
    -> FROM Employee
    -> JOIN Department ON Employee.DeptID = Department.DeptID;
+-------+-------------+-----------------+
| Ename | Location    | Dname           |
+-------+-------------+-----------------+
| Alice | New York    | Human Resources |
| Bob   | Los Angeles | Engineering     |
| Diana | Houston     | Marketing       |
+-------+-------------+-----------------+
3 rows in set (0.00 sec)

//4.mysql> select Dname,salary,location,Ename from Employee right join Departmen
t on Employee.DeptID = Department.DeptID;
+-----------------+----------+-------------+-------+
| Dname           | salary   | location    | Ename |
+-----------------+----------+-------------+-------+
| Human Resources | 55000.00 | New York    | Alice |
| Engineering     | 62000.00 | Los Angeles | Bob   |
| Marketing       | 58000.00 | Houston     | Diana |
| Sales           |     NULL | Chicago     | NULL  |
+-----------------+----------+-------------+-------+
4 rows in set (0.00 sec)

//5.
mysql> SELECT Ename, Dname, Location
 JOIN Department ON Employee.DeptID = Department.DeptID
UNION
SELECT Ename, Dname, Location
FROM Employee
RIGHT JOIN Department ON Employee.DeptID = Department.DeptID;
    -> FROM Employee
    -> LEFT JOIN Department ON Employee.DeptID = Department.DeptID
    -> UNION
    -> SELECT Ename, Dname, Location
    -> FROM Employee
    -> RIGHT JOIN Department ON Employee.DeptID = Department.DeptID;
+---------+-----------------+-------------+
| Ename   | Dname           | Location    |
+---------+-----------------+-------------+
| Alice   | Human Resources | New York    |
| Bob     | Engineering     | Los Angeles |
| Diana   | Marketing       | Houston     |
| Charlie | NULL            | NULL        |
| NULL    | Sales           | Chicago     |
+---------+-----------------+-------------+
5 rows in set (0.01 sec)

//6.
mysql> select Ename,Dname
    -> from Employee
    -> cross join Department;
+---------+-----------------+
| Ename   | Dname           |
+---------+-----------------+
| Charlie | Human Resources |
| Diana   | Human Resources |
| Bob     | Human Resources |
| Alice   | Human Resources |
| Charlie | Engineering     |
| Diana   | Engineering     |
| Bob     | Engineering     |
| Alice   | Engineering     |
| Charlie | Marketing       |
| Diana   | Marketing       |
| Bob     | Marketing       |
| Alice   | Marketing       |
| Charlie | Sales           |
| Diana   | Sales           |
| Bob     | Sales           |
| Alice   | Sales           |
+---------+-----------------+
16 rows in set (0.01 sec)

//7.

mysql> SELECT E1.Ename AS Employee1, E2.Ename AS Employee2, Dname
    -> FROM Employee E1
    -> JOIN Employee E2 ON E1.DeptID = E2.DeptID AND E1.EmpID <> E2.EmpID
    -> JOIN Department D ON E1.DeptID = D.DeptID;
Empty set (0.01 sec)

//8.
mysql>
mysql> create view  Employeeview as
    -> select Ename,EmpID,Salary
    -> from Employee;
Query OK, 0 rows affected (0.06 sec)

//9.
mysql> create view DeptView as
    -> select E.EmpID,E.Ename,E.Salary,D.Dname,D.Location
    -> from Employee E
    -> join Department D on E.DeptId = D.DeptId;
Query OK, 0 rows affected (0.02 sec)

//10.
mysql> Update Employeeview
    -> set salary = salary + 1000
    -> where EmpID = 1;
Query OK, 1 row affected (0.03 sec)
Rows matched: 1  Changed: 1  Warnings: 0

mysql> Select * from Employeeview;
+---------+-------+----------+
| Ename   | EmpID | Salary   |
+---------+-------+----------+
| Alice   |     1 | 56000.00 |
| Bob     |     2 | 62000.00 |
| Diana   |     4 | 58000.00 |
| Charlie |     3 | 47000.00 |
+---------+-------+----------+
4 rows in set (0.00 sec)

mysql> Select * from Deptview;

mysql> Select * from DeptView;
+-------+-------+----------+-----------------+-------------+
| EmpID | Ename | Salary   | Dname           | Location    |
+-------+-------+----------+-----------------+-------------+
|     1 | Alice | 56000.00 | Human Resources | New York    |
|     2 | Bob   | 62000.00 | Engineering     | Los Angeles |
|     4 | Diana | 58000.00 | Marketing       | Houston     |
+-------+-------+----------+-----------------+-------------+
3 rows in set (0.01 sec)

mysql> Select * from Department;
+--------+-----------------+-------------+
| DeptID | Dname           | Location    |
+--------+-----------------+-------------+
|    101 | Human Resources | New York    |
|    102 | Engineering     | Los Angeles |
|    103 | Marketing       | Houston     |
|    104 | Sales           | Chicago     |
+--------+-----------------+-------------+
4 rows in set (0.00 sec)

mysql> Select * from Employee;
+-------+---------+----------+------------+-------------+--------+
| EmpID | Ename   | Salary   | Contactno  | City        | DeptID |
+-------+---------+----------+------------+-------------+--------+
|     1 | Alice   | 56000.00 | 1234567890 | New York    |    101 |
|     2 | Bob     | 62000.00 | 0987654321 | Los Angeles |    102 |
|     4 | Diana   | 58000.00 | 3213213210 | Houston     |    103 |
|     3 | Charlie | 47000.00 | 1231231234 | Chicago     |    105 |
+-------+---------+----------+------------+-------------+--------+
4 rows in set (0.00 sec)

//11.
mysql> Drop view Employeeview;
Query OK, 0 rows affected (0.03 sec)

mysql> Drop view DeptView;
Query OK, 0 rows affected (0.03 sec)

