import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) throws Exception {
        // Run Login file to execute Login Window
        new Login();
        new Library();
    }

    // Check Mail Regular Expression
    public static boolean checkMail(String mail) {
        Pattern regex = Pattern.compile("^([a-zA-Z0-9_-])+@[a-zA-Z0-9]+.[a-z]{2,3}$");
        boolean match = regex.matcher(mail).find();

        return match;
    }

    // Check Password Regular Expression
    public static boolean checkPasswd(String passwd) {
        Pattern regex = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(=.*[.*!@#-,$^+=]).{8,32}$");
        boolean match = regex.matcher(passwd).find();

        return match;
    }
}
