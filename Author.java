CREATE DATABASE book_management;

USE book_management;

-- Create PUBLISHER table
CREATE TABLE PUBLISHER (
    pid INT PRIMARY KEY,
    pname VARCHAR(100),
    address VARCHAR(255),
    state VARCHAR(50),
    phone VARCHAR(20),
    emailid VARCHAR(100)
);

-- Create BOOK table
CREATE TABLE BOOK (
    isbn VARCHAR(13) PRIMARY KEY,
    book_title VARCHAR(255),
    category VARCHAR(100),
    price DECIMAL(10, 2),
    copyright_date DATE,
    year INT,
    page_count INT,
    pid INT,
    FOREIGN KEY (pid) REFERENCES PUBLISHER(pid)
);

-- Create AUTHOR table
CREATE TABLE AUTHOR (
    aid INT PRIMARY KEY,
    aname VARCHAR(100),
    state VARCHAR(50),
    city VARCHAR(100),
    zip VARCHAR(10),
    phone VARCHAR(20),
    url VARCHAR(255)
);

-- Create AUTHOR_BOOK table (to represent the many-to-many relationship between authors and books)
CREATE TABLE AUTHOR_BOOK (
    aid INT,
    isbn VARCHAR(13),
    PRIMARY KEY (aid, isbn),
    FOREIGN KEY (aid) REFERENCES AUTHOR(aid),
    FOREIGN KEY (isbn) REFERENCES BOOK(isbn)
);

-- Create REVIEW table
CREATE TABLE REVIEW (
    rid INT PRIMARY KEY,
    isbn VARCHAR(13),
    rating DECIMAL(2, 1),
    FOREIGN KEY (isbn) REFERENCES BOOK(isbn)
);


-- Insert data into PUBLISHER table
INSERT INTO PUBLISHER (pid, pname, address, state, phone, emailid) VALUES
(1, 'Mehta', 'Street 12, Mumbai', 'Maharashtra', '9998887771', 'mehta@publish.com'),
(2, 'Penguin', 'Street 21, Delhi', 'Delhi', '8889996665', 'penguin@publish.com');

-- Insert data into BOOK table
INSERT INTO BOOK (isbn, book_title, category, price, copyright_date, year, page_count, pid) VALUES
('978-0143420362', 'Five Point Someone', 'Fiction', 299, '2004-05-01', 2004, 270, 1),
('978-8129135728', 'Half Girlfriend', 'Romance', 199, '2014-10-01', 2014, 260, 1),
('978-0143445136', 'The Girl in Room 105', 'Thriller', 399, '2018-10-09', 2018, 300, 1);

-- Insert data into AUTHOR table
INSERT INTO AUTHOR (aid, aname, state, city, zip, phone, url) VALUES
(101, 'Chetan Bhagat', 'Maharashtra', 'Mumbai', '400001', '9876543210', 'www.chetanbhagat.com'),
(102, 'Amish Tripathi', 'Maharashtra', 'Mumbai', '400002', '9876543220', 'www.amishtripathi.com');

-- Insert data into AUTHOR_BOOK table
INSERT INTO AUTHOR_BOOK (aid, isbn) VALUES
(101, '978-0143420362'),
(101, '978-8129135728'),

Query 1: Retrieve city, phone, URL of the author whose name is ‘CHETAN BHAGAT’

SELECT city, phone, url
FROM AUTHOR
WHERE aname = 'Chetan Bhagat';
Query 2: Retrieve book title, reviewable ID, and rating of all books

SELECT b.book_title, r.rid, r.rating
FROM BOOK b
JOIN REVIEW r ON b.isbn = r.isbn;
Query 3: Retrieve book title, price, author name, and URL for publishers ‘MEHTA’

SELECT b.book_title, b.price, a.aname, a.url
FROM BOOK b
JOIN PUBLISHER p ON b.pid = p.pid
JOIN AUTHOR_BOOK ab ON b.isbn = ab.isbn
JOIN AUTHOR a ON ab.aid = a.aid
WHERE p.pname = 'Mehta';
Query 4: Update the phone number of ‘MEHTA’ to 123456

UPDATE PUBLISHER
SET phone = '123456'
WHERE pname = 'Mehta';
Query 5: Calculate and display the average, maximum, and minimum price of each publisher

SELECT p.pname, AVG(b.price) AS avg_price, MAX(b.price) AS max_price, MIN(b.price) AS min_price
FROM BOOK b
JOIN PUBLISHER p ON b.pid = p.pid
GROUP BY p.pname;
Query 6: Delete details of all books having a page count less than 100

DELETE FROM BOOK
WHERE page_count < 100;

Query 7: Retrieve details of all authors residing in city Pune and whose name begins with a specific letter

SELECT aid, aname, state, city, zip, phone, url
FROM AUTHOR
WHERE city = 'Pune' AND aname LIKE 'A%';
