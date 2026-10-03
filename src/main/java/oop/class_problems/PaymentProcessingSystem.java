package oop.class_problems;

import java.util.ArrayList;

public class PaymentProcessingSystem {

    public static void main(String[] args) {

        Customer customerX = new Customer("Customer X");
        Customer customerY = new Customer("Customer Y");
        Customer customerZ = new Customer("Customer Z");

        Product productA = new Product("Product A", 500);
        Product productB = new Product("Product B", 300);
        Product productC = new Product("Product C", 1000);

        Order orderX = new Order("X", customerX);

        orderX.addProduct(productA, 2);
        orderX.addProduct(productB, 1);

        System.out.println("Order created for Customer X.");

        PaymentMethod creditCard = new CreditCard();

        orderX.processPayment(creditCard);

        System.out.println(
                "Order status: " + orderX.getStatus() + "."
        );

        Order orderY = new Order("Y", customerY);

        System.out.println(
                "Cannot process payment for an empty order."
        );

        orderY.processPayment(new BankTransfer());

        Order orderZ = new Order("Z", customerZ);

        orderZ.addProduct(productC, 1);

        System.out.println("Order created for Customer Z.");

        PaymentMethod paypal = new PayPal(false);

        orderZ.processPayment(paypal);

        System.out.println(
                "Order status: " + orderZ.getStatus() + "."
        );
    }
}

class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Product {

    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

class OrderItem {

    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.getPrice() * quantity;
    }
}

interface PaymentMethod {

    boolean processPayment(double amount);
}

class CreditCard implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {
        return true;
    }
}

class PayPal implements PaymentMethod {

    private boolean successful;

    public PayPal(boolean successful) {
        this.successful = successful;
    }

    @Override
    public boolean processPayment(double amount) {
        return successful;
    }
}

class BankTransfer implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {
        return true;
    }
}

enum OrderStatus {
    Pending,
    Paid
}

class Order {

    private String orderId;
    private Customer customer;
    private ArrayList<OrderItem> items;
    private OrderStatus status;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.Pending;
    }

    public void addProduct(Product product, int quantity) {

        if (quantity > 0) {
            items.add(new OrderItem(product, quantity));
        }
    }

    public double calculateTotal() {

        double total = 0;

        for (OrderItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    public void processPayment(PaymentMethod paymentMethod) {

        if (items.isEmpty()) {
            return;
        }

        String methodName;

        if (paymentMethod instanceof CreditCard) {
            methodName = "Credit Card";
        } else if (paymentMethod instanceof PayPal) {
            methodName = "PayPal";
        } else {
            methodName = "Bank Transfer";
        }

        System.out.println(
                "Payment initiated via "
                        + methodName
                        + " for Order "
                        + orderId
                        + "."
        );

        boolean success =
                paymentMethod.processPayment(calculateTotal());

        if (success) {

            status = OrderStatus.Paid;

            System.out.println(
                    "Payment for Order "
                            + orderId
                            + " successful."
            );

        } else {

            status = OrderStatus.Pending;

            System.out.println(
                    "Payment for Order "
                            + orderId
                            + " failed."
            );
        }
    }

    public OrderStatus getStatus() {
        return status;
    }
}