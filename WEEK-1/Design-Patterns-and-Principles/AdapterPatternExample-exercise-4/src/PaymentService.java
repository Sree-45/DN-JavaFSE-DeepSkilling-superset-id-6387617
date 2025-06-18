

import TargetInterface.PaymentProcessor;

public class PaymentService {
    private PaymentProcessor paymentProcessor;
    
    public PaymentService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }
    
    public void setPaymentProcessor(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }
    
    public void processTransaction(double amount, String currency) {
        System.out.println("\n=== Processing Transaction ===");
        System.out.println("Amount: " + amount + " " + currency);
        
        if (paymentProcessor == null) {
            System.out.println("ERROR: No payment processor configured!");
            System.out.println("Transaction failed - cannot process payment");
            System.out.println("================================\n");
            return;
        }
        
        boolean success = paymentProcessor.processPayment(amount, currency);
        
        if (success) {
            System.out.println("Payment Status: " + paymentProcessor.getPaymentStatus());
            System.out.println("Transaction completed successfully!");
        } else {
            System.out.println("Transaction failed!");
        }
        System.out.println("================================\n");
    }
}