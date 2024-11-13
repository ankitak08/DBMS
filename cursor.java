
mysql> create table old_roll(roll int,name varchar(10));
ew_roll(roll int,name varchar(10));
insert into old_roll values(4,'d');
insert into old_roll values(3,'bcd');
insert into old_roll values(1,'bc');
insert into old_roll values(5,'bch');
insert into new_roll values(2,'b');
insert into new_roll values(5,'bch');
insert into new_roll values(1,'bc');Query OK, 0 rows affected (0.09 sec)

mysql> create table new_roll(roll int,name varchar(10));
Query OK, 0 rows affected (0.05 sec)

mysql> insert into old_roll values(4,'d');
Query OK, 1 row affected (0.01 sec)

mysql> insert into old_roll values(3,'bcd');
Query OK, 1 row affected (0.03 sec)

mysql> insert into old_roll values(1,'bc');
Query OK, 1 row affected (0.01 sec)

mysql> insert into old_roll values(5,'bch');
Query OK, 1 row affected (0.00 sec)

mysql> insert into new_roll values(2,'b');
Query OK, 1 row affected (0.01 sec)

mysql> insert into new_roll values(5,'bch');
Query OK, 1 row affected (0.00 sec)

mysql> insert into new_roll values(1,'bc');
Query OK, 1 row affected (0.01 sec)

mysql> DELIMITER $
ll_list()
BEGIN
mysql>
mysql> CREATE PROCEDURE roll_list()
    -> BEGIN
    ->     DECLARE oldrollnumber INT;
    ->     DECLARE oldname VARCHAR(10);
    ->     DECLARE newrollnumber INT;
    ->     DECLARE newname VARCHAR(10);
    ->     DECLARE done INT DEFAULT FALSE;
    ->
    ->     DECLARE c1 CURSOR FOR SELECT roll, name FROM old_roll;
    ->     DECLARE c2 CURSOR FOR SELECT roll, name FROM new_roll;
    ->     DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    ->     OPEN c1;
    ->  loop1: LOOP
    ->         FETCH c1 INTO oldrollnumber, oldname;
    ->         IF done THEN
    ->             LEAVE loop1;
    ->         END IF;
    ->  SET done = FALSE;
    -> OPEN c2;
    -> loop2: LOOP
    ->             FETCH c2 INTO newrollnumber, newname;
    ->             IF done THEN
    ->                 -- If not found in new_roll, insert into new_roll
    ->                 INSERT INTO new_roll (roll, name) VALUES (oldrollnumber, oldname);
    ->                 SET done = FALSE;  -- Reset for the next outer loop iteration
    ->                 LEAVE loop2;  -- Exit inner loop to process the next record from old_roll
    ->             END IF;
    -> IF oldrollnumber = newrollnumber AND oldname = newname THEN
    ->                 LEAVE loop2;  -- Skip insertion for this old_roll record
    ->             END IF;
    ->         END LOOP;
    ->  CLOSE c2;
    ->     END LOOP;
    ->  CLOSE c1;
    -> END $
Query OK, 0 rows affected (0.01 sec)

mysql> DELIMITER ;
mysql> CALL roll_list();
Query OK, 0 rows affected (0.02 sec)

mysql> SELECT * FROM new_roll;
+------+------+
| roll | name |
+------+------+
|    2 | b    |
|    5 | bch  |
|    1 | bc   |
|    4 | d    |
|    3 | bcd  |
+------+------+