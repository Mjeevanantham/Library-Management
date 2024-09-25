import java.util.*;

import service.BookService;
import service.UserService;
import utiles.AppConstants;

public class Library {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            boolean exit = false;
            BookService bookService = new BookService();
            UserService userService = new UserService();
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
                        bookService.addBook(sc);
                        break;
                    case 2:
                        bookService.borrowBook(sc);
                        break;
                    case 3:
                        bookService.returnBook(sc);
                        break;
                    case 4:
                        bookService.viewBooks(sc);
                        break;
                    case 5:
                        userService.registerUser(sc);
                        break;
                    case 6:
                        userService.viewAllUsers();
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
