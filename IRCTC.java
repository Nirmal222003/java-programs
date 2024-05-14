package mysql;

import java.sql.*;
import java.util.Scanner;

public class IRCTC {
	static final String DB_URL = "jdbc:mysql://localhost:3306/nirmal_workspace";
	static final String USER = "root";
	static final String PASS = "Nirmal@22";
	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			createTables();

			int choice;
			do {
				System.out.println("1. Login");
				System.out.println("2. Register");
				System.out.println("3. View Train Details");
				System.out.println("4. Book Train Ticket");
				System.out.println("5. Cancel Train Ticket");
				System.out.println("6. Exit");
				System.out.print("Enter your choice: ");
				choice = scanner.nextInt();

				switch (choice) {
				case 1:
					login();
					break;
				case 2:
					register();
					break;
				case 3:
					viewTrainDetails();
					break;
				case 4:
					bookTicket();
					break;
				case 5:
					cancelTicket();
					break;
				case 6:
					System.out.println("Exiting program.");
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			} while (choice != 6);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	static void createTables() throws SQLException {

		stmt.executeUpdate(
				"CREATE TABLE IF NOT EXISTS users (id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(50), password VARCHAR(50))");
		stmt.executeUpdate(
				"CREATE TABLE IF NOT EXISTS trains (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100), source VARCHAR(100), destination VARCHAR(100), fare DOUBLE)");
		stmt.executeUpdate(
				"CREATE TABLE IF NOT EXISTS bookings (id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(50), train_name VARCHAR(100))");
	}

	static void login() throws SQLException {
		System.out.println("Login");
		System.out.print("Enter username: ");
		String username = scanner.next();
		System.out.print("Enter password: ");
		String password = scanner.next();

		rs = stmt.executeQuery("SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'");
		if (rs.next()) {
			System.out.println("Login successful!");
		} else {
			System.out.println("Invalid username or password.");
		}
	}

	static void register() throws SQLException {
		System.out.println("Registration");
		System.out.print("Enter username: ");
		String username = scanner.next();
		System.out.print("Enter password: ");
		String password = scanner.next();

		rs = stmt.executeQuery("SELECT * FROM users WHERE username='" + username + "'");
		if (rs.next()) {
			System.out.println("Username already exists. Please choose another username.");
		} else {
			stmt.executeUpdate(
					"INSERT INTO users (username, password) VALUES ('" + username + "', '" + password + "')");
			System.out.println("Registration successful!");
		}
	}

	static void viewTrainDetails() throws SQLException {
		System.out.println("Train Details");
		rs = stmt.executeQuery("SELECT * FROM trains");
		while (rs.next()) {
			System.out.println("Train ID: " + rs.getInt("id"));
			System.out.println("Train Name: " + rs.getString("name"));
			System.out.println("Source: " + rs.getString("source"));
			System.out.println("Destination: " + rs.getString("destination"));
			System.out.println("Fare: " + rs.getDouble("fare"));
			System.out.println();
		}
	}

	static void bookTicket() throws SQLException {
		System.out.println("Book Ticket");
		System.out.print("Enter username: ");
		String username = scanner.next();
		System.out.print("Enter train name: ");
		String trainName = scanner.next();

		rs = stmt.executeQuery("SELECT * FROM trains WHERE name='" + trainName + "'");
		if (!rs.next()) {
			System.out.println("Train does not exist.");
			return;
		}

		rs = stmt.executeQuery(
				"SELECT * FROM bookings WHERE user_id='" + username + "' AND train_id='" + trainName + "'");
		if (rs.next()) {
			System.out.println("You have already booked this train.");
			return;
		}

		stmt.executeUpdate("INSERT INTO bookings (user_id, train_id) VALUES ('" + username + "', '" + trainName + "')");
		System.out.println("Ticket booked successfully!");

	}

	static void cancelTicket() throws SQLException {
		System.out.println("Cancel Ticket");
		System.out.print("Enter username: ");
		String username = scanner.next();
		System.out.print("Enter train name: ");
		String trainName = scanner.next();

		rs = stmt.executeQuery(
				"SELECT * FROM bookings WHERE user_id='" + username + "' AND train_id='" + trainName + "'");
		if (!rs.next()) {
			System.out.println("Ticket not found.");
			return;
		}

		stmt.executeUpdate("DELETE FROM bookings WHERE user_id='" + username + "' AND train_id='" + trainName + "'");
		System.out.println("Ticket canceled successfully!");
	}

}
