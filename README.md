# E-Commerce Application

## Table of Contents
1. [Introduction](#introduction)
2. [Prerequisites](#prerequisites)
3. [Database Configuration](#database-configuration)
4. [Running the Application](#running-the-application)
5. [API Endpoints](#api-endpoints)
6. [Usage](#usage)

## Introduction
This is an e-commerce application built with Spring Boot. It supports role-based authentication and various functionalities for admins, sellers, and buyers.

## Prerequisites
- Java 17 or later
- Maven 3.6.0 or later
- MySQL

## Database Configuration
To configure the database, update the `application.properties` file located in the `src/main/resources` directory with your MySQL database details.
server.port = 8090
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
spring.jpa.properties.hibernate.format_sql=true

## API Endpoints

## Register a user
1. POST http://localhost:8090/auth/user
Request Body:
{
"name":"rahul kumar",
"username":"rahul",
"email":"rahul@gmail.com",
"password":"rahul",
"mobile":"123456",
"createby":"rahul",
"updateBy":"rahul",
"roles":"SELLER" or "BUYER" or "ADMIN"
}

Response:
{
"userId": 1,
"name": "rahul kumar",
"username": "rahul",
"email": "rahul@gmail.com",
"password": "$2a$10$hQyjKNoDFrfYfFQoQgs1qe4tSUQ8.YQqxNQJyl.s6UmMJRkRMd8Ly",
"mobile": "708836847",
"roles": "SELLER"
}

2. Login
POST http://localhost:8090/auth/login
Request Body:
{
"username": "user",
"password": "password"
}

Response:
{
"jwtToken": "token",
"username": "username"
}

## Seller Endpoints
1. add Product
POST http://localhost:8090/Seller/addProduct
{
"name": "OPPO",
"image": "",
"quantity": 2,
"price": 18999,
"weight": "200gm",
"description": "30MP Camera"
}
Response :
{
"id": 2,
"name": "OPPO",
"image": "",
"quantity": 2,
"price": 18999,
"weight": "200gm",
"description": "30MP Camera",
"createBy": "rahul kumar",
"createDate": "2024-06-12T11:43:20.161+00:00",
"updtBy": "rahul kumar",
"updtDate": "2024-06-12T11:43:20.161+00:00",
"customerId": 1
}

2. Fetch All Products by Seller
GET http://localhost:8090/Seller/getProducts

3. Fetch Single Products by Seller
GET http://localhost:8090/Seller/getProduct/{productId}

4. update Product
PUT http://localhost:8090/Seller/updateProduct/{productId}
 RequestBody :
   {
   "id": 1,
   "name": "Samsung",
   "image": "",
   "quantity": 2,
   "price": 18999,
   "weight": "200gm",
   "description": "30MP Camera",
   "createBy": "rahul kumar",
   "createDate": "2024-06-12T11:41:41.459+00:00",
   "updtBy": "rahul kumar",
   "updtDate": "2024-06-12T11:41:41.459+00:00",
   "customerId": 1
   }
  Response :
   {
   "id": 1,
   "name": "Samsung",
   "image": "",
   "quantity": 2,
   "price": 18999,
   "weight": "200gm",
   "description": "30MP Camera",
   "createBy": "rahul kumar",
   "createDate": "2024-06-12T11:41:41.459+00:00",
   "updtBy": "rahul kumar",
   "updtDate": "2024-06-12T11:53:06.547+00:00",
   "customerId": 1
   }
5. delete the product
   DELETE http://localhost:8090/Seller/deleteProduct/{productId}

## Buyer Endpoints
(First Register as a buyer and login with buyer credentials then use these endpoints)

1. fetch the All Products and their details
 GET http://localhost:8090/Buyer/searchAllProductByBuyer

2. fetch only one product
GET http://localhost:8090/Buyer/searchOneProductByBuyer/{productId}


3. Place the order 
POST http://localhost:8090/Buyer/purchaseOrder
Request Body :
{
"product":{
"id": 1,
"name": "Samsung",
"image": "",
"quantity": 2,
"price": 18999,
"weight": "200gm",
"description": "30MP Camera"
},
"quantity":2
}

Response Body :
{
"id": 1,
"productId": 1,
"customerId": 2,
"quantity": 2,
"totalprice": 37998.0,
"orderDate": "2024-06-12T12:06:55.514+00:00"
}

## Admin Endpoints
(admin can access both endpoints seller and buyer)
Manage users 
1. Fetch All users
GET http://localhost:8090/Admin/user

2. fetch One User
GET http://localhost:8090/Admin/user/{userId}

3. update users
PUT http://localhost:8090/Admin/user/{userId}
Request Body:
   {
   "userId": 2,
   "name": "name",
   "username": "username",
   "email": "example@gmail.com",
   "password": "12345",
   "mobile": "1234556",
   "roles": "BUYER"
   }

Manage Products
1. Get All Products
    GET http://localhost:8090/Admin/getAllProducts
2. Get One Product
    GET http://localhost:8090/Admin/getOneProduct/1
3. add Product by Admin(Own Product) (same as seller with request Body)

4. upadte seller product by admin
PUT http://localhost:8090/Seller/updateProduct/{productId}
Request Body :
{
   "id": 1,
   "name": "Samsung 4G",
   "image": "",
   "quantity": 2,
   "price": 18999,
   "weight": "200gm",
   "description": "300MP Camera",
   "createBy": "rahul kumar",
   "createDate": "2024-06-12T11:41:41.459+00:00",
   "updtBy": "rahul kumar",
   "updtDate": "2024-06-12T11:53:06.547+00:00",
   "customerId": 1
   }
Response :
   {
   "id": 1,
   "name": "Samsung 4G",
   "image": "",
   "quantity": 2,
   "price": 18999,
   "weight": "200gm",
   "description": "300MP Camera",
   "createBy": "rahul kumar",
   "createDate": "2024-06-12T11:41:41.459+00:00",
   "updtBy": "krishan vasudev yadav",// name of the admin whose update this product
   "updtDate": "2024-06-12T12:41:54.555+00:00",
   "customerId": 1
   }

## Note:- Admin do all the operation which is perform by Seller
## Usage
To interact with the API, you can use tools like Postman or cURL. Ensure you include the JWT token in the Authorization header for protected routes.





