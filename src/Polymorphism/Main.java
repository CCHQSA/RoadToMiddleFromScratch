package Polymorphism;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Payment> payments = new ArrayList<>();
        payments.add(new CreditCardPayment());
        payments.add(new PayPalPayment());
        payments.add(new CryptoPayment());

        for (Payment payment : payments) {
            payment.pay(150.0);
        }
    }
}
