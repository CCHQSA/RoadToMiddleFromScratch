package Exceptions;

public class BankAccount {
    private  double balance;
    public BankAccount(int balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new InvalidAmountException("Invalid amount");
        }
        balance += amount;
        System.out.println("Deposited " + amount);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient Funds");
        } else if (amount <= 0) {
            throw new InvalidAmountException("Invalid Amount");
        }
        balance -= amount;
        System.out.println("Withdrawn " + amount);
    }
}
