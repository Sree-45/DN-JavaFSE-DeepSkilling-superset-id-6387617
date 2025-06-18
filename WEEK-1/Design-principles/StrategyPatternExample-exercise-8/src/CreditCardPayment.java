public class CreditCardPayment implements PaymentStrategy{
private String cardNumber;
    private String cardHolderName;
    @SuppressWarnings("unused")
    private String expiryDate;
    @SuppressWarnings("unused")
    private String cvv;
    
    public CreditCardPayment(String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Processing Credit Card Payment:");
        System.out.println("Card Number: **** **** **** " + cardNumber.substring(cardNumber.length() - 4));
        System.out.println("Cardholder: " + cardHolderName);
        System.out.println("Amount: $" + amount);
        System.out.println("Credit Card payment of $" + amount + " completed successfully!");
        System.out.println("Transaction ID: CC" + System.currentTimeMillis());
        System.out.println("----------------------------------------");
    }
}

