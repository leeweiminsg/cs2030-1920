import java.util.*;

public class BankApp {
    static void transfer(Account source, Account target, double amount) {
        if (source.canWithdraw(amount)) {
            source.withdraw(amount);
            target.deposit(amount);
        }
    }

    static double getAmount() {
        System.out.print("Enter amount to transfer: ");
        return new Scanner(System.in).nextDouble();
    }

    public static void main(String[] args) {
        Account s = getAccount();
        Account t = getAccount();
        double amt = getAmount();
        transfer(s, t, amt);
        System.out.println("Transfer successful");
    }

    // getAccount and other methods omitted
}

abstract class Account {
    private double balance;
    private double min_bal;
    private boolean failedWithdrawal;

    Account(double balance, double min_bal) {
        this.balance = balance;
        this.min_bal = min_bal;
        this.failedWithdrawal = false;
    }

    double getBalance() {
        return balance;
    }

    double getMinBal() {
        return min_bal;
    }

    // or throw exceptions
    boolean canWithdraw(double amount) {
        this.failedWithdrawal = !(amount > 0 && (this.balance - amount) > this.min_bal);

        return !this.failedWithdrawal;
    }

    void withdraw(double amount) {
        this.balance = this.balance - amount;
    }

    void deposit(double amount) {
        this.balance = this.balance + amount;
    }
}

class SavingsAccount extends Account {
    SavingsAccount(double balance, double min_bal) {
        super(balance, 1000);
    }
}