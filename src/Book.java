import java.util.Scanner;

import entity.Books;
import entity.Users;
import utiles.AppConstants;
import utiles.Genre;
import utiles.bookStatus;

public class Book {
    public static void addBook(Scanner sc) {
        System.out.print(AppConstants.PROMPT_ENTER_USER_ID);
        String userId = sc.nextLine();
        if (Library.users.containsKey(userId)) {

            System.out.print(AppConstants.PROMPT_ENTER_BOOK_TITLE);
            String bookName = sc.nextLine();

            System.out.print(AppConstants.PROMPT_ENTER_BOOK_AUTHOR);
            String bookAuthor = sc.nextLine();

            System.out.print(AppConstants.PROMPT_ENTER_BOOK_GENRE);
            String bookGenre = sc.nextLine();
            if (getGenreType(bookGenre) == null) {
                System.out.print(AppConstants.ENTER_VALID_GENRE);
                return;
            }
            Books newBook = new Books(bookName, bookAuthor, getGenreType(bookGenre), bookStatus.AVAILABLE);
            Library.books.add(newBook);
            System.out.println(AppConstants.SUCCESS_BOOK_ADDED);
        } else {
            System.out.println(AppConstants.ERROR_USER_NOT_FOUND);
        }
    }

    public static void borrowBook(Scanner sc) {
        System.out.print(AppConstants.PROMPT_ENTER_USER_ID);
        String userId = sc.nextLine();

        if (!Library.users.containsKey(userId)) {
            System.out.println(AppConstants.ERROR_USER_NOT_FOUND);
            return;
        }
        Users user = Library.users.get(userId);
        if (user.getBorrowLimit() == 0) {
            System.out.println(AppConstants.ERROR_BORROW_LIMIT_REACHED);
            return;
        }
        System.out.print(AppConstants.PROMPT_ENTER_BOOK_TITLE);
        String bookName = sc.nextLine();

        Books borrowedBook = null;
        for (Books book : Library.books) {
            if (book.getTitle().equalsIgnoreCase(bookName)) {
                borrowedBook = book;
                break;
            }
        }
        if (borrowedBook == null) {
            System.out.println(AppConstants.ERROR_BOOK_NOT_FOUND);
        }

        if (borrowedBook != null) {
            Library.books.remove(borrowedBook);
            Library.borrowedBooks.add(borrowedBook.getTitle());
            user.addBorrowedBook(borrowedBook);
            user.setBorrowLimit(user.getBorrowLimit() - 1);
            System.out.println(AppConstants.SUCCESS_BOOK_BORROWED);
        } else {
            System.out.println(AppConstants.ERROR_BOOK_NOT_FOUND);
        }
    }

    public static void returnBook(Scanner sc) {
        System.out.print(AppConstants.PROMPT_ENTER_USER_ID);
        String userId = sc.nextLine();

        if (!Library.users.containsKey(userId)) {
            System.out.println(AppConstants.ERROR_USER_NOT_FOUND);
            return;
        }

        Users user = Library.users.get(userId);

        System.out.print(AppConstants.PROMPT_ENTER_BOOK_TITLE);
        String bookName = sc.nextLine();

        Books returnedBook = null;
        for (Books book : user.getBorrowedBooks()) {
            if (book.getTitle().equalsIgnoreCase(bookName)) {
                returnedBook = book;
                break;
            }
        }

        if (returnedBook == null) {
            System.out.println(AppConstants.ERROR_NOT_BORROWED);
            return;
        }

        user.removeBorrowedBook(returnedBook);
        user.setBorrowLimit(user.getBorrowLimit() + 1);

        Library.books.add(returnedBook);

        System.out.println(AppConstants.SUCCESS_BOOK_RETURNED);
    }

    public static void viewBooks(Scanner sc) {
        System.out.println("1: Available Books");
        System.out.println("2: Borrowed Books");
        System.out.print(AppConstants.PROMPT_ENTER_CHOICE);
        int subChoice = sc.nextInt();
        sc.nextLine();

        switch (subChoice) {
            case 1:
                if (Library.books.isEmpty()) {
                    System.out.println("No available books.");
                } else {
                    System.out.println("Available Books:");
                    for (Books book : Library.books) {
                        System.out.println("✅ " + book);
                    }
                }
                break;
            case 2:
                if (Library.borrowedBooks.isEmpty()) {
                    System.out.println("No borrowed books.");
                } else {
                    System.out.println("Borrowed Books:");
                    for (String book : Library.borrowedBooks) {
                        System.out.println("❌ " + book);
                    }
                }
                break;
            default:
                System.out.println("Invalid option!");
        }
    }

    private static Genre getGenreType(String genreInput) {
        if (genreInput.equalsIgnoreCase("Sci-fi")) {
            return Genre.SCI_FI;
        } else if (genreInput.equalsIgnoreCase("Novel")) {
            return Genre.NOVEL;
        } else if (genreInput.equalsIgnoreCase("Mystery")) {
            return Genre.MYSTERY;
        } else {
            return null;
        }
    }
}
