
class Product implements Comparable<Product> {
    private int productId;
    private String productName;
    private String category;
    
    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }
    
    // Getters
    public int getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }
    
    // For binary search - compare by productId
    @Override
    public int compareTo(Product other) {
        return Integer.compare(this.productId, other.productId);
    }
    
    @Override
    public String toString() {
        return String.format("Product{id=%d, name='%s', category='%s'}", 
                           productId, productName, category);
    }
}

class Main {
    public static void main(String[] args) {
        System.out.println("=== E-Commerce Platform Search Function ===\n");
        
        // Create platform and add products
        EcommerceSearchPlatform platform = new EcommerceSearchPlatform(10);
        
        // Add sample products
        platform.addProduct(new Product(101, "iPhone 15", "Electronics"));
        platform.addProduct(new Product(205, "Samsung Galaxy", "Electronics"));
        platform.addProduct(new Product(112, "Nike Shoes", "Footwear"));
        platform.addProduct(new Product(445, "Adidas Shoes", "Footwear"));
        platform.addProduct(new Product(167, "Jeans", "Clothing"));
        platform.addProduct(new Product(298, "T-Shirt", "Clothing"));
        platform.addProduct(new Product(523, "Headphones", "Electronics"));
        
        // Display all products
        platform.displayAllProducts();
        
        // Test search algorithms
        System.out.println("=== Performance Comparison ===");
        platform.compareSearchAlgorithms(112);  // Found case
        platform.compareSearchAlgorithms(999);  // Not found case
        platform.compareSearchAlgorithms(523);  // Last item case
    }
}