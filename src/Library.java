import entity.Books;
import entity.Users;
import java.util.*;

import utiles.Genre;
import utiles.bookStatus;
import utiles.subscriptionType;

public class Library {
    public static List<Books> books = new ArrayList<>();
    public static List<String> borrowedBooks = new ArrayList<>();
    public static Map<String, Users> users = new HashMap<>();

    public static void main(String[] args) {
        Book bookClass = new Book();
        User userClass = new User();
        try {
            Scanner sc = new Scanner(System.in);
            boolean exit = false;

            users.put("ATS112", new Users("Jeeva", "jeeva@gmail.com", subscriptionType.FREE, 2));
            users.put("ATS113", new Users("Kabil", "Kabil@gmail.com", subscriptionType.PRO, 3));
            users.put("ATS114", new Users("Pranav", "pranav@gmail.com", subscriptionType.FREE, 2));

            books.add(new Books(1, "Rich Dad Poor Dad", "Robert Kiyosaki", Genre.SCI_FI, bookStatus.AVAILABLE));
            books.add(new Books(2, "Money", "Yuval Noah Harari", Genre.NOVEL, bookStatus.AVAILABLE));

            while (!exit) {
                System.out.println("========================== Library Management ===========================");
                System.out.println("1: Add Books");
                System.out.println("2: Borrow Books");
                System.out.println("3: Return Books");
                System.out.println("4: View All Books");
                System.out.println("5: User Registration");
                System.out.println("6: View All Users");
                System.out.println("7: Exit");
                System.out.println("=========================================================================");

                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        bookClass.addBook(sc);
                        break;
                    case 2:
                        bookClass.borrowBook(sc);
                        break;
                    case 3:
                        bookClass.returnBook(sc);
                        break;
                    case 4:
                        bookClass.viewBooks(sc);
                        break;
                    case 5:
                        userClass.registerUser(sc);
                        break;
                    case 6:
                        userClass.viewAllUsers();
                        break;
                    case 7:
                        exit = true;
                        System.out.println("Exiting the Library Management System. Goodbye!");
                        break;
                    default:
                        System.out.println("Please enter a valid option!");
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Something went wrong!");
        }
    }
}
