package SOLID.Open_Closed_Principle.Discounts;

public class BlackFridayDiscount implements Discount {
    @Override
    public double calculate(double price) {
        return price - price * 0.5;
    }
}
