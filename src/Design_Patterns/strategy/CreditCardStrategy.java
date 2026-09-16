package Design_Patterns.strategy;

public class CreditCardStrategy implements Payment{
    @Override
    public void pay(int amount) {
        System.out.println("Credit Card Payment: "  + amount);
    }
}
