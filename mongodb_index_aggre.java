//Aggregation


ankita> use Subject
switched to db Subject
Subject> db.Subject.insertMany([{ Rollno: 1, Name: "Ankita", Class: "TE", Div: "A", Subject: "DBMS", Marks: 80, Address: "Karjat" },{ Rollno: 2, Name: "Pratiksha", Class: "TE", Div: "B", Subject: "DBMS", Marks: 60, Address: "Pune" },{ Rollno: 3, Name: "Pranav", Class: "TE", Div: "A", Subject: "DBMS", Marks: 50, Address: "Pune" }, { Rollno: 1, Name: "Sanika", Class: "TE", Div: "B", Subject: "TOC", Marks: 70, Address: "karad" },{ Rollno: 4, Name: "Mehul", Class: "TE", Div: "A", Subject: "TOC", Marks: 90, Address: "Mumbai" },{ Rollno: 5, Name: "Adarsh", Class: "TE", Div: "B", Subject: "TOC", Marks: 75, Address: "Dehli" },{ Rollno: 6, Name: "Trisha", Class: "TE", Div: "A", Subject: "SPOS", Marks: 80, Address: "Pune" },{ Rollno: 7, Name: "Swapnali", Class: "TE", Div: "B", Subject: "SPOS", Marks: 50, Address: "Pune" },{ Rollno: 8, Name: "Chaitrali", Class: "TE", Div: "A", Subject: "SPOS", Marks: 65, Address: "Nashik" }]);

//1.Find Average of total marks in TOC

 >db.Subject.aggregate([ {$match:{Subject:"TOC"}}, {$group:{_id :"$Subject",Average:{$avg:"$Marks"}}}]);
[ { _id: 'TOC', Average: 78.33333333333333 } ]

//2.Find the No.Of student Division Wise.

Subject>  db.Subject.aggregate([{$group:{_id :"$Div",No_Of_Student:{$sum:1}}}]);
[ { _id: 'A', No_Of_Student: 5 }, { _id: 'B', No_Of_Student: 4 } ]

//3.Find the student of div B who scored min marks in DBMS

Subject>  db.Subject.aggregate([{$match:{Subject:"DBMS",Div:"B"}},{$group:{_id :"$Div",Min_marks:{$min:"$Marks"}}}]);
[ { _id: 'B', Min_marks: 60 } ]

//4. find the total sum of marks in SPOS of student staying in Pune.
Subject> db.Subject.aggregate([ {$match:{Subject:"SPOS",Address:"Pune"}}, {$group:{_id :"$Subject",totalMarks:{$sum:"$Marks"}}}]);
[ { _id: 'SPOS', totalMarks: 130 } ]

//5.Find the division wise count of Student whose DBMS
>  db.Subject.aggregate([ {$match:{Subject:"DBMS"}}, {$group:{_id :"$Div",count_of_sudent:{$sum:1}}}]);
[ { _id: 'A', count_of_sudent: 2 }, { _id: 'B', count_of_sudent: 1 } ]

//6.Find the count of each City.
Subject>  db.Subject.aggregate([  {$group:{_id :"$Address",count_of_city:{$sum:1}}}]);

//7. Create Simple and Compound Indexing
//Simple Index on Rollno
db.student.createIndex({ Rollno: 1 });

//Compound Index on Class and Div
db.student.createIndex({ Class: 1, Div: 1 });

--------------------------------------------------------------------------------------------------------------------------------------------------------------------


//4.
//Index
Subject> db.Subject.insertMany([{ Rollno: 2, Name: "Pratiksha", Class: "TE", Div: "B", Subject: "DS", Marks: 70, Address: "Satara" },{ Rollno: 8, Name: "Chaitrali", Class: "TE", Div: "A", Subject: "DS", Marks: 60, Address: "Pune" },{ Rollno: 1, Name: "Ankita", Class: "TE", Div: "A", Subject: "DS", Marks: 50, Address: "Pune" },{ Rollno: 7, Name: "Swapnali", Class: "TE", Div: "B", Subject: "DS", Marks: 76, Address: "karad" }]);

 

//1. Find the maximum marks of student in DS who stay in the same city

Subject> db.Subject.aggregate([ {$match:{Subject:"DS"}}, {$group:{_id :"$Address",maxMarks:{$max:"$Marks"}}}]);
[
  { _id: 'Satara', maxMarks: 70 },
  { _id: 'Pune', maxMarks: 60 },
  { _id: 'karad', maxMarks: 76 }
]

//2. Calculates the average of given marks.
Subject> db.Subject.aggregate([{$group:{_id:null,average:{$avg:"$Marks"}}}]);
[ { _id: null, average: 67.38461538461539 } ]

//3. Inserts the any value to an array in the resulting document.
Subject> db.Subject.updateOne({ Rollno: 1 }, { $push: { comments: "Excellent performance!" } });


//4. Create a compound index on name and class.
Subject> db.Subject.createIndex({ Name: 1, Class: 1 }); 
Name_1_Class_1

//5. Create single index on name.
Subject> db.Subject.createIndex({ Name: 1 }); 
Name_1


//6. Delete index of name.
Subject> db.Subject.dropIndex("Name_1");
{ nIndexesWas: 3, ok: 1 }

Subject> db.Subject.getIndexes();
[
  { v: 2, key: { _id: 1 }, name: '_id_' },
  { v: 2, key: { Name: 1, Class: 1 }, name: 'Name_1_Class_1' }
]

//6. Implement an Aggregation Pipeline Using Multiple Pipeline Operations

db.student.aggregate([
    { $match: { Class: "TE" } },  // Filter by Class TE
    { $group: { _id: "$Subject", maxMarks: { $max: "$Marks" } } },  // Group by subject and find max marks
    { $sort: { maxMarks: -1 } }  // Sort by maxMarks in descending order
]);

