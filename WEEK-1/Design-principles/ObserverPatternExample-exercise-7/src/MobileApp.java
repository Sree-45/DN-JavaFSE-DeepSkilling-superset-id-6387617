public class MobileApp implements Observer {
    private String appName;
    
    public MobileApp(String appName) {
        this.appName = appName;
    }
    
    @Override
    public void update(String stockSymbol, double price) {
        System.out.println("[" + appName + " Mobile App] Push notification: " + 
                         stockSymbol + " price updated to $" + price);
    }
}
