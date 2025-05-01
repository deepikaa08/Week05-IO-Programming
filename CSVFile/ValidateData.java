import java.io.*;
import java.util.regex.*;

public class ValidateData {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\deepi\\OneDrive\\Desktop\\javaprac\\Week05\\Day1_training\\CSVFile\\details.csv";

        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        Pattern emailPattern = Pattern.compile(emailRegex);

        String phoneRegex = "^\\d{10}$";
        Pattern phonePattern = Pattern.compile(phoneRegex);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            int lineNumber = 1;
            System.out.println("Checking for invalid records...\n");

            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] data = line.split(",");

                if (data.length < 4) {
                    System.out.println("Line " + lineNumber + ": Missing fields => " + line);
                    continue;
                }

                String id = data[0].trim();
                String name = data[1].trim();
                String email = data[2].trim();
                String phone = data[3].trim();

                boolean isEmailValid = emailPattern.matcher(email).matches();
                boolean isPhoneValid = phonePattern.matcher(phone).matches();

                if (!isEmailValid || !isPhoneValid) {
                    System.out.println("Invalid data at line " + lineNumber + ":");
                    System.out.println("  Data  => " + line);
                    if (!isEmailValid)
                        System.out.println("  Invalid Email: " + email);
                    if (!isPhoneValid)
                        System.out.println("  Invalid Phone: " + phone);
                    System.out.println();
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
