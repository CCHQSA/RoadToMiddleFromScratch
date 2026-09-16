package Design_Patterns.factory;

import Design_Patterns.strategy.CryptoStrategy;
import Design_Patterns.strategy.PayPalStrategy;
import Design_Patterns.strategy.Payment;

import java.util.HashMap;
import java.util.Map;

public class PaymentFactory {
    private static final Map<PaymentType, Payment> STRATEGIES = new HashMap<>();

    static {
        STRATEGIES.put(PaymentType.PAYPAL, new PayPalStrategy());
        STRATEGIES.put(PaymentType.CRYPTO, new CryptoStrategy());
    }

    public static Payment getPaymentMethod(PaymentType type) {
        Payment strategy = STRATEGIES.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("Unknown payment type: " + type);
        }
        return strategy;
    }

}
