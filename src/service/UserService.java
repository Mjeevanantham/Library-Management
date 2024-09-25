package service;

import java.util.*;
import java.util.Map;
import java.util.Scanner;

import entity.Books;
import entity.Users;
import utiles.AppConstants;
import utiles.subscriptionType;

public class UserService {

    public static Map<String, Users> users = new HashMap<>();
    public UserService(){
        users.put("ATS112", new Users("Jeeva", "jeeva@gmail.com", subscriptionType.FREE, 2));
        users.put("ATS113", new Users("james", "Kabil@gmail.com", subscriptionType.PRO, 3));
        users.put("ATS114", new Users("anand", "pranav@gmail.com", subscriptionType.FREE, 2));
    }

    public void registerUser(Scanner sc) {
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
            System.out.println(AppConstants.INVALID_EMAIL_ID);
            return;
        }

        subscriptionType subType = getSubscriptionType(subTypeInput);
        if (subType == null) {
            System.out.println(AppConstants.INVALID_SUBSCRIPTION_TYPE);
            return;
        }
        int borrowLimit = subType == subscriptionType.PRO ? AppConstants.PRO_BORROW_LIMIT : AppConstants.FREE_BORROW_LIMIT;

        if (users.containsKey(userId)) {
            System.out.println(AppConstants.USER_ALREADY_EXIST);
        } else {
            users.put(userId, new Users(userName, userMailId, subType, borrowLimit));
            System.out.println(AppConstants.SUCCESS_USER_REGISTERED);
        }
    }

    public void viewAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No registered users.");
        } else {
            System.out.println("Registered Users:");
            for (Map.Entry<String, Users> entry : users.entrySet()) {
                Users userDetails = entry.getValue();
                System.out.println("UserID: " + entry.getKey() + ", Name: " + userDetails.getName() + ", Email: " + userDetails.getEmail() + ", Subscription: " + userDetails.getSubType() + ", Borrow Limit: " + userDetails.getBorrowLimit());
                List<Books> borrowedBooks = userDetails.getBorrowedBooks();
                if (borrowedBooks.isEmpty()) {
                    System.out.println("   No borrowed books.");
                } else {
                    System.out.println("   Borrowed Books:");
                    for (Books book : borrowedBooks) {
                        System.out.println("   ➡️   Title: " + book.getTitle() + ", service.Book Name: " + book.getTitle() + ", Author: " + book.getAuthor());
                    }
                }
            }
        }
    }

    private boolean validate(String input, String type) {
        String emailRegex = AppConstants.EMAIL_PATTERN;
        String userIdRegex = AppConstants.USER_ID_PATTERN;
        return switch (type) {
            case "email" -> !input.matches(emailRegex);
            case "userId" -> !input.matches(userIdRegex);
            default -> true;
        };
    }


    private subscriptionType getSubscriptionType(String subTypeInput) {
        if (subTypeInput.equalsIgnoreCase("Free")) {
            return subscriptionType.FREE;
        } else if (subTypeInput.equalsIgnoreCase("Pro")) {
            return subscriptionType.PRO;
        } else {
            return null;
        }
    }
}
