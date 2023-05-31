package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

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
		String query = "SELECT users.user_name AS name, users.user_surname AS surname, users.mail AS mail, tiers.tier_name AS tier_name, users.tier_id AS tier_id FROM users JOIN tiers ON users.tier_id=tiers.tier_id WHERE users.user_id="
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

	// Check if Book is already in User's Library
	public static boolean checkIfBookInLibrary(int user_id, int book_id) {
		String query = "SELECT * FROM usersbooks WHERE user_id=? AND book_id=?";

		try {
			PreparedStatement stmt = Conn.conn.prepareStatement(query);
			stmt.setInt(1, user_id);
			stmt.setInt(2, book_id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				if (rs.next() == false) { // Empty case
					return false;
				}
			}
			return true; // Alredy in User's Libray case
		} catch (SQLException sqle) {
			System.out.println("CHECK IF BOOK IS ALREDY IN USERS LIBRARY QUERY ERROR: " + sqle);
			return false;
		}
	}

	// Check if Book is Read
	public static int checkIfRead(int user_id, int book_id) {
		int read = 0;
		String query = "SELECT readed FROM usersbooks WHERE user_id=? AND book_id=?";

		try {
			PreparedStatement stmt = Conn.conn.prepareStatement(query);
			stmt.setInt(1, user_id);
			stmt.setInt(2, book_id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				read = rs.getInt("readed");
			}
		} catch (SQLException sqle) {
			System.out.println("CHECK IF BOOK IS READ QUERY ERROR: " + sqle);
		}

		return read;
	}

	// Add Book to User's Library
	public static void addBookToUserLibrary(int user_id, int book_id) {
		String query = "INSERT INTO usersbooks (user_id, book_id) VALUES (?, ?)";

		try {
			PreparedStatement stmt = Conn.conn.prepareStatement(query);

			stmt.setInt(1, user_id);
			stmt.setInt(2, book_id);

			stmt.executeUpdate();
		} catch (SQLException sqle) {
			System.out.println("ADD BOOK TO USER LIBRARY QUERY ERROR: " + sqle);
		}
	}

	// Change Read Book
	public static void setBookReadStatus(int user_id, int book_id, int state) {
		String query = "UPDATE usersbooks SET readed=? WHERE user_id=? AND book_id=?";

		try {
			PreparedStatement stmt = Conn.conn.prepareStatement(query);
			stmt.setInt(1, state);
			stmt.setInt(2, user_id);
			stmt.setInt(3, book_id);

			stmt.executeUpdate();
		} catch (SQLException sqle) {
			System.out.println("SET BOOK READ STATUS QUERY ERROR" + sqle);
		}
	}

	// Remove Book from User's Library
	public static void removeBookFromUserLibrary(int user_id, int book_id) {
		String query = "DELETE FROM usersbooks WHERE user_id=? AND book_id=?";

		try {
			PreparedStatement stmt = Conn.conn.prepareStatement(query);

			stmt.setInt(1, user_id);
			stmt.setInt(2, book_id);

			stmt.executeUpdate();
		} catch (SQLException sqle) {
			System.out.println("REMOVE BOOK FROM USER LIBRARY QUERY ERROR: " + sqle);
		}
	}

	// Return User's Library Books
	public static ArrayList<Integer[]> returnUserBooksId(int user_id) {
		ArrayList<Integer[]> userBooksId = new ArrayList<Integer[]>();
		String query = "SELECT book_id, readed FROM usersbooks WHERE user_id=?";

		try {
			PreparedStatement stmt = Conn.conn.prepareStatement(query);

			stmt.setInt(1, user_id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Integer[] book = new Integer[] { rs.getInt("book_id"), rs.getInt("readed") };
				userBooksId.add(book);
			}
		} catch (SQLException sqle) {
			System.out.println("RETURN ALL BOOKS FROM USER LIBRARY QUERY ERROR: " + sqle);
		}

		return userBooksId;
	}

	// - USER METHODS

	// BOOK METHODS -
	// Return random book_id for random book panel in Library window
	public static int returnRandomBookId() {
		int bookId = -1;
		String query = "SELECT book_id FROM books ORDER BY RAND() LIMIT 1";

		try {
			Statement stmt = Conn.conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				bookId = rs.getInt("book_id");
			}
		} catch (SQLException sqle) {
			System.out.println("RANDOM BOOK COVER QUERY ERROR: " + sqle);
		}

		return bookId;
	}

	// Return coun tof books -> JScrollPane scroll size
	public static int returnCountAllBooks() {
		String query = "SELECT COUNT(book_id) AS count FROM books";
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

	// Return info about book, tier and book for BookRow object
	public static ArrayList<Object> returnBookTier(int book_id) {
		ArrayList<Object> bookInfo = new ArrayList<>();
		String query = "SELECT books.tier_id AS tier_id, books.title AS title, authors.author_name AS author_name FROM books JOIN authors on books.author_id=authors.author_id WHERE book_id="
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

	// Return all book_id to create every BookRow
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

	// Return all book info to be displayed in Book class window
	public static ArrayList<Object> returnBookInfo(int book_id) {
		ArrayList<Object> bookInfo = new ArrayList<Object>();
		String query = "SELECT books.title AS title, authors.author_name AS author, books.publisher AS publisher, books.pub_year AS year, books.lang AS lang, books.book_description AS summary, books.pages AS pages, books.tier_id AS tier FROM books JOIN authors ON books.author_id=authors.author_id WHERE book_id="
				+ book_id;

		try {
			Statement stmt = Conn.conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				bookInfo.add(rs.getString("title"));
				bookInfo.add(rs.getString("author"));
				bookInfo.add(rs.getString("publisher"));
				bookInfo.add(rs.getInt("year"));
				bookInfo.add(rs.getString("lang"));
				bookInfo.add(rs.getString("summary"));
				bookInfo.add(rs.getInt("pages"));
				bookInfo.add(rs.getInt("tier"));
			}
		} catch (SQLException sqle) {
			System.out.println("BOOK INFO QUERY ERROR: " + sqle);
		}

		return bookInfo;
	}

	// Return book cover URL from its ID
	public static String returnBookCover(int book_id) {
		String url = "";
		String query = "SELECT cover FROM books WHERE book_id=" + book_id;

		try {
			Statement stmt = Conn.conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				url = rs.getString("cover");
			}
		} catch (SQLException sqle) {
			System.out.println("BOOK COVER QUERY ERROR: " + sqle);
		}

		return url;
	}

	// Return all books Categories name by book ID
	public static ArrayList<String> returnBookCategoriesName(int book_id) {
		ArrayList<String> bookCatsName = new ArrayList<String>();
		String query = "SELECT categories.cat_name AS name FROM categories JOIN bookscats ON categories.cat_id=bookscats.cat_id WHERE bookscats.book_id="
				+ book_id;

		try {
			Statement stmt = Conn.conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				bookCatsName.add(rs.getString("name"));
			}
		} catch (SQLException sqle) {
			System.out.println("ALL CATS NAME FROM BOOK QUERY ERROR: " + sqle);
		}

		return bookCatsName;
	}
	// - BOOK METHODS

}
