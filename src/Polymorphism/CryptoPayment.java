package Polymorphism;

public class CryptoPayment extends Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Crypto Payment - " + amount);
    }
}
