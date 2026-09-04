package oop.class_problems;
class FeeAccount {
}
class HostelFeeAccount extends FeeAccount {
}
public class PaymentProcessor {
    static int hostelCount = 0;
    static int dayScholarCount = 0;
    void processPayment(FeeAccount account, double amount) {
        if (account instanceof HostelFeeAccount) {
            System.out.println("Paid in two installments (hostel account)");
            hostelCount++;
        } else {
            System.out.println("Paid in one go (day-scholar account)");
            dayScholarCount++;
        }
    }
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        FeeAccount[] accounts = {new HostelFeeAccount(),new HostelFeeAccount(),new FeeAccount(),new FeeAccount()};
        double amount = 60000;
        for (FeeAccount account : accounts) {
            processor.processPayment(account, amount);
        }
        System.out.println(
            "Hostel accounts processed: " + hostelCount+ " | Day-scholar accounts processed: " + dayScholarCount);
    }
}