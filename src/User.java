import java.util.*;
import java.util.Map;
import java.util.Scanner;
import entity.Books;
import entity.Users;
import utiles.subscriptionType;

public class User {
    subscriptionType type;

    public static void registerUser(Scanner sc) {
        System.out.print("Enter Your Name: ");
        String userName = sc.nextLine();

        System.out.print("Enter An Employee ID (ex: ATS112): ");
        String userId = sc.nextLine();

        System.out.print("Enter your mail Id (ex: library@gmail.com): ");
        String userMailId = sc.nextLine();

        System.out.print("Subscription type (Free/Pro): ");
        String subTypeInput = sc.nextLine();

        if (validate(userId, "userId")) {
            System.out.println("Invalid User ID format");
            return;
        }

        if (validate(userMailId, "email")) {
            System.out.println("Invalid Email ID format");
            return;
        }

        subscriptionType subType = getSubscriptionType(subTypeInput);
        if (subType == null) {
            System.out.println("Invalid subscription type. Please choose either Free or Pro.");
            return;
        }
        int borrowLimit = subType == subscriptionType.PRO ? 5 : 2;

        if (Library.users.containsKey(userId)) {
            System.out.println("User ID already exists!");
        } else {
            Library.users.put(userId, new Users(userName, userMailId, subType, borrowLimit));
            System.out.println("User registered successfully!");
        }
    }

    public static void viewAllUsers() {
        if (Library.users.isEmpty()) {
            System.out.println("No registered users.");
        } else {
            System.out.println("Registered Users:");
            for (Map.Entry<String, Users> entry : Library.users.entrySet()) {
                Users userDetails = entry.getValue();
                System.out.println("UserID: " + entry.getKey() + ", Name: " + userDetails.getName() + ", Email: " + userDetails.getEmail() + ", Subscription: " + userDetails.getSubType() + ", Borrow Limit: " + userDetails.getBorrowLimit());
                List<Books> borrowedBooks = userDetails.getBorrowedBooks();
                if (borrowedBooks == null) {
                    System.out.println("   No borrowed books.");
                } else {
                    System.out.println("   Borrowed Books:");
                    for (Books book : borrowedBooks) {
                        System.out.println("      Title: " + book.getTitle()
                                + ", Book Name: " + book.getTitle()
                                + ", Author: " + book.getAuthor());
                    }
                }
            }
        }
    }

    private static boolean validate(String input, String type) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        String userIdRegex = "^ATS\\d{3}$";
        switch (type) {
            case "email":
                return !input.matches(emailRegex);
            case "userId":
                return !input.matches(userIdRegex);
            default:
                return true;
        }
    }


    private static subscriptionType getSubscriptionType(String subTypeInput) {
        if (subTypeInput.equalsIgnoreCase("Free")) {
            return subscriptionType.FREE;
        } else if (subTypeInput.equalsIgnoreCase("Pro")) {
            return subscriptionType.PRO;
        } else {
            return null;
        }
    }
}
