import java.util.ArrayList;

public class ArrayListOfBooks {

    public static void main(String[] args) {

        ArrayList<Book> alb = new ArrayList<>();

        Book b1 = new Book("Icon", 499.0, "FIC000111", "Fiction");
        Book b2 = new Book("21 lessons for the 21st century", 655);
        Book b3 = new Book("Hobbit", 1499.99);
        alb.add(b1);
        alb.add(b2);

        System.out.println("Book details from the ArrayList:");
        for (Book b : alb) {
            System.out.println("Title:" + b.title);
            System.out.println("Price:" + b.price);
            System.out.println("ISBN:" + b.ISBN);
            System.out.println("Genre:" + b.genre);
            System.out.println("Author: " + b.author);
        }

        System.out.println("Is array list empty? " + alb.isEmpty());
        System.out.println("Size of array list: " + alb.size());

        // Adding more books
        Book b3 = new Book("Hobbit", 1499.99);
        Book b4 = new Book("Outlive", 370, "LIF34567", "Non-fiction");

        alb.add(b3);
        alb.add(b4);

        System.out.println("Book details from the ArrayList after adding b3 and b4:");

        for (Book b : alb) {
            System.out.println("Title:" + b.title);
            System.out.println("Price:" + b.price);
            System.out.println("ISBN:" + b.ISBN);
            System.out.println("Genre:" + b.genre);
            System.out.println("Author: " + b.author);
        }
    }
}