
//List the maximum salary paid to a salesman:

SELECT MAX(salary) AS max_salary
FROM emp
WHERE job = 'Salesman';

//List names of employees whose name starts with 'I':

SELECT ename
FROM emp
WHERE ename LIKE 'I%';

//List details of employees who joined before '30-Sept-81':

SELECT *
FROM emp
WHERE hiredate < '1981-09-30';

//List the employee details in descending order of their salary:

SELECT *
FROM emp
ORDER BY salary DESC;

//List the number of employees and average salary for employees in dept no 20:

SELECT COUNT(*) AS total_employees, AVG(salary) AS avg_salary
FROM emp
WHERE deptno = 20;

//List the average salary and minimum salary of employees hired date-wise for dept no 10:

SELECT hiredate, AVG(salary) AS avg_salary, MIN(salary) AS min_salary
FROM emp
WHERE deptno = 10
GROUP BY hiredate;

//List employee name and its department:

SELECT e.ename, d.deptname
FROM emp e
JOIN dept d ON e.deptno = d.deptno;

//List total salary paid to each department:

SELECT d.deptname, SUM(e.salary) AS total_salary
FROM emp e
JOIN dept d ON e.deptno = d.deptno
GROUP BY d.deptname;

//List details of employees working in the 'Dev' department:

SELECT e.*
FROM emp e
JOIN dept d ON e.deptno = d.deptno
WHERE d.deptname = 'Dev';

//Update salary of all employees in deptno 10 by 5%:

UPDATE emp
SET salary = salary * 0.05
WHERE deptno = 10;