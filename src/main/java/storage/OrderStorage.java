package storage;

import type.Order;

import java.util.List;

public interface OrderStorage {
    Order getOrder(long orderId);

    List<Order> getAllOrders();

    long addOrder(Order order); //shoud return OrderId

    void clearTableOrders();

    //TODO add Delete order feature
}
