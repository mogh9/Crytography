public class AS {
    // Encryption: E(x) = (ax + b) mod 26
    public static String encrypt(String plaintext, int a, int b) {
        StringBuilder ciphertext = new StringBuilder();

        for (char ch : plaintext.toCharArray()) {
            if (Character.isLetter(ch)) {
                // Convert uppercase to lowercase for simplicity
                ch = Character.toLowerCase(ch);

                int plainValue = ch - 'a';
                int cipherValue = (a * plainValue + b) % 26;

                char cipherChar = (char) (cipherValue + 'a');
                ciphertext.append(cipherChar);
            } else {
                ciphertext.append(ch);
            }
        }

        return ciphertext.toString();
    }

    // Decryption: D(x) = a^(-1)(x - b) mod 26
    public static String decrypt(String ciphertext, int a, int b) {
        StringBuilder plaintext = new StringBuilder();

        int multiplicativeInverse = getMultiplicativeInverse(a);
        if (multiplicativeInverse == -1) {
            return "Error: a and 26 are not coprime. Decryption is not possible.";
        }

        for (char ch : ciphertext.toCharArray()) {
            if (Character.isLetter(ch)) {
                // Convert uppercase to lowercase for simplicity
                ch = Character.toLowerCase(ch);

                int cipherValue = ch - 'a';
                int plainValue = (multiplicativeInverse * (cipherValue - b + 26)) % 26;

                char plainChar = (char) (plainValue + 'a');
                plaintext.append(plainChar);
            } else {
                plaintext.append(ch);
            }
        }

        return plaintext.toString();
    }

    private static int getMultiplicativeInverse(int a) {
        for (int i = 1; i < 26; i++) {
            if ((a * i) % 26 == 1) {
                return i;
            }
        }
        return -1; // Inverse does not exist
    }

    public static void main(String[] args) {
        String plaintext = "hello world";
        int a = 5;
        int b = 8;

        String encryptedText = encrypt(plaintext, a, b);
        System.out.println("Encrypted: " + encryptedText);

        String decryptedText = decrypt(encryptedText, a, b);
        System.out.println("Decrypted: " + decryptedText);
    }
}
