package Design_Patterns.strategy;

public class PayPalStrategy implements Payment {
    @Override
    public void pay(int amount) {
        System.out.println("Payment for PayPal: " + amount);
    }
}
