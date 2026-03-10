db = db.getSiblingDB('carCards');

db.createCollection('car');

db.car.insertMany([
        {
            "imageUrl": "images/Auto.png",
            "tradeName": "Audi",
            "model": "Flaschback 300",
            "prize": 50000
        },
        {
            "imageUrl": "images/Auto.png",
            "tradeName": "Opel",
            "model": "Manta SE",
            "prize": 20000
        },
        {
            "imageUrl": "images/Auto.png",
            "tradeName": "VW",
            "model": "Golf GL",
            "prize": 12000
        },
        {
            "imageUrl": "images/Auto.png",
            "tradeName": "Fiat",
            "model": "500",
            "prize": 15000
        },
    {
        "imageUrl": "images/Auto.png",
        "tradeName": "Alfa",
        "model": "Giulietta",
        "prize": 15000
    }
]);

// ===================== moviesdb =====================
db = db.getSiblingDB('moviesdb');

db.createCollection('movies');
db.createCollection('studios');
db.createCollection('people');

// Aufgabe 2 – Ergänzte Testdaten (movies)
db.movies.insertMany([
    {
        "_id": 156,
        "title": "The Terminator",
        "plot": "A cyborg killing machine is sent from 2029 to 1984 to execute a target.",
        "director_id": 217,
        "release_year": 1984,
        "imdb": {
            "rating": 8.0,
            "votes": 782237,
            "url": "https://www.imdb.com/title/tt0088247/"
        },
        "actors": [
            { "person_id": 180, "as": "Terminator", "salary": 15000000 },
            { "person_id": 13122, "as": "Sarah Connor" },
            { "person_id": 13123, "as": "Kyle Reese" }
        ]
    },
    {
        "_id": 157,
        "title": "Titanic",
        "plot": "A young couple falls in love aboard the ill-fated RMS Titanic.",
        "director_id": 217,
        "release_year": 1997,
        "imdb": {
            "rating": 7.9,
            "votes": 1100000,
            "url": "https://www.imdb.com/title/tt0120338/"
        },
        "actors": [
            { "person_id": 181, "as": "Jack Dawson", "salary": 2500000 },
            { "person_id": 182, "as": "Rose DeWitt Bukater", "salary": 2500000 }
        ]
    },
    {
        "_id": 158,
        "title": "Avatar",
        "plot": "A paraplegic marine dispatched to the moon Pandora on a unique mission.",
        "director_id": 217,
        "release_year": 2009,
        "imdb": {
            "rating": 7.8,
            "votes": 1200000,
            "url": "https://www.imdb.com/title/tt0499549/"
        },
        "actors": [
            { "person_id": 183, "as": "Jake Sully", "salary": 5000000 },
            { "person_id": 184, "as": "Neytiri", "salary": 4000000 }
        ]
    },
    {
        "_id": 159,
        "title": "The Dark Knight",
        "plot": "Batman faces the Joker, a criminal mastermind who wants to plunge Gotham City into anarchy.",
        "director_id": 218,
        "release_year": 2008,
        "imdb": {
            "rating": 9.0,
            "votes": 2700000,
            "url": "https://www.imdb.com/title/tt0468569/"
        },
        "actors": [
            { "person_id": 185, "as": "Batman", "salary": 10000000 },
            { "person_id": 186, "as": "Joker", "salary": 8000000 }
        ]
    }
]);

// Aufgabe 2 – Ergänzte Testdaten (studios)
db.studios.insertMany([
    {
        "_id": 1,
        "name": "Warner Bros.",
        "year_founded": 1923,
        "movies": [156, 159],
        "headquarters": {
            "address": "4000 Warner Blvd.",
            "city": "Burbank",
            "state": "California",
            "country": "United States"
        }
    },
    {
        "_id": 2,
        "name": "Paramount Pictures",
        "year_founded": 1912,
        "movies": [157],
        "headquarters": {
            "address": "5555 Melrose Ave.",
            "city": "Hollywood",
            "state": "California",
            "country": "United States"
        }
    },
    {
        "_id": 3,
        "name": "20th Century Studios",
        "year_founded": 1935,
        "movies": [158],
        "headquarters": {
            "address": "10201 W. Pico Blvd.",
            "city": "Los Angeles",
            "state": "California",
            "country": "United States"
        }
    }
]);

// Aufgabe 2 – Ergänzte Testdaten (people)
db.people.insertMany([
    { "_id": 180, "first_name": "Arnold", "last_name": "Schwarzenegger", "birth_date": "1947-07-30" },
    { "_id": 217, "first_name": "James", "last_name": "Cameron", "birth_date": "1954-08-16" },
    { "_id": 218, "first_name": "Christopher", "last_name": "Nolan", "birth_date": "1970-07-30" },
    { "_id": 181, "first_name": "Leonardo", "last_name": "DiCaprio", "birth_date": "1974-11-11" },
    { "_id": 182, "first_name": "Kate", "last_name": "Winslet", "birth_date": "1975-10-05" },
    { "_id": 183, "first_name": "Sam", "last_name": "Worthington", "birth_date": "1976-08-02" },
    { "_id": 184, "first_name": "Zoe", "last_name": "Saldana", "birth_date": "1978-06-19" },
    { "_id": 185, "first_name": "Christian", "last_name": "Bale", "birth_date": "1974-01-30" },
    { "_id": 186, "first_name": "Heath", "last_name": "Ledger", "birth_date": "1979-04-04" },
    { "_id": 13122, "first_name": "Linda", "last_name": "Hamilton", "birth_date": "1956-09-26" },
    { "_id": 13123, "first_name": "Michael", "last_name": "Biehn", "birth_date": "1956-07-31" }
]);
