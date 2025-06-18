package AdapterClasses;

import AdapteeClasses.PayPalGateway;
import TargetInterface.PaymentProcessor;

public class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway payPalGateway;
    
    public PayPalAdapter(PayPalGateway payPalGateway) {
        this.payPalGateway = payPalGateway;
    }
    
    @Override
    public boolean processPayment(double amount, String currency) {
        if (payPalGateway == null) {
            System.out.println("PayPal Gateway is null - cannot process payment");
            return false;
        }
        if (amount <= 0) {
            System.out.println("PayPal: Invalid amount - must be greater than 0");
            return false;
        }
        if (currency == null || currency.trim().isEmpty()) {
            System.out.println("PayPal: Invalid currency - cannot be null or empty");
            return false;
        }
        payPalGateway.makePayment(amount, currency);
        return true;
    }
    
    @Override
    public String getPaymentStatus() {
        if (payPalGateway == null) {
            return "PayPal Gateway is null - cannot get status";
        }
        return payPalGateway.checkTransactionStatus();
    }
}
