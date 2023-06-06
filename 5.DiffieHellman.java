import java.math.BigInteger;

public class DiffieHellman {
    public static void main(String[] args) {
        // Given values
        BigInteger g = BigInteger.valueOf(9);
        BigInteger p = BigInteger.valueOf(23);
        BigInteger x = BigInteger.valueOf(4);
        BigInteger y = BigInteger.valueOf(3);

        // Calculate public values
        BigInteger alicePublicValue = g.modPow(x, p);
        BigInteger bobPublicValue = g.modPow(y, p);

        // Exchange public values
        BigInteger aliceReceivedPublicValue = bobPublicValue;
        BigInteger bobReceivedPublicValue = alicePublicValue;

        // Calculate symmetric keys
        BigInteger aliceSymmetricKey = aliceReceivedPublicValue.modPow(x, p);
        BigInteger bobSymmetricKey = bobReceivedPublicValue.modPow(y, p);

        // Output
        System.out.println("Alice's public value: " + alicePublicValue);
        System.out.println("Bob's public value: " + bobPublicValue);
        System.out.println("Alice's symmetric key: " + aliceSymmetricKey);
        System.out.println("Bob's symmetric key: " + bobSymmetricKey);
    }
}
