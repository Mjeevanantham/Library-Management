package utiles;

public class AppConstants {
    // User ID pattern
    public static final String USER_ID_PATTERN = "ATS\\d{3}";

    // Email pattern
    public static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    public static final int FREE_BORROW_LIMIT = 2;
    public static final int PRO_BORROW_LIMIT = 5;

    public static final String MENU_HEADER = "========================== Library Management ===========================";
    public static final String MENU_FOOTER = "=========================================================================";

    // Error messages
    public static final String ERROR_USER_NOT_FOUND = "User not found!";
    public static final String ERROR_BOOK_NOT_FOUND = "Book not found!";
    public static final String ERROR_INVALID_INPUT = "Invalid input. Please try again.";
    public static final String ERROR_BORROW_LIMIT_REACHED = "You have reached your borrow limit!";

    // Success messages
    public static final String SUCCESS_BOOK_ADDED = "Book added successfully!";
    public static final String SUCCESS_BOOK_BORROWED = "Book borrowed successfully!";
    public static final String SUCCESS_BOOK_RETURNED = "Book returned successfully!";
    public static final String SUCCESS_USER_REGISTERED = "User registered successfully!";

    // Prompt messages
    public static final String PROMPT_ENTER_USER_ID = "Enter User ID (ex: ATS112): ";
    public static final String PROMPT_ENTER_BOOK_TITLE = "Enter the title of the book: ";
    public static final String PROMPT_ENTER_BOOK_AUTHOR = "Enter the book author: ";
    public static final String PROMPT_ENTER_BOOK_GENRE = "Enter the book genre: ";
    public static final String PROMPT_ENTER_USER_NAME = "Enter Your Name: ";
    public static final String PROMPT_ENTER_USER_EMAIL = "Enter your email (ex: library@gmail.com): ";
    public static final String PROMPT_ENTER_SUBSCRIPTION_TYPE = "Subscription type (Free/Pro): ";
}