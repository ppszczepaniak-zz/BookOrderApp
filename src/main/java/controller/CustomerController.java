package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fi.iki.elonen.NanoHTTPD.*;
import storage.BookStorage;
import storage.CustomerStorage;
import storage.implementations.BookStorageImpl;
import storage.implementations.CustomerStorageImpl;
import type.Book;
import type.Customer;

import java.util.List;
import java.util.Map;

import static fi.iki.elonen.NanoHTTPD.Response.Status.*;
import static fi.iki.elonen.NanoHTTPD.newFixedLengthResponse;

public class CustomerController {

    private CustomerStorage customerStorage = new CustomerStorageImpl(); //creates storage of customers

    public CustomerStorage getCustomerStorage() {
        return customerStorage;
    }

    private static final String CUSTOMER_ID_PARAM_NAME = "customerId"; //used to get customer from storage

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
        Map<String, List<String>> requestParameters = session.getParameters(); //takes all params from session

        if (requestParameters.containsKey(CUSTOMER_ID_PARAM_NAME)) { //if there is a parameter customerID, then...
            List<String> customerIdparams = requestParameters.get(CUSTOMER_ID_PARAM_NAME); //gets list of parameters
            String customerIdParam = customerIdparams.get(0); //gets 1st
            long customerID;

            try {
                customerID = Long.parseLong(customerIdParam);
            } catch (NumberFormatException nfe) { //if NaN
                System.err.println("Error during converting of request parameter: \n" + nfe);
                return newFixedLengthResponse(BAD_REQUEST, "text/plain", "Request parameter 'customerID' must be a number!");
            }

            Customer foundCustomer = customerStorage.getCustomer(customerID);
            if (foundCustomer != null) { //if this customer we take isn't empty
                ObjectMapper objectMapper = new ObjectMapper(); //to convert Java objects into JSON

                try {
                    String response = objectMapper.writeValueAsString(foundCustomer); //przypisuje customera do Stringa w formacie JSON
                    return newFixedLengthResponse(OK, "application/json", response); //zwracam string w JSON

                } catch (JsonProcessingException e) {
                    System.err.println("Error during process request: \n" + e);
                    return newFixedLengthResponse(INTERNAL_ERROR, "text/plain", "Internal error! Can't read a customer.");
                }

            } else {
                return newFixedLengthResponse(NOT_FOUND, "application/json", ""); //return when such customer hasn't been found
            }

        } else {
            return newFixedLengthResponse(BAD_REQUEST, "text/plain", "Incorrect request parameter!"); //return when there there's no customerID param in request
        }
    }

}
