package Streams.Orders;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private long id;
    private String Customer;
    private List<OrderItem> orderItems;

    public Order(long id, String Customer, List<OrderItem> orderItems) {
        this.id = id;
        this.Customer = Customer;
        this.orderItems = orderItems;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String customer) {
        Customer = customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }
}
