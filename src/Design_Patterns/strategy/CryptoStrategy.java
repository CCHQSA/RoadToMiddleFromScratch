package Design_Patterns.strategy;

public class CryptoStrategy implements Payment {
    @Override
    public void pay(int amount) {
        System.out.println("Payment for Crypto: " + amount);
    }
}
