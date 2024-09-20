package entity;

import utiles.Genre;
import utiles.bookStatus;

public class Books {
    private static int lastUsedId = 0;
    private String id;
    private String title;
    private String author;
    private Genre genre;
    private bookStatus status;

    public Books(String title, String author, Genre genre, bookStatus status) {
        this.id = generateUniqueId();
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = status;
    }

    private static String generateUniqueId() {
        lastUsedId++;
        return String.format("BOOK%03d", lastUsedId);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public bookStatus getStatus() {
        return status;
    }

    public void setStatus(bookStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Genre: " + genre;
    }
}
