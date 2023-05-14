package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conn {
	// ATTRIBUTE
	protected static Connection conn = null;

	// CONSTRUCTOR
	public Conn() {
		this.connect();
	}

	// Create DB Connection Method
	private void connect() {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException exc) {
				System.out.println("No driver detected! " + exc);
			}

			this.conn = DriverManager.getConnection("jdbc:mysql://192.168.56.100:3306/Library", "library", "library");
		} catch (java.sql.SQLException sqle) {
			System.out.println("ERROR: " + sqle);
			JOptionPane.showMessageDialog(null, "There's been a problem connecting with the database\nPlease try later",
					"Connection Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Connection test method for login error
	public boolean connectionValid() {
		try {
			return this.conn.isValid(50000);
		} catch (SQLException sqle) {
			System.out.println("CONNECTION ERROR: " + sqle);
			return false;
		}
	}

	// Close DB Connection Method
	public static void closeConnection() {
		if (Conn.conn != null) {
			try {
				Conn.conn.close();
			} catch (SQLException sqle) {
				System.out.println("CLOSE CONN ERROR: " + sqle);
			}
		}
	}

	public static void main(String[] args) {
		Conn conn = new Conn();
	}
}
