
import java.util.HashMap;
import java.util.Map;


/**
 * Class for managing the inventory using HashMap.
 */
public class InventoryManager {
    private Map<String, Product> inventory;

    public InventoryManager() {
        inventory = new HashMap<>();
    }

    /**
     * Adds a new product to the inventory.
     */
    public void addProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            System.out.println("Product already exists. Use updateProduct to modify.");
        } else {
            inventory.put(product.getProductId(), product);
            System.out.println("Product added successfully.");
        }
    }

    /**
     * Updates an existing product in the inventory.
     */
    public void updateProduct(String productId, String name, int quantity, double price) {
        if (inventory.containsKey(productId)) {
            Product product = inventory.get(productId);
            product.setProductName(name);
            product.setQuantity(quantity);
            product.setPrice(price);
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    /**
     * Deletes a product from the inventory.
     */
    public void deleteProduct(String productId) {
        if (inventory.containsKey(productId)) {
            inventory.remove(productId);
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    /**
     * Displays a single product by product ID.
     */
    public void viewProduct(String productId) {
        if (inventory.containsKey(productId)) {
            System.out.println(inventory.get(productId));
        } else {
            System.out.println("Product not found.");
        }
    }

    /**
     * Displays the entire inventory.
     */
    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            for (Product product : inventory.values()) {
                System.out.println(product);
            }
        }
    }

    /**
     * Calculates total value of inventory.
     */
    public void calculateTotalValue() {
        double total = 0;
        for (Product p : inventory.values()) {
            total += p.getPrice() * p.getQuantity();
        }
        System.out.println("Total Inventory Value: $" + total);
    }

    /**
     * Main method to test all functionalities explicitly.
     */
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();

        // Add products
        Product p1 = new Product("P101", "Keyboard", 10, 25.5);
        Product p2 = new Product("P102", "Mouse", 20, 15.0);
        Product p3 = new Product("P103", "Monitor", 5, 120.0);
        Product p4 = new Product("P104", "Laptop", 3, 750.0);
        Product p5 = new Product("P105", "Headphones", 15, 45.0);
        Product p6 = new Product("P106", "Webcam", 8, 60.0);
        Product p7 = new Product("P107", "USB-C Cable", 30, 10.0);

        manager.addProduct(p1);
        manager.addProduct(p2);
        manager.addProduct(p3);
        manager.addProduct(p4);
        manager.addProduct(p5);
        manager.addProduct(p6);
        manager.addProduct(p7);

        // Try to add an existing product
        manager.addProduct(p1);

        // Display all products
        System.out.println("\nDisplaying All Products:");
        manager.displayInventory();

        // View a specific product
        System.out.println("\nViewing Product with ID P102:");
        manager.viewProduct("P102");

        // Update a product
        System.out.println("\nUpdating Product P102:");
        manager.updateProduct("P102", "Wireless Mouse", 25, 18.5);
        manager.viewProduct("P102");

        // Delete a product
        System.out.println("\nDeleting Product P101:");
        manager.deleteProduct("P101");
        manager.viewProduct("P101");

        // Display final inventory
        System.out.println("\nFinal Inventory:");
        manager.displayInventory();

        // Calculate total value
        System.out.println("\nCalculating Total Inventory Value:");
        manager.calculateTotalValue();
    }
}
