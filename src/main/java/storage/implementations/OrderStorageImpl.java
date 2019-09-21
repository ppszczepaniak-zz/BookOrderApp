package storage.implementations;

import storage.OrderStorage;
import type.Order;

import java.util.List;

public class OrderStorageImpl implements OrderStorage {
    @Override
    public Order getOrder(long orderId) {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public long addOrder(Order order) {
        return 0;
    }

    @Override
    public void clearTableOrders() {

    }
}
