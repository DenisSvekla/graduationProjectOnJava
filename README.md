# graduationProjectOnJava application

This project is a simple version of service for car service. Supported extension is only .jpg. By default, application
have an admin account with username 'root' and password 'root'.

## Database

Application use PostgreSQL database. For start the application you need Postgres server. password, port and link you can find on 
application.property file. DataBase has following table

* Table _user_table_ - contains information about users;
* Table _phone_ - contains information about phone which use users;
* Table _lt_comments_car_ - contains comments for cars;
* Table _car_ - contains data about cars;
* Table _subscriptions_ - contains information about users subscription
* Table _lt_favorites_car_user_car_ - table for favorite car for user;

## Available endpoints for users

* http://localhost:8080/auth - POST method, authorization 
* http://localhost:8080/user - GET method, getAllUsers
* http://localhost:8080/user/{ID} - GET method, getUserById
* http://localhost:8080/user/{ID} - DELETE method, delete user by id
* http://localhost:8080/user/{ID} - PUT method, update user by id
* http://localhost:8080/user/registration - POST method, registration

* http://localhost:8080/user/{id}/subscription - GET method, get subscription
* http://localhost:8080/user/{id}/subscription - POST method, create or update subscription

* http://localhost:8080/user/{id}/phone - GET method, get all phones
* http://localhost:8080/user/{id}/phone - POST method, create a phone
* http://localhost:8080/user/{id}/phone{idPhone} - delete method, delete a phone

* http://localhost:8080/car - GET method, get all cars
* http://localhost:8080/car - PUT method, create a car
* http://localhost:8080/car - POST method, update a car
* http://localhost:8080/car/{id} - GET method, get car by id
* http://localhost:8080/car/{id}/addComment - PUT method, add comment for car
* http://localhost:8080/car/{id}/addCar - PUT method, add car as favorite
* http://localhost:8080/car/{id}/deleteCar - DELETE method, delete car as favorite
