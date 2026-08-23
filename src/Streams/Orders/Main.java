package Streams.Orders;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    static void main() {
        List<OrderItem> itemsForOrder1 = Arrays.asList(
                new OrderItem("Laptop", 1, 1000.0),
                new OrderItem("Mouse", 2, 25.0)
        );

        List<OrderItem> itemsForOrder2 = Arrays.asList(
                new OrderItem("Phone", 1, 500.0),
                new OrderItem("Mouse", 1, 25.0)
        );

        List<OrderItem> itemsForOrder3 = Arrays.asList(
                new OrderItem("Keyboard", 1, 75.0)
        );

        List<OrderItem> itemsForOrder4 = Arrays.asList(
                new OrderItem("Laptop", 2, 1000.0)
        );

        List<Order> orders = Arrays.asList(
                new Order(1, "Alice", itemsForOrder1),
                new Order(2, "Bob", itemsForOrder2),
                new Order(3, "Alice", itemsForOrder3),
                new Order(4, "Charlie", itemsForOrder4)
        );

        double totalRevenue = orders.stream()
                .flatMap(order -> order.getOrderItems().stream())
                .mapToDouble(OrderItem::getTotalPrice)
                .sum();

        Map<String, Double> collect = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomer,
                        Collectors.summingDouble
                                (o -> o.getOrderItems().stream()
                                        .mapToDouble(OrderItem::getTotalPrice).sum())));


        Optional<Order> mostExpensiveOrder = orders.stream()
                .max(Comparator.comparingDouble(o -> o.getOrderItems().stream()
                        .mapToDouble(OrderItem::getTotalPrice)
                        .sum()));


        Map<String, Integer> mostPopularProducts = orders.stream()
                .flatMap(o -> o.getOrderItems().stream())
                .collect(Collectors.groupingBy(
                        OrderItem::getProduct,
                        Collectors.summingInt(OrderItem::getQuantity)
                ));

        Optional<String> mostPopular = mostPopularProducts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);


        List<String> top3Customers = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomer,
                        Collectors.summingDouble(order -> order.getOrderItems().stream()
                                .mapToDouble(OrderItem::getTotalPrice)
                                .sum())
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();


    }
}
