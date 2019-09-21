package storage;

import type.Book;
import type.Customer;

import java.util.List;

public interface CustomerStorage {
    Customer getCustomer(long id);

    List<Customer> getAllCustomers();

    long addCustomer(Customer customer); //shoud return customerId

    void clearTableCustomers();

    //TODO add Delete customer feature
}
