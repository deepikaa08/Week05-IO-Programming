import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Base64;

public class EncryptDecrypt {
    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "1234567890123456";


    public static String encrypt(String value) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = cipher.doFinal(value.getBytes());
        return Base64.getEncoder().encodeToString(encVal);
    }

    public static String decrypt(String encrypted) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decoded = Base64.getDecoder().decode(encrypted);
        byte[] decVal = cipher.doFinal(decoded);
        return new String(decVal);
    }

    public static void main(String[] args) {
        String filePath = "employees_encrypted.csv";

        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            pw.println("ID,Name,Salary,Email");

            String[][] data = {
                    {"1", "Alice", "60000", "alice@example.com"},
                    {"2", "Bob", "55000", "bob@example.com"},
                    {"3", "Charlie", "70000", "charlie@example.com"}
            };

            for (String[] emp : data) {
                String encryptedSalary = encrypt(emp[2]);
                String encryptedEmail = encrypt(emp[3]);
                pw.println(emp[0] + "," + emp[1] + "," + encryptedSalary + "," + encryptedEmail);
            }

            System.out.println("✅ Encrypted data written to: " + filePath);
        } catch (Exception e) {
            System.out.println("❌ Error writing CSV: " + e.getMessage());
        }

        // Step 2: Read and decrypt the CSV
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Skip header
            System.out.println("\n🔓 Decrypted Employee Records:");
            System.out.println("-----------------------------");

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                String name = parts[1];
                String salary = decrypt(parts[2]);
                String email = decrypt(parts[3]);

                System.out.println("ID    : " + id);
                System.out.println("Name  : " + name);
                System.out.println("Salary: " + salary);
                System.out.println("Email : " + email);
                System.out.println("-----------------------------");
            }
        } catch (Exception e) {
            System.out.println("❌ Error reading CSV: " + e.getMessage());
        }
    }
}
