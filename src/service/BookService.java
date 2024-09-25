package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import entity.Books;
import entity.Users;
import utiles.AppConstants;
import utiles.Genre;
import utiles.bookStatus;

import static service.UserService.users;

public class BookService {

    private List<Books> books = new ArrayList<>();
    private List<String> borrowedBooks = new ArrayList<>();

    public BookService() {
        books.add(new Books("Rich Dad Poor Dad", "Robert", Genre.SCI_FI, bookStatus.AVAILABLE));
        books.add(new Books("Basketball", "Lebron james", Genre.NOVEL, bookStatus.AVAILABLE));
        books.add(new Books("Football", "Messi", Genre.NOVEL, bookStatus.AVAILABLE));
    }

    public void addBook(Scanner sc) {
        System.out.print(AppConstants.PROMPT_ENTER_USER_ID);
        String userId = sc.nextLine();

        if (users.containsKey(userId)) {
            System.out.println(AppConstants.BOOK_MENU1);
            System.out.println(AppConstants.BOOK_MENU2);
            System.out.print(AppConstants.PROMPT_ENTER_CHOICE);
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addSingleBook(sc);
                    break;
                case 2:
                    addMultipleBooks(sc);
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } else {
            System.out.println(AppConstants.ERROR_USER_NOT_FOUND);
        }
    }

    private void addSingleBook(Scanner sc) {
        System.out.print(AppConstants.PROMPT_ENTER_BOOK_TITLE);
        String bookName = sc.nextLine();
        for (Books book : books) {
            if (book.getTitle().equalsIgnoreCase(bookName)) {
                System.out.print(AppConstants.BOOK_ALREADY_EXIST);
                break;
            }
        }
        System.out.print(AppConstants.PROMPT_ENTER_BOOK_AUTHOR);
        String bookAuthor = sc.nextLine();

        System.out.print(AppConstants.PROMPT_ENTER_BOOK_GENRE);
        String bookGenre = sc.nextLine();

        Genre genre = getGenreType(bookGenre);
        if (genre == null) {
            System.out.println(AppConstants.ENTER_VALID_GENRE);
            return;
        }

        Books newBook = new Books(bookName, bookAuthor, genre, bookStatus.AVAILABLE);
        books.add(newBook);
        System.out.println(AppConstants.SUCCESS_BOOK_ADDED);
    }

    private void addMultipleBooks(Scanner sc) {
        System.out.print(AppConstants.PROMPT_ENTER_MULTIPLE_BOOK_TITLE);
        String bookDetails = sc.nextLine();

        StringTokenizer bookTokenizer = new StringTokenizer(bookDetails, ";");

        while (bookTokenizer.hasMoreTokens()) {
            String bookData = bookTokenizer.nextToken();
            StringTokenizer detailsTokenizer = new StringTokenizer(bookData, ",");

            if (detailsTokenizer.countTokens() == 3) {
                String bookTitle = detailsTokenizer.nextToken().trim();
                String bookAuthor = detailsTokenizer.nextToken().trim();
                String bookGenre = detailsTokenizer.nextToken().trim();
                Boolean IsAlreadyExist = false;
                for (Books book : books) {
                    if (book.getTitle().equalsIgnoreCase(bookTitle)) {
                        IsAlreadyExist = true;
                        break;
                    }
                }
                if (IsAlreadyExist) {
                    System.out.println(AppConstants.BOOK_ALREADY_EXIST);
                    break;
                }
                Genre genre = getGenreType(bookGenre);
                if (genre != null) {
                    books.add(new Books(bookTitle, bookAuthor, genre, bookStatus.AVAILABLE));
                    System.out.println("Book added: " + bookTitle);
                } else {
                    System.out.println(AppConstants.ENTER_VALID_GENRE);
                }
            } else {
                System.out.println(AppConstants.ENTER_BOOK_FORMAT_GENRE + bookData);
            }
        }
    }

    public void borrowBook(Scanner sc) {
        System.out.print(AppConstants.PROMPT_ENTER_USER_ID);
        String usersId = sc.nextLine();

        if (!users.containsKey(usersId)) {
            System.out.println(AppConstants.ERROR_USER_NOT_FOUND);
            return;
        }
        Users user = users.get(usersId);
        if (user.getBorrowLimit() == 0) {
            System.out.println(AppConstants.ERROR_BORROW_LIMIT_REACHED);
            return;
        }
        System.out.print(AppConstants.PROMPT_ENTER_BOOK_TITLE);
        String bookName = sc.nextLine();

        Books borrowedBook = null;
        for (Books book : books) {
            if (book.getTitle().equalsIgnoreCase(bookName)) {
                borrowedBook = book;
                break;
            }
        }
        if (borrowedBook == null) {
            System.out.println(AppConstants.ERROR_BOOK_NOT_FOUND);
        }

        if (borrowedBook != null) {
            books.remove(borrowedBook);
            borrowedBooks.add(borrowedBook.getTitle());
            user.addBorrowedBook(borrowedBook);
            user.setBorrowLimit(user.getBorrowLimit() - 1);
            System.out.println(AppConstants.SUCCESS_BOOK_BORROWED);
        } else {
            System.out.println(AppConstants.ERROR_BOOK_NOT_FOUND);
        }
    }

    public void returnBook(Scanner sc) {
        System.out.print(AppConstants.PROMPT_ENTER_USER_ID);
        String userId = sc.nextLine();

        if (!users.containsKey(userId)) {
            System.out.println(AppConstants.ERROR_USER_NOT_FOUND);
            return;
        }

        Users user = users.get(userId);

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

        books.add(returnedBook);
        borrowedBooks.remove(returnedBook.getTitle());
        System.out.println(AppConstants.SUCCESS_BOOK_RETURNED);
    }

    public void viewBooks(Scanner sc) {
        System.out.println("1: Available Books");
        System.out.println("2: Borrowed Books");
        System.out.print(AppConstants.PROMPT_ENTER_CHOICE);
        int subChoice = sc.nextInt();
        sc.nextLine();

        switch (subChoice) {
            case 1:
                if (books.isEmpty()) {
                    System.out.println("No available books.");
                } else {
                    StringBuilder availableBooks = new StringBuilder("Available Books:\n");
                    for (Books book : books) {
                        availableBooks.append("✅ ").append(book.toString()).append("\n");
                    }
                    System.out.println(availableBooks.toString());

                }
                break;
            case 2:
                if (borrowedBooks.isEmpty()) {
                    System.out.println("No borrowed books.");
                } else {
                    StringBuilder borrowedBooksList = new StringBuilder("Borrowed Books:\n");
                    for (String book : borrowedBooks) {
                        borrowedBooksList.append("❌ ").append(book).append("\n");
                    }
                    System.out.println(borrowedBooksList.toString());
                }
                break;
            default:
                System.out.println("Invalid option!");
        }
    }

    private Genre getGenreType(String genreInput) {
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
