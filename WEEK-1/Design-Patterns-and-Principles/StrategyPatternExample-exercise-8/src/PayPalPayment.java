public class PayPalPayment implements PaymentStrategy {
private String email;
    @SuppressWarnings("unused")
    private String password;
    
    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Processing PayPal Payment:");
        System.out.println("PayPal Email: " + email);
        System.out.println("Amount: $" + amount);
        System.out.println("PayPal payment of $" + amount + " completed successfully!");
        System.out.println("Transaction ID: PP" + System.currentTimeMillis());
        System.out.println("----------------------------------------");
    }
}

