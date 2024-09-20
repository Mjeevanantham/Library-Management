package utiles;

public class AppConstants {
    // Regex
    public static final String USER_ID_PATTERN = "ATS\\d{3}";
    public static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    public static final int FREE_BORROW_LIMIT = 2;
    public static final int PRO_BORROW_LIMIT = 5;

    public static final String MENU_HEADER = "========================== Library Management ===========================";
    public static final String MENU_FOOTER = "=========================================================================";

    public static final String MENU1 = "1: Add Books";
    public static final String MENU2 = "2: Borrow Books";
    public static final String MENU3 = "3: Return Books";
    public static final String MENU4 = "4: View All Books";
    public static final String MENU5 = "5: User Registration";
    public static final String MENU6 = "6: View All Users";
    public static final String MENU7 = "7: Exit";

    // Error messages
    public static final String ERROR_USER_NOT_FOUND = "User not found!";
    public static final String ERROR_BOOK_NOT_FOUND = "Book not found!";
    public static final String ERROR_INVALID_INPUT = "Invalid input. Please try again.";
    public static final String ERROR_BORROW_LIMIT_REACHED = "You have reached your borrow limit!";
    public static final String ERROR_NOT_BORROWED = "This book was not borrowed by the user.";
    public static final String INVAILD_EMAIL_ID = "Invalid Email ID format";
    public static final String INVAILD_SUBSCRIPTION_TYPE = "Invalid subscription type. Please choose either Free or Pro.";
    public static final String USER_ALREADY_EXIST = "User ID already exists!";
    public static final String ENTER_VALID_GENRE = "Please select any one of the given genre!!!\n";

    // Success messages
    public static final String SUCCESS_BOOK_ADDED = "Book added successfully!";
    public static final String SUCCESS_BOOK_BORROWED = "Book borrowed successfully!";
    public static final String SUCCESS_BOOK_RETURNED = "Book returned successfully!";
    public static final String SUCCESS_USER_REGISTERED = "User registered successfully!";
    public static final String BYE_MESSAGE = "Exiting the Library Management System. Goodbye!";

    // Prompt messages
    public static final String PROMPT_ENTER_USER_ID = "Enter User ID (ex: ATS112): ";
    public static final String PROMPT_ENTER_BOOK_TITLE = "Enter the title of the book: ";
    public static final String PROMPT_ENTER_BOOK_AUTHOR = "Enter the book author: ";
    public static final String PROMPT_ENTER_BOOK_GENRE = "Enter the book genre (Sci-fi or Novel or Mystery): ";
    public static final String PROMPT_ENTER_USER_NAME = "Enter Your Name: ";
    public static final String PROMPT_ENTER_USER_EMAIL = "Enter your email (ex: library@gmail.com): ";
    public static final String PROMPT_ENTER_SUBSCRIPTION_TYPE = "Subscription type (Free/Pro): ";
    public static final String PROMPT_ENTER_CHOICE = "Enter your choice: ";
}