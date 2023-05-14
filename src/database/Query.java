package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
	// ATTRIBUTES

	// Check if mail exists in DB
	public static boolean checkMailDB(String mail) {
		String query = "SELECT mail FROM users where mail='" + mail + "'";
		// Create DB objects
		try {
			Statement stmt = Conn.conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				if (rs.getString("mail").equals(mail)) {
					return true;
				}
			}
			return false;
		} catch (SQLException sqle) {
			System.out.println("CHECK MAIL DB QUERY ERROR: " + sqle);
			return false;
		}
	}

	// Check if mail and password are correct
	public static boolean checkLogin(String mail, String passwd) {
		String query = "SELECT mail, passwd FROM users where mail='" + mail + "'";
		// Create DB objects
		try {
			Statement stmt = Conn.conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				if (rs.getString("mail").equals(mail) && rs.getString("passwd").equalsIgnoreCase(passwd)) {
					return true;
				}
			}
			return false;
		} catch (SQLException sqle) {
			System.out.println("CHECK LOGIN QUERY ERROR: " + sqle);
			return false;
		}
	}

	// Return user_id from mail query
	public static int returnUserIdfromMail(String mail) {
		String query = "SELECT user_id FROM users where mail='" + mail + "'";
		int user_id = -1;
		// Create DB objects
		try {
			Statement stmt = Conn.conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				user_id = rs.getInt("user_id");
			}
		} catch (SQLException sqle) {
			System.out.println("RETURN USER ID FROM MAIL QUERY ERROR: " + sqle);
		}

		return user_id;
	}

	// Create new User
	public static boolean createNewUser(String user_name, String user_surname, String mail, String passwd, int tier_id) {
		String query = "INSERT INTO users (user_name, user_surname, mail, passwd, tier_id) VALUES ('" + user_name + "', '"
				+ user_surname + "', '" + mail + "', '" + passwd + "', " + tier_id + ")";

		try {
			Statement stmt = Conn.conn.createStatement();
			stmt.executeUpdate(query);

			return true;
		} catch (SQLException sqle) {
			System.out.println("CREATE NEW USER QUERY ERROR: " + sqle);
			return false;
		}
	}

	// Select Last Insert Id from users (just for sign in case)
	public static int returnNewUserId() {
		String query = "SELECT user_id FROM users ORDER BY user_id DESC LIMIT 1";
		int new_id = -1;

		try {
			Statement stmt = Conn.conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				new_id = rs.getInt("user_id");
			}
		} catch (SQLException sqle) {
			System.out.println("RETURN NEW USER ID QUERY ERROR: " + sqle);
		}

		return new_id;
	}
}
