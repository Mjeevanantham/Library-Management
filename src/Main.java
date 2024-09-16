import java.util.*;

public class Main {
    private static ArrayList<String> books = new ArrayList<>();
    private static ArrayList<String> borrowedBooks = new ArrayList<>();
    private static HashMap<String, String> users = new HashMap<>();

    public static void main(String[] args) {
        try {

            Scanner sc = new Scanner(System.in);
            boolean exit = false;

            users.put("ATS112", "Jeeva");
            users.put("ATS113", "Jeya");
            users.put("ATS114", "Pranav");
            books.add("Rich Dad Poor Dad");
            books.add("Money");
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
                        addBook(sc);
                        break;
                    case 2:
                        borrowBook(sc);
                        break;
                    case 3:
                        returnBook(sc);
                        break;
                    case 4:
                        viewBooks(sc);
                        break;
                    case 5:
                        registerUser(sc);
                        break;
                    case 6:
                        viewAllUsers();
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
            System.out.println("Something went wrong !");
        }
    }

    // Method to add books
    private static void addBook(Scanner sc) {
        System.out.print("Enter the name of the book to be added: ");
        String bookName = sc.nextLine();
        books.add(bookName);
        System.out.println("Book added successfully!");
    }

    // Method to borrow books
    private static void borrowBook(Scanner sc) {
        System.out.print("Enter User ID (ex: ATS112): ");
        String userId = sc.nextLine();

        if (!users.containsKey(userId)) {
            System.out.println("User not found! Please register first.");
            return;
        }

        System.out.print("Enter the name of the book to be borrowed: ");
        String bookName = sc.nextLine();

        if (books.contains(bookName)) {
            books.remove(bookName);
            borrowedBooks.add(bookName);
            System.out.println("Book borrowed successfully!");
        } else {
            System.out.println("Book not available!");
        }
    }

    // Method to return books
    private static void returnBook(Scanner sc) {
        System.out.print("Enter User ID (ex: ATS112): ");
        String userId = sc.nextLine();

        if (!users.containsKey(userId)) {
            System.out.println("User not found!");
            return;
        }

        System.out.print("Enter the name of the book to return: ");
        String bookName = sc.nextLine();

        if (borrowedBooks.contains(bookName)) {
            borrowedBooks.remove(bookName);
            books.add(bookName);
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("This book was not borrowed.");
        }
    }

    // Method to view all books
    private static void viewBooks(Scanner sc) {
        System.out.println("1: Available Books");
        System.out.println("2: Borrowed Books");
        System.out.print("Enter your choice: ");
        int subChoice = sc.nextInt();
        sc.nextLine(); // consume newline

        switch (subChoice) {
            case 1:
                if (books.isEmpty()) {
                    System.out.println("No available books.");
                } else {
                    System.out.println("Available Books:");
                    for (String book : books) {
                        System.out.println("- " + book);
                    }
                }
                break;
            case 2:
                if (borrowedBooks.isEmpty()) {
                    System.out.println("No borrowed books.");
                } else {
                    System.out.println("Borrowed Books:");
                    for (String book : borrowedBooks) {
                        System.out.println("- " + book);
                    }
                }
                break;
            default:
                System.out.println("Invalid option!");
        }
    }

    // Method to register users
    private static void registerUser(Scanner sc) {
        System.out.print("Enter Your Name: ");
        String userName = sc.nextLine();

        System.out.print("Enter An Employee ID (ex: ATS112): ");
        String userId = sc.nextLine();

        if (users.containsKey(userId)) {
            System.out.println("User ID already exists!");
        } else {
            users.put(userId, userName);
            System.out.println("User registered successfully!");
        }
    }

    // Method to view all users
    private static void viewAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No registered users.");
        } else {
            System.out.println("Registered Users:");
            for (Map.Entry<String, String> entry : users.entrySet()) {
                System.out.println("UserID: " + entry.getKey() + ", Name: " + entry.getValue());
            }
        }
    }
}
