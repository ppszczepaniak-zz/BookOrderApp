package dispatcher;

import controller.BookController;
import controller.CustomerController;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;

import static fi.iki.elonen.NanoHTTPD.Method.GET;
import static fi.iki.elonen.NanoHTTPD.Method.POST;
import static fi.iki.elonen.NanoHTTPD.Response.Status.NOT_FOUND;
import static fi.iki.elonen.NanoHTTPD.newFixedLengthResponse;

public class RequestUrlMapper {

    private final static String ADD_BOOK_URL = "/book/add";
    private final static String GET_BOOK_URL = "/book/get";
    private final static String GET_ALL_BOOK_URL = "/book/getAll";
    private final static String ADD_CUSTOMER_URL = "/customer/add";
    private final static String GET_CUSTOMER_URL = "/customer/get";
    private final static String GET_ALL_CUSTOMER_URL = "/customer/getAll";
    private BookController bookController = new BookController();
    private CustomerController customerController = new CustomerController();

    public CustomerController getCustomerController() {
        return customerController;
    }

    public BookController getBookController() {
        return bookController;
    }

    public Response delegateRequest(IHTTPSession session) {
        //BOOKS
        if (GET.equals(session.getMethod()) && GET_BOOK_URL.equals(session.getUri())) {
            return bookController.serveGetBookRequest(session);
        } else if (GET.equals(session.getMethod()) && GET_ALL_BOOK_URL.equals(session.getUri())) {
            return bookController.serveGetAllBooksRequest(session);
        } else if (POST.equals(session.getMethod()) && ADD_BOOK_URL.equals(session.getUri())) {
            return bookController.serveAddBookRequest(session);

        //CUSTOMERS
        } else if (GET.equals(session.getMethod()) && GET_CUSTOMER_URL.equals(session.getUri())) {
            return customerController.serveGetCustomerRequest(session);
        } else if (GET.equals(session.getMethod()) && GET_ALL_CUSTOMER_URL.equals(session.getUri())) {
            return customerController.serveGetAllCustomersRequest(session);
        } else if (POST.equals(session.getMethod()) && ADD_CUSTOMER_URL.equals(session.getUri())) {
            return customerController.serveAddCustomerRequest(session);
        }

        return newFixedLengthResponse(NOT_FOUND, "text/plain", "404! These are not the droids you're looking for...");
    }
}
