import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class ListToJSONArray {
    public static void main(String[] args) {
        // Create a list of Book objects
        List<Book> books = new ArrayList<>();
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", 1960));
        books.add(new Book("1984", "George Orwell", 1949));
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925));

        // Convert the list of books to a JSON array string
        Gson gson = new Gson();
        String jsonArray = gson.toJson(books);

        // Print the JSON array
        System.out.println(jsonArray);
    }
}

// Book class
class Book {
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
}
