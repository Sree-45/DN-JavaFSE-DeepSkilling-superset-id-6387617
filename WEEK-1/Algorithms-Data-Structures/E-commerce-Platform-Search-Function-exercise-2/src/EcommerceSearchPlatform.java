import java.util.Arrays;

public class EcommerceSearchPlatform {
    private Product[] products;        // For linear search
    private Product[] sortedProducts;  // For binary search
    private int size;
    
    public EcommerceSearchPlatform(int capacity) {
        this.products = new Product[capacity];
        this.sortedProducts = new Product[capacity];
        this.size = 0;
    }
    
    // Add product to both arrays
    public void addProduct(Product product) {
        if (size < products.length) {
            products[size] = product;
            sortedProducts[size] = product;
            size++;
            
            // Keep sorted array sorted by productId
            Arrays.sort(sortedProducts, 0, size);
        }
    }
    
    // Linear Search Implementation
    public Product linearSearch(int productId) {
        for (int i = 0; i < size; i++) {
            if (products[i].getProductId() == productId) {
                return products[i];
            }
        }
        return null; // Not found
    }
    
    // Binary Search Implementation
    public Product binarySearch(int productId) {
        int left = 0;
        int right = size - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midId = sortedProducts[mid].getProductId();
            
            if (midId == productId) {
                return sortedProducts[mid];
            }
            
            if (midId < productId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null; // Not found
    }
    
    // Performance comparison method
    public void compareSearchAlgorithms(int searchId) {
        System.out.println("Searching for Product ID: " + searchId);
        
        // Linear Search
        long startTime = System.nanoTime();
        Product linearResult = linearSearch(searchId);
        long linearTime = System.nanoTime() - startTime;
        
        // Binary Search
        startTime = System.nanoTime();
        Product binaryResult = binarySearch(searchId);
        long binaryTime = System.nanoTime() - startTime;
        
        // Results
        System.out.println("Linear Search: " + (linearResult != null ? "Found" : "Not Found") 
                         + " | Time: " + linearTime + " ns");
        System.out.println("Binary Search: " + (binaryResult != null ? "Found" : "Not Found") 
                         + " | Time: " + binaryTime + " ns");
        
        if (linearTime > 0 && binaryTime > 0) {
            System.out.printf("Binary search was %.2fx faster\n", 
                             (double) linearTime / binaryTime);
        }
        System.out.println();
    }
    
    public void displayAllProducts() {
        System.out.println("All Products:");
        for (int i = 0; i < size; i++) {
            System.out.println(products[i]);
        }
        System.out.println();
    }
}
