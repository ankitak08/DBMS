//9.
1. Update the lname of the staff with staffno 106 to 'Shinde'.

UPDATE Staff
SET lname = 'Shinde'
WHERE staffno = 106;

2. Delete the branch with postcode 411041.

DELETE FROM Branch
WHERE postcode = '411041';

3. List the employees who have at least 2 'A's in their name.

SELECT fname, lname
FROM Staff
WHERE fname LIKE '%A%A%' OR lname LIKE '%A%A%';

4. List the maximum salary from each branch of the staff members from highest to lowest.

SELECT branchno, MAX(salary) AS max_salary
FROM Staff
GROUP BY branchno
ORDER BY max_salary DESC;

5. Find the clients who are also owners.

SELECT c.fname, c.lname
FROM Client c
JOIN PrivateOwner po ON c.fname = po.fname AND c.lname = po.lname;

6. Find the owners who are not clients.

SELECT po.fname, po.lname
FROM PrivateOwner po
LEFT JOIN Client c ON po.fname = c.fname AND po.lname = c.lname
WHERE c.fname IS NULL;

7. Find the owners who have 'C' as the third letter in their name.

SELECT fname, lname
FROM PrivateOwner
WHERE fname LIKE '__C%';

7. List All Properties Managed by Staff Member with ID S123
SELECT * 
FROM PropertyForRent
WHERE staffNo = 'S123';

8. Find the Average Rent of Properties in Pune
SELECT AVG(rent) AS average_rent
FROM PropertyForRent
WHERE city = 'Pune';

9. Find the Number of Properties Managed by Each Branch
SELECT branchNo, COUNT(*) AS property_count
FROM PropertyForRent
GROUP BY branchNo;
This script covers the setup of tables, insertion of data, and all the specified queries. Let me know if you need further modifications!















//10.
 Update the Street of BranchNo 1001 to 'MG'

UPDATE Branch
SET street = 'MG'
WHERE branchno = 1001;

Delete the Owner Who Has fname as 'AMIT'

DELETE FROM PrivateOwner
WHERE fname = 'Amit';

List Clients Who Pay Rent More Than 10000

SELECT fname, lname, rent
FROM Client
WHERE rent > 10000;

List Names of Staff with Salary Greater Than the Average Salary of All Staff

SELECT fname, lname
FROM Staff
WHERE salary > (SELECT AVG(salary) FROM Staff);

Find the Type and Rooms of Properties in Pune

SELECT type, rooms
FROM PropertyForRent
WHERE city = 'Pune';

Find the Names of Staff Who Work as Salesman or Saleswoman

SELECT fname, lname
FROM Staff
WHERE position IN ('Salesman', 'Saleswoman');

Find the Number of Properties Which Are of Type 'Flat'

SELECT COUNT(*) AS FlatCount
FROM PropertyForRent
WHERE type = 'Flat';


8. List Staff Members Born Before 2004
SELECT fname, lname, DOB
FROM Staff
WHERE DOB < '2004-01-01';

9. Find the Total Number of Staff Members in Each Branch
SELECT branchNo, COUNT(*) AS staff_count
FROM Staff
GROUP BY branchNo;