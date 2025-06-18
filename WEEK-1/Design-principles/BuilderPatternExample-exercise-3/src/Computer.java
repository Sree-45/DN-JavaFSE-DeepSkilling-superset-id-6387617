
public class Computer {
    private final String CPU;
    private final String RAM;

    private final String storage;
    private final String graphicsCard;
    private final String motherboard;
    private final String powerSupply;
    private final String coolingSystem;
    private final boolean hasWiFi;
    private final boolean hasBluetooth;
    

    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
        this.graphicsCard = builder.graphicsCard;
        this.motherboard = builder.motherboard;
        this.powerSupply = builder.powerSupply;
        this.coolingSystem = builder.coolingSystem;
        this.hasWiFi = builder.hasWiFi;
        this.hasBluetooth = builder.hasBluetooth;
    }
    
    
    public String getCPU() {
        return CPU;
    }
    
    public String getRAM() {
        return RAM;
    }
    
    public String getStorage() {
        return storage;
    }
    
    public String getGraphicsCard() {
        return graphicsCard;
    }
    
    public String getMotherboard() {
        return motherboard;
    }
    
    public String getPowerSupply() {
        return powerSupply;
    }
    
    public String getCoolingSystem() {
        return coolingSystem;
    }
    
    public boolean hasWiFi() {
        return hasWiFi;
    }
    
    public boolean hasBluetooth() {
        return hasBluetooth;
    }
    
    @Override
    public String toString() {
        return "Computer Configuration:\n" +
                "CPU: " + CPU + "\n" +
                "RAM: " + RAM + "\n" +
                "Storage: " + (storage != null ? storage : "Not specified") + "\n" +
                "Graphics Card: " + (graphicsCard != null ? graphicsCard : "Integrated") + "\n" +
                "Motherboard: " + (motherboard != null ? motherboard : "Standard") + "\n" +
                "Power Supply: " + (powerSupply != null ? powerSupply : "Standard") + "\n" +
                "Cooling System: " + (coolingSystem != null ? coolingSystem : "Stock") + "\n" +
                "WiFi: " + (hasWiFi ? "Yes" : "No") + "\n" +
                "Bluetooth: " + (hasBluetooth ? "Yes" : "No") + "\n";
    }
    
    public static class Builder {
        private final String CPU;
        private final String RAM;
        
        private String storage;
        private String graphicsCard;
        private String motherboard;
        private String powerSupply;
        private String coolingSystem;
        private boolean hasWiFi = false;
        private boolean hasBluetooth = false;
        
        public Builder(String CPU, String RAM) {
            this.CPU = CPU;
            this.RAM = RAM;
        }
        
        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }
        
        public Builder setGraphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }
        
        public Builder setMotherboard(String motherboard) {
            this.motherboard = motherboard;
            return this;
        }
        
        public Builder setPowerSupply(String powerSupply) {
            this.powerSupply = powerSupply;
            return this;
        }
        
        public Builder setCoolingSystem(String coolingSystem) {
            this.coolingSystem = coolingSystem;
            return this;
        }
        
        public Builder setWiFi(boolean hasWiFi) {
            this.hasWiFi = hasWiFi;
            return this;
        }
        
        public Builder setBluetooth(boolean hasBluetooth) {
            this.hasBluetooth = hasBluetooth;
            return this;
        }
        
        public Computer build() {
            return new Computer(this);
        }
    }
}