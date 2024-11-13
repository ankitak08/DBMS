16. Write a Stored Procedure namely proc_Grade for the categorization of student. If marks scored by students in
examination is <=1500 and marks>=990 then student will be placed in distinction category if marks scored are
between 989 and 900 category is first class, if marks 899 and 825 category is Higher Second Class

mysql> create database Student;
Query OK, 1 row affected (0.15 sec)

mysql> use Student;
Database changed

mysql> create table Result(Roll_No int, Name varchar(10),Class varchar(5));
Query OK, 0 rows affected (0.38 sec)

mysql> create table Stud_Marks(Roll_No int,Name varchar(10),Total_marks int);
Query OK, 0 rows affected (0.48 sec)

mysql> insert into Stud_Marks values(3102023,'Ritesh',1499);
Query OK, 1 row affected (0.07 sec)

mysql> insert into Stud_Marks values(3102025,'Pratiksha',959);
Query OK, 1 row affected (0.07 sec)

mysql> insert into Stud_Marks values(3102053,'Piyush',850);
Query OK, 1 row affected (0.07 sec)

mysql> insert into Stud_Marks values(3202010,'Aniket',1500);
Query OK, 1 row affected (0.07 sec)

mysql> insert into Stud_Marks values(3102099,'Sanket',800);
Query OK, 1 row affected (0.07 sec)

mysql> select * from Stud_Marks;

+----------+------------+----------------+
| Roll_No | Name       |  Total_marks |
+----------+------------+----------------+
| 3102023 | Ritesh      |               1499|
| 3102025 | Pratiksha |                 959|
| 3102053 | Piyush     |                 850|
| 3202010 | Aniket     |               1500|
| 3102099 | Sanket     |                 800|
+----------+------------+----------------+
5 rows in set (0.02 sec)

mysql> delimiter //
mysql> create procedure proc_Grade(in r int,in n varchar(50))
    -> BEGIN
    -> declare tm int;
    -> select total_marks into tm from Stud_Marks where Roll_No =r and Name = n;
    -> if tm<=1500 and tm>=990 then
    -> insert into Result values(r,n,'Distinction');
    -> end if;
    -> if tm<=989 and tm>=900 then
    -> insert into Result values(r,n,'First Class');
    -> end if;
    -> if tm<=899 and tm>=825 then
    -> insert into Result values(r,n,'Second Class');
    -> end if;
    -> if tm<=824 then
    -> insert into Result values(r,n,'Fail');
    -> end if;
    -> end//
Query OK, 0 rows affected (0.12 sec)

mysql> call proc_Grade(3102023,'Ritesh');
Query OK, 1 row affected (0.20 sec)

mysql> call proc_Grade(3102025,'Pratiksha');
Query OK, 1 row affected (0.07 sec)

mysql> call proc_Grade(3102053,'Piyush');
Query OK, 1 row affected (0.07 sec)

mysql> call proc_Grade(3202010,'Aniket');
Query OK, 1 row affected (0.09 sec)

mysql> call proc_Grade(3102099,'Sanket');
Query OK, 1 row affected (0.09 sec)

mysql> select * from Result;

+----------+------------+-----------------+
| Roll_No |  Name      |   Class            |
+----------+-------------+----------------+
| 3102023 | Ritesh      | Distinction     |
| 3102025 | Pratiksha | First Class      |
| 3102053 | Piyush     | Second Class |
| 3202010 | Aniket     | Distinction     |
| 3102099 | Sanket     | Fail                |
+----------+------------+---------------- +
5 rows in set (0.01 sec)
