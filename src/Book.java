import java.util.Scanner;
import entity.Books;
import entity.Users;
import utiles.Genre;
import utiles.bookStatus;

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
            System.out.print("Enter the book genre: ");
            String bookGenre = sc.nextLine();
            if(getGenreType(bookGenre) == null)
            {
                System.out.print("Enter the book status: ");

            }
            Books newBook = new Books(bookId, bookName, bookAuthor, getGenreType(bookGenre), bookStatus.AVAILABLE);
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
        Users user = Library.users.get(userId);
        if (user.getBorrowedBooks() != null && user.getBorrowedBooks().size() >= user.getBorrowLimit()) {
            System.out.println("You have reached your borrow limit! Please return a book before borrowing another.");
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
        if (borrowedBook == null) {
            System.out.println("Oops! Book not found!");
        }

        if (borrowedBook != null) {
            Library.books.remove(borrowedBook);
            Library.borrowedBooks.add(borrowedBook.getTitle());
            user.addBorrowedBook(borrowedBook);
            user.setBorrowLimit(user.getBorrowLimit() - 1);
            System.out.println(">>>> " + user.getBorrowedBooks());
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

        Users user = Library.users.get(userId);

        System.out.print("Enter the title of the book to be returned: ");
        String bookName = sc.nextLine();

        Books returnedBook = null;
        for (Books book : user.getBorrowedBooks()) {
            if (book.getTitle().equalsIgnoreCase(bookName)) {
                returnedBook = book;
                break;
            }
        }

        if (returnedBook == null) {
            System.out.println("This book was not borrowed by the user.");
            return;
        }

        user.removeBorrowedBook(returnedBook);
        user.setBorrowLimit(user.getBorrowLimit() + 1);

        Library.books.add(returnedBook);

        System.out.println("Book returned successfully!");
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
