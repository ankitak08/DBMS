1. Create Database and Collections:


use SITS

// Create Teachers collection
db.createCollection("Teachers")

// Insert sample data for Teachers collection
db.Teachers.insertMany([
   { Tname: "John", dno: 1, dname: "Computer Science", experience: 10, salary: 60000, date_of_joining: new Date("2015-06-15") },
   { Tname: "Alice", dno: 2, dname: "Mathematics", experience: 7, salary: 45000, date_of_joining: new Date("2018-03-12") },
   { Tname: "Mark", dno: 1, dname: "Computer Science", experience: 5, salary: 55000, date_of_joining: new Date("2020-09-21") },
   { Tname: "Eva", dno: 3, dname: "Physics", experience: 12, salary: 70000, date_of_joining: new Date("2012-05-18") }
])

// Create Students collection
db.createCollection("Students")

// Insert sample data for Students collection
db.Students.insertMany([
   { Sname: "Ravi", roll_no: 101, class: "TE" },
   { Sname: "Bhakti", roll_no: 102, class: "SE" },
   { Sname: "Manoj", roll_no: 103, class: "FE" }
])

//1. Display the department-wise average salary:

db.Teachers.aggregate([
   { $group: { _id: "$dname", avg_salary: { $avg: "$salary" } } }
])

//2. Display the number of employees working in each department:

db.Teachers.aggregate([
   { $group: { _id: "$dname", no_of_employees: { $sum: 1 } } }
])

//3. Display the department-wise total salary of departments having total salary greater than or equal to 50000:

db.Teachers.aggregate([
   { $group: { _id: "$dname", total_salary: { $sum: "$salary" } } },
   { $match: { total_salary: { $gte: 50000 } } }
])

//4. Queries using different operators like max, min, etc.:
//Maximum salary in the Teachers collection:

db.Teachers.aggregate([
   { $group: { _id: null, max_salary: { $max: "$salary" } } }
])

//Minimum salary in the Teachers collection:

db.Teachers.aggregate([
   { $group: { _id: null, min_salary: { $min: "$salary" } } }
])

//Average experience in the Teachers collection:

db.Teachers.aggregate([
   { $group: { _id: null, avg_experience: { $avg: "$experience" } } }
])

//5. Create a unique index on any field (e.g., roll_no in the Students collection):

db.Students.createIndex({ roll_no: 1 }, { unique: true })

//6.Create a compound index (e.g., on Tname and salary in the Teachers collection):

db.Teachers.createIndex({ Tname: 1, salary: -1 })

//7. Show all the indexes created in the database "SITS":

db.getCollectionNames().forEach(function(collection) {
   print("Indexes for collection: " + collection)
   printjson(db[collection].getIndexes())
})

//8.Show all the indexes created in the Teachers and Students collections:
//For Teachers collection:


db.Teachers.getIndexes()

