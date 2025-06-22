import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LibraryManagementSystem {

    public static Book linearSearchByTitle(List<Book> books, String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public static Book binarySearchByTitle(List<Book> books, String title) {
        int low = 0, high = books.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = books.get(mid).title.compareToIgnoreCase(title);
            if (cmp == 0) {
                return books.get(mid);
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald"));
        books.add(new Book(2, "To Kill a Mockingbird", "Harper Lee"));
        books.add(new Book(3, "1984", "George Orwell"));
        books.add(new Book(4, "Moby Dick", "Herman Melville"));
        books.add(new Book(5, "A Game of Thrones", "George R. R. Martin"));
        books.add(new Book(6, "A Clash of Kings", "George R. R. Martin"));
        books.add(new Book(7, "A Storm of Swords", "George R. R. Martin"));
        books.add(new Book(8, "A Feast for Crows", "George R. R. Martin"));
        books.add(new Book(9, "A Dance with Dragons", "George R. R. Martin"));
        books.add(new Book(10, "Pride and Prejudice", "Jane Austen"));
        books.add(new Book(11, "The Hobbit", "J.R.R. Tolkien"));
        books.add(new Book(12, "The Catcher in the Rye", "J.D. Salinger"));
        books.add(new Book(13, "Brave New World", "Aldous Huxley"));
        books.add(new Book(14, "The Lord of the Rings", "J.R.R. Tolkien"));
        books.add(new Book(15, "The Alchemist", "Paulo Coelho"));
        books.add(new Book(16, "Crime and Punishment", "Fyodor Dostoevsky"));
        books.add(new Book(17, "The Brothers Karamazov", "Fyodor Dostoevsky"));
        books.add(new Book(18, "War and Peace", "Leo Tolstoy"));
        books.add(new Book(19, "Anna Karenina", "Leo Tolstoy"));
        books.add(new Book(20, "Frankenstein", "Mary Shelley"));
        books.add(new Book(21, "Jane Eyre", "Charlotte Brontë"));
        books.add(new Book(22, "Wuthering Heights", "Emily Brontë"));
        books.add(new Book(23, "Dracula", "Bram Stoker"));
        books.add(new Book(24, "The Picture of Dorian Gray", "Oscar Wilde"));
        books.add(new Book(25, "Les Misérables", "Victor Hugo"));

        books.sort(Comparator.comparing(b -> b.title.toLowerCase()));

        String searchTitle = "A Storm of Swords";

        System.out.println("--- Linear Search ---");
        long startLinear = System.nanoTime();
        Book result1 = linearSearchByTitle(books, searchTitle);
        long endLinear = System.nanoTime();
        System.out.println(result1 != null ? result1 : "Book not found");
        System.out.println("Time taken (nanoseconds): " + (endLinear - startLinear));

        System.out.println("\n--- Binary Search ---");
        long startBinary = System.nanoTime();
        Book result2 = binarySearchByTitle(books, searchTitle);
        long endBinary = System.nanoTime();
        System.out.println(result2 != null ? result2 : "Book not found");
        System.out.println("Time taken (nanoseconds): " + (endBinary - startBinary));

        System.out.println("\n--- Additional Statistics ---");
        System.out.println("Total books in library: " + books.size());
        long linearDuration = endLinear - startLinear;
        long binaryDuration = endBinary - startBinary;
        if (binaryDuration != 0) {
            System.out.printf("Binary search was %.2f times faster than linear search.\n",
                (double)linearDuration / binaryDuration);
        } else {
            System.out.println("Binary search time too fast to compare reliably.");
        }
    }
}
