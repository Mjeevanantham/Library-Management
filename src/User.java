import java.util.*;
import java.util.Map;
import java.util.Scanner;

import entity.Books;
import entity.Users;
import utiles.AppConstants;
import utiles.subscriptionType;

public class User {
    subscriptionType type;

    public static void registerUser(Scanner sc) {
        System.out.print(AppConstants.PROMPT_ENTER_USER_NAME);
        String userName = sc.nextLine();

        System.out.print(AppConstants.PROMPT_ENTER_USER_ID);
        String userId = sc.nextLine();

        System.out.print(AppConstants.PROMPT_ENTER_USER_EMAIL);
        String userMailId = sc.nextLine();

        System.out.print(AppConstants.PROMPT_ENTER_SUBSCRIPTION_TYPE);
        String subTypeInput = sc.nextLine();

        if (validate(userId, "userId")) {
            System.out.println(AppConstants.ERROR_USER_NOT_FOUND);
            return;
        }

        if (validate(userMailId, "email")) {
            System.out.println(AppConstants.INVAILD_EMAIL_ID);
            return;
        }

        subscriptionType subType = getSubscriptionType(subTypeInput);
        if (subType == null) {
            System.out.println(AppConstants.INVAILD_SUBSCRIPTION_TYPE);
            return;
        }
        int borrowLimit = subType == subscriptionType.PRO ? AppConstants.PRO_BORROW_LIMIT : AppConstants.FREE_BORROW_LIMIT;

        if (Library.users.containsKey(userId)) {
            System.out.println(AppConstants.USER_ALREADY_EXIST);
        } else {
            Library.users.put(userId, new Users(userName, userMailId, subType, borrowLimit));
            System.out.println(AppConstants.SUCCESS_USER_REGISTERED);
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
                if (borrowedBooks.isEmpty()) {
                    System.out.println("   No borrowed books.");
                } else {
                    System.out.println("   Borrowed Books:");
                    for (Books book : borrowedBooks) {
                        System.out.println("   ➡️   Title: " + book.getTitle() + ", Book Name: " + book.getTitle() + ", Author: " + book.getAuthor());
                    }
                }
            }
        }
    }

    private static boolean validate(String input, String type) {
        String emailRegex = AppConstants.EMAIL_PATTERN;
        String userIdRegex = AppConstants.USER_ID_PATTERN;
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
