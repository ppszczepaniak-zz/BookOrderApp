#BookOrderApp 
[![Build Status](https://travis-ci.org/ppszczepaniak/BookOrderApp.svg?branch=master)](https://travis-ci.org/ppszczepaniak/BookOrderApp)

Web app (REST API, NanoHTTPD server) which allows to CRUD books, clients and orders in PostgreSQL

### Postgres SQL - create database via these commands:
****
create db
```
CREATE DATABASE bookorder;

```
***
books table
```
create table books
(
   book_id bigint not null,
   title character varying(256) NOT NULL
);


ALTER TABLE books ADD CONSTRAINT book_id_pk PRIMARY KEY (book_id);

CREATE SEQUENCE sequence_books
   AS BIGINT 
   INCREMENT BY 1 
   MINVALUE 1 
   START WITH 1;
   
 UPDATE books SET book_id=(NEXTVAL('sequence_books'));
``` 
***
customers table
```
create table customers
(
   customer_id bigint not null,
   name character varying(256) NOT NULL
);

ALTER TABLE customers ADD CONSTRAINT customers_id_pk PRIMARY KEY (customer_id);

CREATE SEQUENCE sequence_customers
   AS BIGINT 
   INCREMENT BY 1 
   MINVALUE 1 
   START WITH 1;
   
UPDATE customers SET customer_id=(NEXTVAL('sequence_customers'));
``` 
***
####How stuff works:  
>in Postman:
     * POST http://localhost:8080/book/add +in BODY: fill some JSON, see bookExample.txt  
     * GET http://localhost:8080/book/getAll  
     * GET http://localhost:8080/book/get +in PARAMS: add bookId, value: [number - first add book, and find one ID by getAll]

use these parameters:
- customerId, bookid
