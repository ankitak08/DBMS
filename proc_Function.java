mysql> use stud; Database 
changed
mysql> CREATE TABLE stud_marks (roll_nointeger primarykey,name 
varchar(20),total_marks integer);
Query OK, 0 rows affected (0.62 sec)
mysql> CREATE TABLE result(roll_no integer , class varchar (20),constraint xyz foreign key(roll_no) 
references stud_marks(roll_no)); Query OK, 0 rows affected (0.70 sec)
mysql> insert into stud_marks
-> 
(roll_no,name,total_marks)values(1,’Apeksha',1130),(2,'Asawari',930),(3,'Sakshi',950),(4,’Shrutika',840)
,(5,'Tanaya',1000),(6,'Pornima',860);
Query OK, 6 rows affected (0.26 sec) Records: 6 
Duplicates: 0 Warnings: 0 mysql> select * from 
stud_MARKS;
ERROR 1146 (42S02): Table 'stud.stud_MARKS' doesn't exist mysql> select * 
from stud_marks;
+---------+---------+-------------+
| roll_no | name | total_marks |
+---------+---------+-------------+
| 1 | Apeksha |1130 |
| 2 | Asawari | 930 | | 
3 | Sakshi | 950 | 
| 4 | Shrutika | 840 |
| 5 | Tanaya |1000 | 
| 6 | Pornima | 860 |
+---------+---------+-------------+
6 rows in set (0.00 sec) mysql> 
delimiter //
mysql> create procedure credit (IN roll integer )
-> begin
-> declare m integer;
-> declare c varchar(20);
-> select total_marks into m from stud_marks where roll_no=roll;
-> if m>=990 and m<=1500 then
-> set c ='Distinction';
-> insert into result values(roll,c);
-> elseif m>= 900 and m<= 989 then
-> set c = 'First class';
-> insert into result values(roll,c);
-> elseif m>= 825 and m<= 899 then
-> set c = 'Higher Second Class'; -> 
insert into result values(roll,c);
-> end if;
-> end //
Query OK, 0 rows affected (0.27 sec)
mysql> call credit(1)//
Query OK, 1 row affected (0.22 sec)
mysql> call credit(2)//
Query OK, 1 row affected (0.08 sec)
mysql> call credit(3)//
Query OK, 1 row affected (0.07 sec)
mysql> call credit(4)//
Query OK, 1 row affected (0.11 sec)
mysql> call credit(5)//
Query OK, 1 row affected (0.07 sec)
mysql> call credit(6)//
Query OK, 1 row affected (0.11 sec)
mysql> select * from result //
+---------+---------------------+
| roll_no | class |
+---------+---------------------+
| 1 | Distinction |
| 2 | First class |
| 3 | First class |
| 4 | Higher Second Class |
| 5 | Distinction |
| 6 | Higher Second Class |
+---------+---------------------+
6 rows in set (0.00 sec)
mysql> CREATE FUNCTION disp_grade2(roll1 INT)
-> RETURNS VARCHAR(20)
-> DETERMINISTIC
-> READS SQL DATA
-> BEGIN
-> DECLARE m1 INT;
-> DECLARE c1 VARCHAR(20)
-> SELECT total_marks INTO m1 FROM stud_marks WHERE roll_no = roll1;
-> IF m1 >= 990 AND m1 <= 1500 THEN
-> SET c1 = 'Distinction';
-> ELSEIF m1 >= 900 AND m1 <= 989 THEN
-> SET c1 = 'First Class';
-> ELSEIF m1 >= 825 AND m1 <= 899 THEN
-> SET c1 = 'Higher Second Class';
-> ELSE
-> SET c1 = 'No Grade'; -- Handling cases where marks are below 825 or invalid roll number
-> END IF;
->
-> RETURN c1;
-> END //
Query OK, 0 rows affected (0.12 sec)
mysql> SELECT disp_grade2(1) AS grade;
+-------------+
| grade |
+-------------+
| Distinction |
+-------------+
1 row in set (0.00 sec)
mysql> SELECT disp_grade2(2) AS grade;
+-------------+
| grade |
+-------------+
| First Class |
+-------------+
1 row in set (0.00 sec)
mysql> SELECT disp_grade2(3) AS grade;
+-------------+
| grade |
+-------------+
| First Class |
+-------------+
1 row in set (0.00 sec)
mysql> SELECT disp_grade2(4) AS grade;
+---------------------+
| grade |
+---------------------+
| Higher Second Class |
+---------------------+
1 row in set (0.00 sec)
mysql> SELECT disp_grade2(5) AS grade;
+-------------+
| grade |
+-------------+
| Distinction |
+-------------+
1 row in set (0.00 sec)
mysql> SELECT disp_grade2(6) AS grade;
+---------------------+
| grade |
+---------------------+
| Higher Second Class |
+---------------------+
1 row in set (0.00 sec