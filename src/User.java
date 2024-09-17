import java.util.Map;
import java.util.Scanner;

import entity.Users;
import utiles.common.SubType;

public class User {
    SubType type;

    public static void registerUser(Scanner sc) {
        System.out.print("Enter Your Name: ");
        String userName = sc.nextLine();

        System.out.print("Enter An Employee ID (ex: ATS112): ");
        String userId = sc.nextLine();

        System.out.print("Enter your mail Id (ex: library@gmail.com): ");
        String userMailId = sc.nextLine();

        System.out.print("Subscription type (Free/Pro): ");
        String subTypeInput = sc.nextLine();

        if (!validate(userId, "userId")) {
            System.out.println("Invalid User ID format");
            return;
        }

        if (!validate(userMailId, "email")) {
            System.out.println("Invalid Email ID format");
            return;
        }

        SubType subType = getSubscriptionType(subTypeInput);
        if (subType == null) {
            System.out.println("Invalid subscription type. Please choose either Free or Pro.");
            return;
        }

        if (Library.users.containsKey(userId)) {
            System.out.println("User ID already exists!");
        } else {
            Library.users.put(userId, new Users(userName, userMailId, subType));
            System.out.println("User registered successfully!");
        }
    }

    public static void viewAllUsers() {
        if (Library.users.isEmpty()) {
            System.out.println("No registered users.");
        } else {
            System.out.println("Registered Users:");
            for (Map.Entry<String, Users> entry : Library.users.entrySet()) {
                Users userDetails = (Users) entry.getValue();
                System.out.println("UserID: " + entry.getKey() + ", Name: " + userDetails.getName() + ", Email: " + userDetails.getEmail() + ", Subscription: " + userDetails.getSubType());
            }
        }
    }

    private static boolean validate(String input, String type) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        String userIdRegex = "^ATS\\d{3}$"; // Updated to ensure 'ATS' followed by 3 digits

        switch (type) {
            case "email":
                return input.matches(emailRegex);
            case "userId":
                return input.matches(userIdRegex);
            default:
                return false;
        }
    }

    private static SubType getSubscriptionType(String subTypeInput) {
        if (subTypeInput.equalsIgnoreCase("Free")) {
            return SubType.FREE;
        } else if (subTypeInput.equalsIgnoreCase("Pro")) {
            return SubType.PRO;
        } else {
            return null;
        }
    }
}
