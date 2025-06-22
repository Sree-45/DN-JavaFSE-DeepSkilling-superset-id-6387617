public class OrderSorting {

    // Bubble Sort
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].totalPrice > orders[j + 1].totalPrice) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    // Quick Sort
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].totalPrice;
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (orders[j].totalPrice < pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }

    // Helper to print orders
    public static void printOrders(Order[] orders) {
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    // Generate sample test orders
    public static Order[] generateTestOrders() {
        return new Order[] {
            new Order(1, "Sreeshanth", 450.0),
            new Order(2, "Ananya", 120.0),
            new Order(3, "Harsha", 305.75),
            new Order(4, "Ravi", 250.5),
            new Order(5, "Pooja", 199.99),
            new Order(6, "Krishna", 720.0),
            new Order(7, "Sreeshanth", 310.5),
            new Order(8, "Meghana", 180.0),
            new Order(9, "Nikhil", 330.25),
            new Order(10, "Karthik", 99.5),
            new Order(11, "Sreeshanth", 510.0),
            new Order(12, "Raj", 600.0),
            new Order(13, "Ritika", 250.0),
            new Order(14, "Akhil", 410.0),
            new Order(15, "Roshni", 130.0)
        };
    }

    // Main method to test sorting
    public static void main(String[] args) {
        Order[] originalOrders = generateTestOrders();

        System.out.println("Original Orders:");
        printOrders(originalOrders);

        // Bubble Sort
        Order[] bubbleSortedOrders = originalOrders.clone();
        long startBubble = System.nanoTime();
        bubbleSort(bubbleSortedOrders);
        long endBubble = System.nanoTime();
        System.out.println("\nOrders after Bubble Sort:");
        printOrders(bubbleSortedOrders);
        System.out.println("Bubble Sort Time: " + (endBubble - startBubble) / 1_000_000.0 + " ms");

        // Quick Sort
        Order[] quickSortedOrders = originalOrders.clone();
        long startQuick = System.nanoTime();
        quickSort(quickSortedOrders, 0, quickSortedOrders.length - 1);
        long endQuick = System.nanoTime();
        System.out.println("\nOrders after Quick Sort:");
        printOrders(quickSortedOrders);
        System.out.println("Quick Sort Time: " + (endQuick - startQuick) / 1_000_000.0 + " ms");
    }
}