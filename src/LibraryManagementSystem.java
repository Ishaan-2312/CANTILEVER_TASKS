import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {
    private static final BookDAO bookDAO = new BookDAO();
    private static final UserDAO userDAO = new UserDAO();
    private static final CheckoutDAO checkoutDAO = new CheckoutDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nLibrary Management System:");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. View All Books");
            System.out.println("4. Add User");
            System.out.println("5. Remove User");
            System.out.println("6. View All Users");
            System.out.println("7. Checkout Book");
            System.out.println("8. Return Book");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        addBook();
                        break;
                    case 2:
                        removeBook();
                        break;
                    case 3:
                        viewAllBooks();
                        break;
                    case 4:
                        addUser();
                        break;
                    case 5:
                        removeUser();
                        break;
                    case 6:
                        viewAllUsers();
                        break;
                    case 7:
                        checkoutBook();
                        break;
                    case 8:
                        returnBook();
                        break;
                    case 9: {
                        System.out.println("Exiting...");
                        return;
                    }
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void addBook() throws SQLException {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        bookDAO.addBook(title, author);
        System.out.println("Book added successfully.");
    }

    private static void removeBook() throws SQLException {
        System.out.print("Enter book ID to remove: ");
        int bookId = scanner.nextInt();
        bookDAO.removeBook(bookId);
        System.out.println("Book removed successfully.");
    }

    private static void viewAllBooks() throws SQLException {
        List<Book> books = bookDAO.getAllBooks();
        System.out.println("\nBooks in Library:");
        for (Book book : books) {
            System.out.printf("ID: %d, Title: %s, Author: %s, Available: %b%n", book.getId(), book.getTitle(),
                    book.getAuthor(), book.isAvailable());
        }
    }

    private static void addUser() throws SQLException {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter user email: ");
        String email = scanner.nextLine();
        userDAO.addUser(name, email);
        System.out.println("User added successfully.");
    }

    private static void removeUser() throws SQLException {
        System.out.print("Enter user ID to remove: ");
        int userId = scanner.nextInt();
        userDAO.removeUser(userId);
        System.out.println("User removed successfully.");
    }

    private static void viewAllUsers() throws SQLException {
        List<User> users = userDAO.getAllUsers();
        System.out.println("\nUsers:");
        for (User user : users) {
            System.out.printf("ID: %d, Name: %s, Email: %s%n", user.getId(), user.getName(), user.getEmail());
        }
    }

    private static void checkoutBook() throws SQLException {
        System.out.print("Enter book ID to checkout: ");
        int bookId = scanner.nextInt();
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        checkoutDAO.checkoutBook(bookId, userId);
        System.out.println("Book checked out successfully.");
    }

    private static void returnBook() throws SQLException {
        System.out.print("Enter book ID to return: ");
        int bookId = scanner.nextInt();
        checkoutDAO.returnBook(bookId);
        System.out.println("Book returned successfully.");
    }
}