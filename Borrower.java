mysql> create table Borrower(roll int , name varchar(25) , doi date , bookname varchar(30),status varchar(20));
Query OK, 0 rows affected (0.08 sec)

mysql> INSERT INTO Borrower VALUES(12,'HARSHADA','2023-10-15','DS','Issued')
;
Query OK, 1 row affected (0.01 sec)

mysql> create table Fine(roll int , dor date , amt int);
Query OK, 0 rows affected (0.06 sec)


mysql> delimiter //
mysql> create procedure E(in roll_new int , in book_name varchar(20))
begin  DECLARE X INT DEFAULT 0;
DECLARE book_found BOOLEAN DEFAULT FALSE;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET book_found = FALSE;
SELECT DATEDIFF(CURDATE(), doi) INTO X
FROM Borrower WHERE roll = roll_new AND bookname = book_name;  
SET book_found = TRUE;  
IF book_found THEN         
IF X >=15 AND X <= 30 THEN    
INSERT INTO Fine (roll, dor, amt) VALUES (roll_new, CURDATE(), X * 5); 
ELSEIF X > 30 THEN             
INSERT INTO Fine (roll, dor, amt) VALUES (roll_new, CURDATE(),X * 50); 
end if;  
UPDATE Borrower          
SET Status = 'R'         
WHERE roll = roll_new AND bookname = book_name; 
else  SELECT 'Record Not Found for the given Roll Number and Book Name'
AS Error_Message;     
END IF; 
end//