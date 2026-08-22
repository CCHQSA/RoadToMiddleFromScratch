package SOLID.Open_Closed_Principle.Discounts;

public class Main {
    public static void main(String[] args) {
        Order[] orders = {
                new Order(new RegularDiscount()),
                new Order(new VipDiscount()),
                new Order(new BlackFridayDiscount())
        };

        for (Order order : orders) {
            System.out.println(order.getDiscount().calculate(100));
        }
    }
}
