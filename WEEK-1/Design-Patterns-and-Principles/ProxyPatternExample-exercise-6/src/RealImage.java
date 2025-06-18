class RealImage implements Image {
    private String filename;
    
    public RealImage(String filename) {
        this.filename = filename;
        loadImageFromRemoteServer();
    }
    
    private void loadImageFromRemoteServer() {
        System.out.println("Loading image from remote server: " + filename);
        // Simulate time-consuming operation
        try {
            Thread.sleep(2000); // Simulate 2 seconds loading time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Image loaded successfully: " + filename);
    }
    
    @Override
    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}