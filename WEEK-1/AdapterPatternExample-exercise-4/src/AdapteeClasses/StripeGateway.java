package AdapteeClasses;
public class StripeGateway {
    public boolean chargeCard(double amount, String currency) {
        System.out.println("Charging card for " + amount + " " + currency + " using Stripe Gateway");
        // Simulate Stripe payment processing
        try {
            Thread.sleep(800); // Simulate processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return true; // Simulate successful charge
    }
    
    public String getChargeStatus() {
        return "Stripe Charge Processed Successfully";
    }
}