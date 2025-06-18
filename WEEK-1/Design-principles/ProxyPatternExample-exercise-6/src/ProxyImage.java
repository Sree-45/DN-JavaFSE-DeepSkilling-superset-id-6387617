import java.util.HashMap;
import java.util.Map;

class ProxyImage implements Image {
    private String filename;
    private static Map<String, RealImage> imageCache = new HashMap<>(); // Static cache shared across all proxy instances
    
    public ProxyImage(String filename) {
        this.filename = filename;
        System.out.println("ProxyImage created for: " + filename + " (NO loading yet - lazy initialization)");
    }
    
    @Override
    public void display() {
        // LAZY INITIALIZATION: Only load/check cache when display() is actually called
        System.out.println("display() called for: " + filename);
        
        // Check if image is already in cache
        RealImage cachedImage = imageCache.get(filename);
        
        if (cachedImage == null) {
            // LAZY LOADING: First time this image is being displayed
            System.out.println("LAZY INITIALIZATION: Image not in cache - loading from remote server...");
            // Create new RealImage and add to cache
            cachedImage = new RealImage(filename);
            imageCache.put(filename, cachedImage);
            System.out.println("Image loaded and added to cache: " + filename);
        } else {
            System.out.println("CACHE HIT: Image found in cache - no loading required: " + filename);
        }
        
        // Display the image (either newly loaded or from cache)
        cachedImage.display();
    }
    
    // Utility method to check cache status
    public static void printCacheStatus() {
        System.out.println("Cache Status: " + imageCache.size() + " images cached");
        for (String key : imageCache.keySet()) {
            System.out.println("  - " + key);
        }
    }
    
    // Utility method to clear cache if needed
    public static void clearCache() {
        imageCache.clear();
        System.out.println("Cache cleared");
    }
}
