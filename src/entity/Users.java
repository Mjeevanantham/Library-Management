package entity;

import utiles.subscriptionType;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private final String name;
    private final String email;
    private final subscriptionType subType;
    private int borrowLimit;
    private final List<Books> booksBorrowed = new ArrayList<>();

    public Users(String name, String email, subscriptionType subType, int borrowLimit) {
        this.name = name;
        this.email = email;
        this.subType = subType;
        this.borrowLimit = borrowLimit;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public subscriptionType getSubType() {
        return subType;
    }

    public int getBorrowLimit() {
        return borrowLimit;
    }

    public void setBorrowLimit(int borrowLimit) {
        this.borrowLimit = borrowLimit;
    }

    public List<Books> getBorrowedBooks() {
        return booksBorrowed;
    }

    public void addBorrowedBook(Books borrowedBook) {
        this.booksBorrowed.add(borrowedBook);
    }

    public void removeBorrowedBook(Books returnedBook) {
        this.booksBorrowed.remove(returnedBook);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email + ", Subscription: " + subType;
    }
}
