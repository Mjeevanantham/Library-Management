import entity.Books;
import entity.Users;

import java.util.*;

import utiles.AppConstants;
import utiles.AppConstants.*;
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

            books.add(new Books("Rich Dad Poor Dad", "Robert", Genre.SCI_FI, bookStatus.AVAILABLE));
            books.add(new Books("Basketball", "Lebron james", Genre.NOVEL, bookStatus.AVAILABLE));
            books.add(new Books("Football", "Messi", Genre.NOVEL, bookStatus.AVAILABLE));

            while (!exit) {
                System.out.println(AppConstants.MENU_HEADER);
                System.out.println(AppConstants.MENU1);
                System.out.println(AppConstants.MENU2);
                System.out.println(AppConstants.MENU3);
                System.out.println(AppConstants.MENU4);
                System.out.println(AppConstants.MENU5);
                System.out.println(AppConstants.MENU6);
                System.out.println(AppConstants.MENU7);
                System.out.println(AppConstants.MENU_FOOTER);

                System.out.print(AppConstants.PROMPT_ENTER_CHOICE);
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
                        System.out.println(AppConstants.BYE_MESSAGE);
                        break;
                    default:
                        System.out.println(AppConstants.ERROR_INVALID_INPUT);
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }
    }
}
