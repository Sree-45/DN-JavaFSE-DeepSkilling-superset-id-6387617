package AdapteeClasses;

public class PayPalGateway {
    public void makePayment(double amount, String currency) {
        System.out.println("Processing payment of " + amount + " " + currency + " through PayPal Gateway");
        // Simulate PayPal payment processing
        try {
            Thread.sleep(1000); // Simulate processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public String checkTransactionStatus() {
        return "PayPal Transaction Completed Successfully";
    }
}