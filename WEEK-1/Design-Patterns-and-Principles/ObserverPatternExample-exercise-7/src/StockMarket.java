import java.util.ArrayList;
import java.util.List;

public class StockMarket implements Stock {
    private List<Observer> observers;
    private String stockSymbol;
    private double stockPrice;
    
    public StockMarket() {
        this.observers = new ArrayList<>();
    }
    
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
        System.out.println("Observer registered successfully.");
    }
    
    @Override
    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
        System.out.println("Observer deregistered successfully.");
    }
    
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockSymbol, stockPrice);
        }
    }
    
    public void setStockPrice(String stockSymbol, double stockPrice) {
        this.stockSymbol = stockSymbol;
        this.stockPrice = stockPrice;
        System.out.println("\nStock price updated: " + stockSymbol + " = $" + stockPrice);
        notifyObservers();
    }
    
    public double getStockPrice() {
        return stockPrice;
    }
    
    public String getStockSymbol() {
        return stockSymbol;
    }

}
