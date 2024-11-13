CREATE DATABASE company_sales;

USE company_sales;

-- Create Sales_Representative table
CREATE TABLE Sales_Representative (
    rep_no INT PRIMARY KEY,
    name VARCHAR(50),
    re_office VARCHAR(50),
    quota DECIMAL(10, 2),
    sales DECIMAL(10, 2)
);

-- Create Customers table
CREATE TABLE Customers (
    cust_no INT PRIMARY KEY,
    company VARCHAR(100),
    cust_rep INT,
    credit_limit DECIMAL(10, 2),
    FOREIGN KEY (cust_rep) REFERENCES Sales_Representative(rep_no)
);

-- Create Orders table
CREATE TABLE Orders (
    order_no INT PRIMARY KEY,
    cust INT,
    product VARCHAR(50),
    quantity INT,
    amount DECIMAL(10, 2),
    disc DECIMAL(10, 2),
    FOREIGN KEY (cust) REFERENCES Customers(cust_no)
);

Query Set 1:
List for each customer: customer name, their rep’s name, their rep’s office number:


SELECT c.company AS customer_name, sr.name AS rep_name, sr.re_office AS rep_office
FROM Customers c
JOIN Sales_Representative sr ON c.cust_rep = sr.rep_no;
List orders over $25,000 including the name of the salesperson who took the order and the name of the customer who placed it:


SELECT o.order_no, o.amount, c.company AS customer_name, sr.name AS salesperson_name
FROM Orders o
JOIN Customers c ON o.cust = c.cust_no
JOIN Sales_Representative sr ON c.cust_rep = sr.rep_no
WHERE o.amount > 25000;
Find the products which have been sold to TCS:


SELECT product
FROM Orders o
JOIN Customers c ON o.cust = c.cust_no
WHERE c.company = 'TCS';
Find the company which has been offered maximum discount:


SELECT c.company, MAX(o.disc) AS max_discount
FROM Orders o
JOIN Customers c ON o.cust = c.cust_no
GROUP BY c.company
ORDER BY max_discount DESC
LIMIT 1;
Find the sales representatives who work in the same office:


SELECT name, re_office
FROM Sales_Representative
GROUP BY re_office
HAVING COUNT(*) > 1;
Find the credit limit of companies and the discount they have received:

SELECT c.company, c.credit_limit, SUM(o.disc) AS total_discount
FROM Customers c
JOIN Orders o ON c.cust_no = o.cust
GROUP BY c.company, c.credit_limit;



Query Set 2:
List for each customer: customer name, credit limit, rep name serving the customer, and rep sales:


SELECT c.company, c.credit_limit, sr.name AS rep_name, sr.sales
FROM Customers c
JOIN Sales_Representative sr ON c.cust_rep = sr.rep_no;
List all orders showing number and amount, and name and credit limit of customer:


SELECT o.order_no, o.amount, c.company AS customer_name, c.credit_limit
FROM Orders o
JOIN Customers c ON o.cust = c.cust_no;
Find the product-wise sale amount of products that exceed $12,000:


SELECT product, SUM(amount) AS total_sales
FROM Orders
GROUP BY product
HAVING SUM(amount) > 12000;
Find the names of customers and representatives who have been involved in the sale of software:

SELECT o.amount, c.company AS customer_name, sr.name AS rep_name
FROM Orders o
JOIN Customers c ON o.cust = c.cust_no
JOIN Sales_Representative sr ON c.cust_rep = sr.rep_no
WHERE o.product = 'Software';
Find the credit limit of companies and the discount they have received:


SELECT c.company, c.credit_limit, SUM(o.disc) AS total_discount
FROM Customers c
JOIN Orders o ON c.cust_no = o.cust
GROUP BY c.company, c.credit_limit;
Find the sales representatives who work in the same office:


SELECT name, re_office
FROM Sales_Representative
GROUP BY re_office
HAVING COUNT(*) > 1;