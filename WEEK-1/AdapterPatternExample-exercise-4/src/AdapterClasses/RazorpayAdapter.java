package AdapterClasses;

import AdapteeClasses.RazorpayGateway;
import TargetInterface.PaymentProcessor;

public class RazorpayAdapter implements PaymentProcessor {
    private RazorpayGateway razorpayGateway;
    
    public RazorpayAdapter(RazorpayGateway razorpayGateway) {
        this.razorpayGateway = razorpayGateway;
    }
    
    @Override
    public boolean processPayment(double amount, String currency) {
        if (razorpayGateway == null) {
            System.out.println("Razorpay Gateway is null - cannot process payment");
            return false;
        }
        if (amount <= 0) {
            System.out.println("Razorpay: Invalid amount - must be greater than 0");
            return false;
        }
        if (currency == null || currency.trim().isEmpty()) {
            System.out.println("Razorpay: Invalid currency - cannot be null or empty");
            return false;
        }
        razorpayGateway.initiatePayment(amount, currency);
        return true;
    }
    
    @Override
    public String getPaymentStatus() {
        if (razorpayGateway == null) {
            return "Razorpay Gateway is null - cannot get status";
        }
        return razorpayGateway.fetchPaymentStatus();
    }
}