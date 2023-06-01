package windows;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

import database.Conn;

public class App {
    public static void main(String[] args) {
        // Create the connection object
        Conn conn = new Conn();

        if (conn.connectionValid()) {
            new Login();
        }
    }

    // Check Mail Regular Expression
    public static boolean checkMail(String mail) {
        Pattern regex = Pattern.compile("^([a-zA-Z0-9_-])+@[a-zA-Z0-9]+.[a-z]{2,3}$");
        boolean match = regex.matcher(mail).find();

        return match;
    }

    // Check Password Regular Expression
    public static boolean checkPasswd(String passwd) {
        Pattern regex = Pattern.compile("^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,64}$");
        boolean match = regex.matcher(passwd).find();

        return match;
    }

    // Hash Password in SHA-256
    // ? https://www.youtube.com/watch?v=ef3kenC4xa0&ab_channel=Randomcode
    public static String hashPassword(String passwd) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] message = md.digest(passwd.getBytes());

            BigInteger bigInt = new BigInteger(1, message);

            return bigInt.toString(16);

        } catch (NoSuchAlgorithmException e) {
            System.out.println("ERROR: " + e);
            return "";
        }

    }
}
