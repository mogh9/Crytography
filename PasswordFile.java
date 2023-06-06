import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class PasswordFile {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Map<String, String> users = new HashMap<>();
        String[] passwords = {"password1", "password2", "password3", "password4", "password5", "password6", "password7", "password8", "password9", "password10"};

        // Store the hash values of passwords along with salts
        for (String password : passwords) {
            String salt = generateSalt();
            String hashedPassword = hashPassword(password, salt);
            users.put(salt, hashedPassword);
        }

        // Sample output
        for (Map.Entry<String, String> entry : users.entrySet()) {
            System.out.println("Salt: " + entry.getKey() + ", Hashed Password: " + entry.getValue());
        }
    }

    private static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        String saltedPassword = salt + password;
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] hashedBytes = md.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hashedBytes);
    }
}
