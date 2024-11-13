Name: Ankita Amol Khater
Div: A        Batch:D
Roll No.:3101058


ankita> db.Students.find();
[
  {
    _id: ObjectId('66fb950bdb22a12c7e964033'),
    id: 1,
    div: 'A',
    marks: 80
  },
  {
    _id: ObjectId('66fb950bdb22a12c7e964034'),
    id: 2,
    div: 'B',
    marks: 70
  },
  {
    _id: ObjectId('66fb950bdb22a12c7e964035'),
    id: 3,
    div: 'A',
    marks: 45
  },
  {
    _id: ObjectId('66fb950bdb22a12c7e964036'),
    id: 4,
    div: 'A',
    marks: 60
  },
  {
    _id: ObjectId('66fb950bdb22a12c7e964037'),
    id: 5,
    div: 'B',
    marks: 85
  },
  {
    _id: ObjectId('66fb950bdb22a12c7e964038'),
    id: 6,
    div: 'B',
    marks: 78
  },
  {
    _id: ObjectId('66fba2fadb22a12c7e964039'),
    id: 8,
    div: 'A',
    marks: 35
  }
]


ankita> var map = function ()
 { var marks = this.marks; 
if (marks > 70) {
 emit("Highscores", 1);
} else if (marks > 40 && marks <= 70) {
 emit("Average Score", 1); }
else if (marks <= 40) { 
 emit("Failed", 1); } };

ankita> var reduce = function (key, values) 
{ return Array.sum(values);}

ankita> db.Students.mapReduce( map,reduce ,
{ out:"Total"} );
{ result: 'Total', ok: 1 }
ankita> db.Total.find();
[
  { _id: 'Highscores', value: 3 },
  { _id: 'Failed', value: 1 },
  { _id: 'Average Score', value: 3 }
]


//6.
//2.
ankita> db.City.insertMany([
  ...     { cityname: "City A", area: 500, population: 1200000 }, // 12 Lakhs
  ...     { cityname: "City B", area: 300, population: 900000 },  // 9 Lakhs
  ...     { cityname: "City C", area: 800, population: 500000 },  // 5 Lakhs
  ...     { cityname: "City D", area: 400, population: 2000000 }  // 20 Lakhs
  ... ]);
 
  
 
  
  ankita> var mapFunction = function() {
  ...     var population = this.population;
  ...
  ...     if (population > 1000000) {
  ...         emit("HIGHLY POPULATED", 1);
  ...     } else {
  ...         emit("LOW POPULATED", 1);
  ...     }
  ... };
  
  ankita> var reduceFunction = function(key, values) {
  ...     return Array.sum(values);
  ... };
  
  ankita> db.City.mapReduce(
  ...     mapFunction,
  ...     reduceFunction,
  ...     {
  ...         out: "city_population_classification"
  ...     }
  ... );
  { result: 'city_population_classification', ok: 1 }
  ankita> db.city_population_classification.find();
  [
    { _id: 'HIGHLY POPULATED', value: 2 },
    { _id: 'LOW POPULATED', value: 2 }
  ]
  

 
