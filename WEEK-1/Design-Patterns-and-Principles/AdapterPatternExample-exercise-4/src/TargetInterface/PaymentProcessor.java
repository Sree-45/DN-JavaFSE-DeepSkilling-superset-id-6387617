package TargetInterface;

public interface PaymentProcessor {
    boolean processPayment(double amount, String currency);
    String getPaymentStatus();
}