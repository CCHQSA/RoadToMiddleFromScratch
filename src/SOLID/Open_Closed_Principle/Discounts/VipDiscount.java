package SOLID.Open_Closed_Principle.Discounts;

public class VipDiscount implements Discount {
    @Override
    public double calculate(double price) {
        return price - price * 0.2;
    }
}
