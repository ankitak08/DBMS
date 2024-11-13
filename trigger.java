
Create table Library :
mysql> create table Library(
    -> Sr_No int primary key,
    -> title  varchar(50),
    -> publisher varchar(50),
    -> Author varchar(70),
    -> section varchar(50)
    -> );
Query OK, 0 rows affected (0.08 sec)

Create table Library_Audit :
mysql> create table Library_Audit(Sr_No int,prev_title varchar(50),prev_publisher varchar(50),prev_Author varchar(70),prev_section varchar(50),Operation varchar(40),foreign key(Sr_No) references Library(Sr_No));
Query OK, 0 rows affected (0.12 sec)

Insert values into Library:
mysql> insert into Library(Sr_No,title,publisher,Author,section) values (1,"Yayati","Maharashtra rajya sahity","V.S.Khandekar","Historical");
Query OK, 1 row affected (0.02 sec)

mysql> insert into Library(Sr_No,title,publisher,Author,section) values (2,"Shyamchi Aai","purandare","Sane Guruji","Novel");
Query OK, 1 row affected (0.01 sec)

mysql> insert into Library(Sr_No,title,publisher,Author,section) values (3,"Chhava","Continental Prakashan","Shivaji Sawant","Historical");
Query OK, 1 row affected (0.01 sec)

mysql> insert into Library(Sr_No,title,publisher,Author,section) values (4,"The Guide","Indian Classic","R.k.Narayan","Indian Litreture");
Query OK, 1 row affected (0.02 sec)

mysql> insert into Library(Sr_No,title,publisher,Author,section) values (5,"Fakira","Purandare","AnnaBhau Sathe","Novel"
);
Query OK, 1 row affected (0.02 sec)

mysql> select * from Library;
+-------+--------------+--------------------------+----------------+------------------+
| Sr_No | title        | publisher                | Author         | section          |
+-------+--------------+--------------------------+----------------+------------------+
|     1 | Yayati       | Maharashtra rajya sahity | V.S.Khandekar  | Historical       |
|     2 | Shyamchi Aai | purandare                | Sane Guruji    | Novel            |
|     3 | Chhava       | Continental Prakashan    | Shivaji Sawant | Historical       |
|     4 | The Guide    | Indian Classic           | R.k.Narayan    | Indian Litreture |
|     5 | Fakira       | Purandare                | AnnaBhau Sathe | Novel            |
+-------+--------------+--------------------------+----------------+------------------+
5 rows in set (0.00 sec)

mysql> delimiter //

//create trigger for before update :

mysql> create TRIGGER before_update
    -> before update on Library
    -> for each row
    -> begin
    -> insert into Library_Audit(Sr_No,prev_title,prev_publisher,prev_Author,prev_section,Operation)
    -> values(OLD.Sr_No,OLD.title,OLD.publisher,OLD.Author,OLD.section,"BEFORE UPDATE");
    -> END //
Query OK, 0 rows affected (0.15 sec)

//create trigger for After update :

mysql> create TRIGGER after_update
    -> after update on Library
    -> for each row
    -> begin
    -> insert into Library_Audit(Sr_No,prev_title,prev_publisher,prev_Author,prev_section,Operation)
    -> values(New.Sr_No,New.title,New.publisher,New.Author,New.section,"AFTER UPDATE");
    -> END //
Query OK, 0 rows affected (0.02 sec)

//create trigger for before delete:
mysql> create TRIGGER before_delete
    -> before delete on Library
    -> for each row
    -> begin
    -> insert into Library_Audit(Sr_No,prev_title,prev_publisher,prev_Author,prev_section,Operation)
    -> values(OLD.Sr_No,OLD.title,OLD.publisher,OLD.Author,OLD.section,"Deleted!!!");
    -> END //
Query OK, 0 rows affected (0.02 sec)

mysql> delimiter ;

mysql> Update Library set section = "Literature" where Sr_No = 4;
Query OK, 1 row affected (0.03 sec)
Rows matched: 1  Changed: 1  Warnings: 0

mysql> select *from Library_Audit;
+-------+------------+----------------+-------------+------------------+---------------+
| Sr_No | prev_title | prev_publisher | prev_Author | prev_section     | Operation     |
+-------+------------+----------------+-------------+------------------+---------------+
|     4 | The Guide  | Indian Classic | R.k.Narayan | Indian Litreture | BEFORE UPDATE |
|     4 | The Guide  | Indian Classic | R.k.Narayan | Literature       | AFTER UPDATE  |
+-------+------------+----------------+-------------+------------------+---------------+
2 rows in set (0.00 sec)

mysql> Update Library set section = "Novel" where Sr_No = 3;
Query OK, 1 row affected (0.15 sec)
Rows matched: 1  Changed: 1  Warnings: 0

mysql> set foreign_key_checks = 0;
Query OK, 0 rows affected (0.01 sec)

mysql> delete from Library where Sr_No = 3;
Query OK, 1 row affected (0.06 sec)

mysql> select *from Library_Audit;
+-------+------------+-----------------------+----------------+------------------+---------------+
| Sr_No | prev_title | prev_publisher        | prev_Author    | prev_section     | Operation     |
+-------+------------+-----------------------+----------------+------------------+---------------+
|     4 | The Guide  | Indian Classic        | R.k.Narayan    | Indian Litreture | BEFORE UPDATE |
|     4 | The Guide  | Indian Classic        | R.k.Narayan    | Literature       | AFTER UPDATE  |
|     3 | Chhava     | Continental Prakashan | Shivaji Sawant | Novel            | Deleted!!!    |  
+-------+------------+-----------------------+----------------+------------------+---------------+
4 rows in set (0.00 sec)

mysql> select *from Library;
+-------+--------------+--------------------------+----------------+------------+
| Sr_No | title        | publisher                | Author         | section    |
+-------+--------------+--------------------------+----------------+------------+
|     1 | Yayati       | Maharashtra rajya sahity | V.S.Khandekar  | Historical |
|     2 | Shyamchi Aai | purandare                | Sane Guruji    | Novel      |
|     4 | The Guide    | Indian Classic           | R.k.Narayan    | Literature |
|     5 | Fakira       | Purandare                | AnnaBhau Sathe | Novel      |
+-------+--------------+--------------------------+----------------+------------+
4 rows in set (0.00 sec)

mysql>
