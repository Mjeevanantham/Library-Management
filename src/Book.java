import java.util.Scanner;

import entity.Books;

public class Book {

    public static void addBook(Scanner sc) {
        System.out.print("Enter User ID: ");
        String userId = sc.nextLine();
        if (Library.users.containsKey(userId)) {
            System.out.print("Enter the book ID: ");
            int bookId = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter the title of the book: ");
            String bookName = sc.nextLine();

            System.out.print("Enter the book author: ");
            String bookAuthor = sc.nextLine();

            System.out.print("Enter the book genre: ");
            String bookGenre = sc.nextLine();

            Books newBook = new Books(bookId, bookName, bookAuthor, bookGenre);
            Library.books.add(newBook);
            System.out.println("Book added successfully!");
        } else {
            System.out.println("Oops!! User not found!");
        }
    }

    public static void borrowBook(Scanner sc) {
        System.out.print("Enter User ID (ex: ATS112): ");
        String userId = sc.nextLine();

        if (!Library.users.containsKey(userId)) {
            System.out.println("User not found! Please register first.");
            return;
        }

        System.out.print("Enter the title of the book to be borrowed: ");
        String bookName = sc.nextLine();

        Books borrowedBook = null;
        for (Books book : Library.books) {
            if (book.getTitle().equalsIgnoreCase(bookName)) {
                borrowedBook = book;
                break;
            }
        }

        if (borrowedBook != null) {
            Library.books.remove(borrowedBook);
            Library.borrowedBooks.add(borrowedBook.getTitle());
            System.out.println("Book borrowed successfully!");
        } else {
            System.out.println("Book not available!");
        }
    }

    public static void returnBook(Scanner sc) {
        System.out.print("Enter User ID (ex: ATS112): ");
        String userId = sc.nextLine();

        if (!Library.users.containsKey(userId)) {
            System.out.println("User not found!");
            return;
        }

        System.out.print("Enter the title of the book to return: ");
        String bookName = sc.nextLine();

        if (Library.borrowedBooks.contains(bookName)) {
            Library.borrowedBooks.remove(bookName);

            System.out.print("Enter the book ID: ");
            int bookId = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter the author: ");
            String author = sc.nextLine();
            System.out.print("Enter the genre: ");
            String genre = sc.nextLine();

            Books returnedBook = new Books(bookId, bookName, author, genre);
            Library.books.add(returnedBook);
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("This book was not borrowed.");
        }
    }

    public static void viewBooks(Scanner sc) {
        System.out.println("1: Available Books");
        System.out.println("2: Borrowed Books");
        System.out.print("Enter your choice: ");
        int subChoice = sc.nextInt();
        sc.nextLine();

        switch (subChoice) {
            case 1:
                if (Library.books.isEmpty()) {
                    System.out.println("No available books.");
                } else {
                    System.out.println("Available Books:");
                    for (Books book : Library.books) {
                        System.out.println("- " + book);
                    }
                }
                break;
            case 2:
                if (Library.borrowedBooks.isEmpty()) {
                    System.out.println("No borrowed books.");
                } else {
                    System.out.println("Borrowed Books:");
                    for (String book : Library.borrowedBooks) {
                        System.out.println("- " + book);
                    }
                }
                break;
            default:
                System.out.println("Invalid option!");
        }
    }
}
