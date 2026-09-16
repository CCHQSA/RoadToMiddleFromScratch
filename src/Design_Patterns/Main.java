package Design_Patterns;

import Design_Patterns.builder.User;
import Design_Patterns.factory.PaymentFactory;
import Design_Patterns.factory.PaymentType;
import Design_Patterns.strategy.Payment;

public class Main {
    public static void main(String[] args) {
        PaymentType userChoice = PaymentType.PAYPAL;

        Payment paymentMethod = PaymentFactory.getPaymentMethod(userChoice);

        paymentMethod.pay(100);

        User user = new User.Builder(1)
                .name("Dio")
                .email("dio@gmail.com")
                .phone("+422232324")
                .address("address")
                .age(30)
                .build();

        System.out.println(user);
    }
}
