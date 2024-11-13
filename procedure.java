mysql> CREATE TABLE Student(roll_no int,Name varchar(30),marks int);
Query OK, 0 rows affected (0.07 sec)
mysql> Insert into Student(roll_no,Name,marks) values(1,"Ankita",1150),(2,"Pratiksha",2000),(3,"Sanika",200),(4,"Chaitali",500),(5,"Rutuja",950);   

mysql> create procedure Grade(in roll int,out category varchar(50))
begin declare m integer ;
select marks into m from Student where roll_no = roll;
if m >= 990 and m<= 1500 then 
set category = 'Distinction';
elseif m >=900 and m<= 989 then
set category = 'First Class'; 
elseif m >= 825 and m <= 899 then 
set category = 'Higher second class' ; 
else  set category = 'No category'; 
end if; 
end//

mysql> call Grade(1,@Category);

mysql> select @Category;