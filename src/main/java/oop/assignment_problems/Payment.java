package oop.assignment_problems;
class CardPayment extends Payment {
    public void payWithProcessingFee(double amount) {
        double total = amount + (amount * 0.02);
        System.out.println("Card payment: " + total);
    }
}
public class Payment {
    public void pay(double amount) {
        System.out.println("Payment: " + amount);
    }
    public double processTransaction(Payment payment, double amount) {
        if (payment instanceof CardPayment) {
            double total = amount + (amount * 0.02);
            ((CardPayment) payment).payWithProcessingFee(amount);
            return total;
        } else {
            payment.pay(amount);
            return amount;
        }
    }
    public static void main(String[] args) {
        Payment processor = new Payment();
        Payment[] payments = {new CardPayment(),new Payment(),new CardPayment(),new Payment(),new CardPayment()};
        double[] amounts = {100,50,200,75,120};
        double totalCollected = 0;
        for (int i = 0; i < payments.length; i++) {
            totalCollected += processor.processTransaction(payments[i],amounts[i]);
        }
        System.out.println("Total collected: " + totalCollected);
    }
}