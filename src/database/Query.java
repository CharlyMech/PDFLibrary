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

	public static int returnCountAllBooks() {
		String query = "SELECT COUNT(book_id) as count FROM books";
		int count = -1;
		try {
			Statement stmt = Conn.conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException sqle) {
			System.out.println("COUNT ALL BOOKS QUERY ERROR: " + sqle);
		}

		return count;
	}

	public static ArrayList<Object> returnBookTier(int book_id) {
		ArrayList<Object> bookInfo = new ArrayList<>();
		String query = "SELECT books.tier_id as tier_id, books.title as title, authors.author_name as author_name FROM books JOIN authors on books.author_id=authors.author_id WHERE book_id="
				+ book_id;
		try {
			Statement stmt = Conn.conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				bookInfo.add(rs.getInt("tier_id"));
				bookInfo.add(rs.getString("title"));
				bookInfo.add(rs.getString("author_name"));
			}
		} catch (SQLException sqle) {
			System.out.println("BOOK ID QUERY ERROR: " + sqle);
		}

		return bookInfo;
	}

	public static ArrayList<Integer> returnAllBooksId() {
		ArrayList<Integer> booksList = new ArrayList<>();
		String query = "SELECT book_id FROM books ORDER BY RAND()";
		try {
			Statement stmt = Conn.conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				booksList.add(rs.getInt("book_id"));
			}
		} catch (SQLException sqle) {
			System.out.println("ALL BOOKS ID QUERY ERROR: " + sqle);
		}

		return booksList;
	}
	// - BOOK METHODS

}
