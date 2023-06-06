import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class HashFunction {

    public static byte[] computeHash(String message) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(message.getBytes());
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Select a simple message
        String originalMessage = "Hello, world!";

        // Perform a hash function on it
        byte[] originalMessageHash = computeHash(originalMessage);
        System.out.println("Original message hash: " + Arrays.toString(originalMessageHash));

        // Simulate a receiver computing the hash again and ensuring its integrity
        String receivedMessage = "Hello, world!";
        byte[] receivedMessageHash = computeHash(receivedMessage);
        System.out.println("Received message hash: " + Arrays.toString(receivedMessageHash));

        boolean isIntact = Arrays.equals(originalMessageHash, receivedMessageHash);
        System.out.println("Message integrity: " + isIntact);

        // Slightly change the message
        String alteredMessage = "Hello, world!!";

        // Simulate a receiver computing hash and find it not matching
        byte[] alteredMessageHash = computeHash(alteredMessage);
        System.out.println("Altered message hash: " + Arrays.toString(alteredMessageHash));

        boolean isAlteredIntact = Arrays.equals(originalMessageHash, alteredMessageHash);
        System.out.println("Altered message integrity: " + isAlteredIntact);
    }
}
