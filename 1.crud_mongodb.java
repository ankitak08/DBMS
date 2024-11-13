ankita> db.createCollection("Student")
{ ok: 1 }
ankita> db.Student.insertMany( [ { Fname: "Ankita", Lname: "Khater", Class: "TE-A", Gender: "Female", Age: 20, Grd_Point: 40 }, { Fname: "Pratiksha", Lname: "Karande", Class: "TE-A", Gender: "Female", Age: 21, Grd_Point: 50 }, { Fname: "Adarsh", Lname: "Khater", Class: "TE-B", Gender: "Male", Age: 18, Grd_Point: 80 }]);

//1. select all documents from the collection &quot;student&quot; which satisfying the following condition -
a.  gender of student is male and
b. class of the student is TE A and
c. grd_point of the student is greater than equal to 31 with comparison operator.

ankita> db.Student.find({$and: [{Gender: "Male"}, {Class:"TE-A"}, {Grd_Point:{$gte:31}}]},{Fname:1,Lname:1,Class:1,Grd_Point:1});
[
  {
    _id: ObjectId('66eaee36ac7a70be18964038'),
    Fname: 'Pranav',
    Lname: 'Sancheti',
    Class: 'TE-A',
    Grd_Point: 34
  }
]
2. select such a documents from the collection &quot;student” who belongs to “Bangalore” and whose age is 19.
ankita> db.Student.find({$and: [{Address: "Banglore"}, {Age:19}]},{Fname:1,Lname:1,Class:1,Address:1});
[
  {
    _id: ObjectId('66eae920ac7a70be18964036'),
    Fname: 'Sanika',
    Lname: 'Kanchan',
    Class: 'TE-B',
    Address: 'Banglore'
  }
]

3.Update course to “MCA” from collection “student” whose first name is “ Bhakti”

ankita> db.Student.updateOne({Fname:"Bhakti"},{$set:{Course:"MCA"}});
{
  acknowledged: true,
  insertedId: null,
  matchedCount: 1,
  modifiedCount: 0,
  upsertedCount: 0
}
ankita> db.Student.find({Fname:”Bhakti”});
  {
    _id: ObjectId('66eae920ac7a70be18964037'),
    Fname: 'Bhakti',
    Lname: 'Jadhav',
    Class: 'TE-B',
    Gender: 'Female',
    Age: 21,
    Grd_Point: 28,
    Address: 'Solapur',
    Course: 'MCA'
  }

//4. Delete the document from collection student where age is “18” and gender is “ male”.
ankita> db.Student.deleteOne({$and: [{Age:18}, {Gender:"Male"}]},{Fname:1,Lname:1,Class:1,Age:1,Gender:1});
{ acknowledged: true, deletedCount: 1 }
ankita> db.Student.find();

//5. Delete document from collection student where last name is Jadhav and belongs to class TE B

ankita> db.Student.deleteOne({$and: [{Lname:"Jadhav"}, {Class:"TE-B"}]});
{ acknowledged: true, deletedCount: 1 }
ankita> db.Student.find();

//
db.student.find({
  $nor: [
      { grd_point: { $gt: 30 } },
      { city: "Mumbai" }
  ]
});

//
db.student.save({
  _id: ObjectId(),
  firstName: "Sameer",
  lastName: "Gupta",
  gender: "male",
  class: "SE C",
  grd_point: 27,
  city: "Pune",
  age: 21,
  course: "MBA"
});


let student = db.student.findOne({ firstName: "Rahul" });
student.course = "MCA";  // Update the course
db.student.save(student);



//2.


//1. select all documents from the collection &quot;student&quot; which satisfying the following condition -
a.  gender of student is male or
b. grd_point of the student is greater than equal to 31 with comparison operator.

ankita> db.Student.find({$or: [{Gender: "Male"}, {Grd_Point:{$gte:31}}]},{Fname:1,Lname:1,Gender:1,Grd_Point:1});

//2. Update age to 20 from collection “student” whose first name is “ Riyansh”
ankita> db.Student.updateOne( { Fname: "Riyansh"},{$set:{Age :20}});

//3. Update or save the city to nagpur and State to MH collection “student” whose last name is “soniminde”
ankita> db.Student.updateOne( { Lname: "Sonimide"},{$set:{Address :"Nagpur",State:"MH"}});

//4. Students have cancelled admission from the college who is belongs to state “KA”.
ankita> db.Student.deleteOne({State:"KA"});
{ acknowledged: true, deletedCount: 1 }

//5.
db.student.deleteOne({
  firstName: "Evanshika",
  age: { $lt: 18 }
});

//6.
db.student.find({
  $nor: [
      { grd_point: { $lt: 30 } },
      { city: "Mumbai" }
  ]
});
