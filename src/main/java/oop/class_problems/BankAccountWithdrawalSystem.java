package oop.class_problems;

import java.util.*;

public class BankAccountWithdrawalSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, BankAccount> accounts = new HashMap<>();

        while (sc.hasNext()) {
            String operation = sc.next();

            if (operation.equals("Savings")) {
                String id = sc.next();
                double balance = sc.nextDouble();
                accounts.put(id, new SavingsAccount(id, balance));

            } else if (operation.equals("Current")) {
                String id = sc.next();
                double balance = sc.nextDouble();
                accounts.put(id, new CurrentAccount(id, balance));

            } else if (operation.equals("WITHDRAW")) {
                String id = sc.next();
                double amount = sc.nextDouble();

                BankAccount account = accounts.get(id);

                if (account == null) {
                    System.out.println("Account not found");
                } else {
                    account.withdraw(amount);
                }
            }
        }

        sc.close();
    }
}

abstract class BankAccount {
    protected String id;
    protected double balance;

    public BankAccount(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public abstract void withdraw(double amount);
}

class SavingsAccount extends BankAccount {
    public SavingsAccount(String id, double balance) {
        super(id, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount < 1000) {
            System.out.println(id + " rejected: minimum balance 1000");
        } else {
            balance -= amount;
            System.out.println(id + " balance " + format(balance));
        }
    }

    private String format(double value) {
        return value == (long) value
                ? String.valueOf((long) value)
                : String.valueOf(value);
    }
}

class CurrentAccount extends BankAccount {
    public CurrentAccount(String id, double balance) {
        super(id, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount < -5000) {
            System.out.println(id + " rejected: overdraft limit 5000");
        } else {
            balance -= amount;
            System.out.println(id + " balance " + format(balance));
        }
    }

    private String format(double value) {
        return value == (long) value
                ? String.valueOf((long) value)
                : String.valueOf(value);
    }
}