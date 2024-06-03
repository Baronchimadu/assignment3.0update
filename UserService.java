package com.coderscampus.assignment3;

	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.List;

public class UserService {
	    public List<User> loadUsersFromFile(String filename) {
	        List<User> users = new ArrayList<>();

	        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] parts = line.split(",");
	                if (parts.length == 3) { // Check if the line has the required number of fields
	                    String username = parts[0];
	                    String password = parts[1];
	                    String name = parts[2];
	                    users.add(new User(username, password, name));
	                } else {
	                    System.err.println("Skipping invalid line: " + line);
	                }
	            }
	        } catch (IOException e) {
	            System.err.println("Error reading file: " + e.getMessage());
	        }

	        return users;
	    }

	    public boolean authenticateUser(String username, String password, List<User> users) {
	        for (User user : users) {
	            if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
	                return true;
	            }
	        }
	        return false;
	    }
}
