import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logger class implementing the Singleton design pattern
 * Ensures only one instance of the logger exists throughout the application
 */
public class Logger {
    // Private static instance of the Logger class (lazy initialization)
    private static Logger instance;
    
    // Date formatter for log timestamps
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    // Private constructor to prevent instantiation from outside
    private Logger() {
        // Reflection protection: Check if instance already exists
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to get the singleton instance");
        }
        
        // Initialize any resources if needed
        System.out.println("Logger instance created at: " + LocalDateTime.now().format(formatter));
    }
    
    /**
     * Public static method to get the single instance of Logger
     * This is the KEY METHOD that implements the Singleton pattern!
     * Uses lazy initialization with thread safety
     * @return the single Logger instance
     */
    public static synchronized Logger getInstance() {
        // Check if instance is null (first time being called)
        if (instance == null) {
            // Create the instance only once
            instance = new Logger();
        }
        // Always return the same instance
        return instance;
    }
    
    /**
     * Method to log info messages
     * @param message the message to log
     */
    public void logInfo(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println("[INFO] " + timestamp + " - " + message);
    }
    
    /**
     * Method to log error messages
     * @param message the error message to log
     */
    public void logError(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.err.println("[ERROR] " + timestamp + " - " + message);
    }
    
    /**
     * Method to log warning messages
     * @param message the warning message to log
     */
    public void logWarning(String message) {
        System.out.println("[WARNING] " + LocalDateTime.now().format(formatter) + " - " + message);
    }
    
    /**
     * Method to get logger information
     * @return string representation of the logger instance
     * 
     * A hashcode is a unique integer value that represents the memory address 
     * of an object. It's used for:
     * - Quick object comparison and identification
     * - Hash table lookups and collections
     * - Debugging to verify object identity
     * 
     * In a singleton pattern, all instances should have the same hashcode 
     * since they refer to the same object in memory.
     */
    public String getLoggerInfo() {
        return "Logger instance: " + this.hashCode() + " at " + LocalDateTime.now().format(formatter);
    }
}