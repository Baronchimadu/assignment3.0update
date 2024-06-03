package com.coderscampus.assignment3;

import java.util.List;
import java.util.Scanner;

public class UserLoginApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        List<User> users = userService.loadUsersFromFile("data.txt");
        int failedAttempts = 0;
        boolean isAuthenticated = false;

        while (!isAuthenticated && failedAttempts < 5) {
            System.out.print("Enter your email: ");
            String username = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            isAuthenticated = userService.authenticateUser(username, password, users);

            if (isAuthenticated) {
                User authenticatedUser = getUserByUsername(username, users);
                System.out.println("Welcome: " + authenticatedUser.getName());
            } else {
                System.out.println("Invalid login, please try again");
                failedAttempts++;
            }
        }

        if (!isAuthenticated) {
            System.out.println("Too many failed login attempts, you are now locked out.");
        }
    }

    // Helper method to get the User object by username
    private static User getUserByUsername(String username, List<User> users) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }
}



