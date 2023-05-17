package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.SpringLayout;
import javax.swing.plaf.nimbus.State;

public class Query {
	// USER METHODS -

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

	// Return Last Insert Id from users (just for sign in case)
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

	// Return User Info by ID
	public static ArrayList<Object> returnUserInfo(int user_id) {
		ArrayList<Object> userInfo = new ArrayList<>();
		String query = "SELECT users.user_name as name, users.user_surname as surname, users.mail as mail, tiers.tier_name as tier_name, users.tier_id as tier_id FROM users JOIN tiers ON users.tier_id=tiers.tier_id WHERE users.user_id="
				+ user_id;

		try {
			Statement stmt = Conn.conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				userInfo.add(rs.getString("name"));
				userInfo.add(rs.getString("surname"));
				userInfo.add(rs.getString("mail"));
				userInfo.add(rs.getString("tier_name"));
				userInfo.add(rs.getInt("tier_id"));
			}
		} catch (SQLException sqle) {
			System.out.println("RETURN USER INFO QUERY ERROR: " + sqle);
		}

		return userInfo;
	}

	// Update User (no Passwd) Info by ID
	public static boolean updateUserInfo(int user_id, String user_name, String user_surname, String mail,
			String passwd, int tier_id) {
		String query = "UPDATE users SET user_name='" + user_name + "', user_surname='" + user_surname + "', mail='"
				+ mail + "', passwd='" + passwd + "', tier_id=" + tier_id + " WHERE user_id=" + user_id;

		try {
			Statement stmt = Conn.conn.createStatement();
			stmt.executeUpdate(query);

			return true;
		} catch (SQLException sqle) {
			System.out.println("UPDATE USER QUERY ERROR: " + sqle);
			return false;
		}

	}

	// Update User (no Passwd) Info by ID
	public static boolean updateUserInfoNoPasswd(int user_id, String user_name, String user_surname, String mail,
			int tier_id) {
		String query = "UPDATE users SET user_name='" + user_name + "', user_surname='" + user_surname + "', mail='"
				+ mail + "', tier_id=" + tier_id + " WHERE user_id=" + user_id;

		try {
			Statement stmt = Conn.conn.createStatement();
			stmt.executeUpdate(query);

			return true;
		} catch (SQLException sqle) {
			System.out.println("UPDATE USER NO PASSWD QUERY ERROR: " + sqle);
			return false;
		}

	}

	// - USER METHODS

	// BOOK METHODS -
	public static String returnRandomBookCover() {
		String query = "SELECT cover FROM books ORDER BY RAND() LIMIT 1";
		String url = "";

		try {
			Statement stmt = Conn.conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				url = rs.getString("cover");
			}
		} catch (SQLException sqle) {
			System.out.println("RANDOM BOOK COVER QUERY ERROR: " + sqle);
		}

		return url;
	}
	// - BOOK METHODS

}
