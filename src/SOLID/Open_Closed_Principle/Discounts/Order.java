package SOLID.Open_Closed_Principle.Discounts;

public class Order {
    private Discount discount;
    public Order(Discount discount) {
        this.discount = discount;
    }
    public Discount getDiscount() {
        return discount;
    }
}
