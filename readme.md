#BookOrderApp 
[![Build Status](https://travis-ci.org/ppszczepaniak/BookOrderApp.svg?branch=master)](https://travis-ci.org/pedro-programator/BookOrderApp)

Web app (REST API, NanoHTTPD server) which allows to CRUD books, clients and orders in PostgreSQL

Postgres SQL - create database via these commands:
****
CREATE DATABASE bookorder;

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
