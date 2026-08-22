package Polymorphism;

public class PayPalPayment extends Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paypal Payment - " + amount);
    }
}
