#BookOrderApp 
[![Build Status](https://travis-ci.org/ppszczepaniak/BookOrderApp.svg?branch=master)](https://travis-ci.org/pedro-programator/BookOrderApp)

Web app (REST API, NanoHTTPD server) which allows to CRUD books, clients and orders in PostgreSQL

Postgres SQL - create database via these commands:

create table books
(
   book_id bigint not null,
   title character varying(256) NOT NULL,
   author character varying(256) NOT NULL,
   pages_sum integer NOT NULL,
   year_of_published integer,
   publishing_house character varying(256)
);




ALTER TABLE books ADD CONSTRAINT book_id_pk PRIMARY KEY (book_id);
