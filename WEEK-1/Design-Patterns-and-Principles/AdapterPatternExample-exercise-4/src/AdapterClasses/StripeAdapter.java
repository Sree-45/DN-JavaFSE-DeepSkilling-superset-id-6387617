package AdapterClasses;

import AdapteeClasses.StripeGateway;
import TargetInterface.PaymentProcessor;

public class StripeAdapter implements PaymentProcessor {
    private StripeGateway stripeGateway;
    
    public StripeAdapter(StripeGateway stripeGateway) {
        this.stripeGateway = stripeGateway;
    }
    
    @Override
    public boolean processPayment(double amount, String currency) {
        if (stripeGateway == null) {
            System.out.println("Stripe Gateway is null - cannot process payment");
            return false;
        }
        if (amount <= 0) {
            System.out.println("Stripe: Invalid amount - must be greater than 0");
            return false;
        }
        if (currency == null || currency.trim().isEmpty()) {
            System.out.println("Stripe: Invalid currency - cannot be null or empty");
            return false;
        }
        return stripeGateway.chargeCard(amount, currency);
    }
    
    @Override
    public String getPaymentStatus() {
        if (stripeGateway == null) {
            return "Stripe Gateway is null - cannot get status";
        }
        return stripeGateway.getChargeStatus();
    }
}
