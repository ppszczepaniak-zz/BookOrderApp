package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fi.iki.elonen.NanoHTTPD.*;
import storage.BookStorage;
import storage.CustomerStorage;
import storage.implementations.BookStorageImpl;
import storage.implementations.CustomerStorageImpl;
import type.Book;

import static fi.iki.elonen.NanoHTTPD.Response.Status.INTERNAL_ERROR;
import static fi.iki.elonen.NanoHTTPD.Response.Status.OK;
import static fi.iki.elonen.NanoHTTPD.newFixedLengthResponse;

public class CustomerController {

    private CustomerStorage customerStorage = new CustomerStorageImpl(); //creates storage of customers

    public CustomerStorage getCustomerStorage() {
        return customerStorage;
    }

    private static final String CUSTOMER_IT_PARAM_NAME = "customerId"; //used to get book from storage

    public Response serveAddCustomerRequest(IHTTPSession session) {
//        ObjectMapper objectMapper = new ObjectMapper(); //to convert Java objects into JSON
//        long customerId;
//        String lengthHeader = session.getHeaders().get("content-length"); //reads content length (in bytes) from headers (indicates the size of the entity-body)
//        int contentLength = Integer.parseInt(lengthHeader); //converts to int
//        byte[] buffer = new byte[contentLength]; //creates table of that many bytes (buffer)
//
//        try {
//            //noinspection ResultOfMethodCallIgnored
//            session.getInputStream().read(buffer, 0, contentLength);    /* reads "body" of the request -> read(buffer, offset, length)
//                                                                             buffer - data will be put into this
//                                                                             offset - starting point of reading (skip something?)
//                                                                             length - how much do we read */
//            String requestBody = new String(buffer).trim();       //creates string from updated buffer data, trims whitespaces
//            Book requestBook = objectMapper.readValue(requestBody, Book.class); /*creates book from JSON data -> readValue(content,value type)
//                                                                                content - String in JSON, used to create object
//                                                                                value type - we point Java class, of which object should be created*/
//            bookId = bookStorage.addBook(requestBook); //adds book to bookStorage and returns ID of the book
//
//        } catch (Exception e) {
//            System.err.println("Error during process request: \n" + e);
//            return newFixedLengthResponse(INTERNAL_ERROR, "text/plain", "Internal error! Book has not been added.");
//        }
//
//        return newFixedLengthResponse(OK, "text/plain", "Book has been successfully added. The ID of the book is: " + bookId); //zwraca liste ksiazek w JSON
        return null;
    }

    public Response serveGetAllCustomersRequest(IHTTPSession session) {
        ObjectMapper objectMapper = new ObjectMapper(); //to convert Java objects into JSON
        String response;
        try {
            response = objectMapper.writeValueAsString(customerStorage.getAllCustomers());  //przypisuje liste ksiazek do Stringa w formacie JSON

        } catch (JsonProcessingException e) {
            System.err.println("Error during process request: \n" + e);
            return newFixedLengthResponse(INTERNAL_ERROR, "text/plain", "Internal error! Can't get all customers.");
        }
        return newFixedLengthResponse(OK, "application/json", response); //zwraca liste ksiazek w JSON
    }

    public Response serveGetCustomerRequest(IHTTPSession session) {
        return null;
    }
}
