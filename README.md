# Library Management System

## Description
This Java-based Library Management System provides a simple yet functional platform for managing books and users in a library setting. It allows for basic operations such as adding books, borrowing and returning books, user registration, and viewing both books and users.

## Features
- service.Book Management: Add, borrow, return, and view books
- service.User Management: Register users, view all users
- Subscription Types: Implement different user subscription types (Free and Pro) with varying borrowing limits
- Basic Input Validation: Ensures correct format for user IDs and email addresses

## Project Structure
- `Library.java`: Main class containing the program entry point and core logic
- `service/BookService.java`: Handles book-related operations & logic
- `service/UserService.java`: Manages user-related operations & logic
- `entity/Books.java`: Entity class for book objects
- `entity/Users.java`: Entity class for user objects
- `utiles/Genre.java`: Enum for book genres
- `utiles/bookStatus.java`: Enum for book status
- `utiles/subscriptionType.java`: Enum for user subscription types
