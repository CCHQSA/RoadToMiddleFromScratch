package SOLID.Open_Closed_Principle.Discounts;

public class RegularDiscount implements Discount {

    @Override
    public double calculate(double price) {
        return price - price * 0.05;
    }
}
