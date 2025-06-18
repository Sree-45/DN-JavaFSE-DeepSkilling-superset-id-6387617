package AdapteeClasses;

public class RazorpayGateway {
    public void initiatePayment(double amount, String currency) {
        System.out.println("Initiating payment of " + amount + " " + currency + " via Razorpay Gateway");
        // Simulate Razorpay payment processing
        try {
            Thread.sleep(1200); // Simulate processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public String fetchPaymentStatus() {
        return "Razorpay Payment Initiated and Completed";
    }
}