import java.util.Scanner;

public class BankManagement {
    private static UserDAO userDAO = new UserDAO();
    private static User currentUser;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Check Balance");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            int option = scanner.nextInt();


            switch (option) {
                case 1:
                    register(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    deposit(scanner);
                    break;
                case 4:
                    withdraw(scanner);
                    break;
                case 5:
                    checkBalance();
                    break;
                case 6:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void register(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();


        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setBalance(0);

        if (userDAO.registerUser(user)) {
            System.out.println("User registered successfully.");
        } else {
            System.out.println("Registration failed.");
        }
    }

    private static void login(Scanner scanner) {
        scanner.nextLine();


        System.out.println("Enter email: ");
        String email = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        currentUser = userDAO.loginUser(email, password);
        if (currentUser != null) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    private static void deposit(Scanner scanner) {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return;
        }

        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (userDAO.updateBalance(currentUser, amount)) {
            currentUser.setBalance(currentUser.getBalance() + amount);
            System.out.println("Deposit successful. New balance: " + currentUser.getBalance());
        } else {
            System.out.println("Deposit failed.");
        }
    }

    private static void withdraw(Scanner scanner) {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return;
        }

        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (currentUser.getBalance() >= amount) {
            if (userDAO.updateBalance(currentUser, -amount)) {
                currentUser.setBalance(currentUser.getBalance() - amount);
                System.out.println("Withdrawal successful. New balance: " + currentUser.getBalance());
            } else {
                System.out.println("Withdrawal failed.");
            }
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    private static void checkBalance() {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return;
        }
        System.out.println("Current balance: " + currentUser.getBalance());
    }
}


