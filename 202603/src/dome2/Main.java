package dome2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Book b1 = new Book(1, "Java入门", 100);
        Book b2 = new Book(2, "MySQL", 69.9);
        Book b3 = new Book(3,"SpringBoot",70);

        List<Book> books = new ArrayList<>();
        books.add(b1);
        books.add(b2);
        books.add(b3);


        for (int i = 0; i < books.size() ; i++) {
            System.out.println(b1.getTitle() + "," + b2.getTitle() + "," + b3.getTitle() );
        }
    }
}
