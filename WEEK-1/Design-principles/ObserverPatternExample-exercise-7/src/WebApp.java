public class WebApp implements Observer {
    private String appName;
    
    public WebApp(String appName) {
        this.appName = appName;
    }
    
    @Override
    public void update(String stockSymbol, double price) {
        System.out.println("[" + appName + " Web App] Dashboard updated: " + 
                         stockSymbol + " = $" + price);
    }
}
