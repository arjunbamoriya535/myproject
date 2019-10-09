package Hospital;

public class Login {
 
    public static boolean authenticate(String username, String password) {
        // hardcoded username and password
        if (username.equals("arjun") && password.equals("arjun")) {
            return true;
        }
        return false;
    }
}