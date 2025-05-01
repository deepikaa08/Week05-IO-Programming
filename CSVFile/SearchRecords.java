import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SearchRecords {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\deepi\\OneDrive\\Desktop\\javaprac\\Week05\\Day1_training\\CSVFile\\employees.csv";
        String line;
        boolean found = false;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter employee name to search: ");
        String searchName = scanner.nextLine().trim();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[1].trim();

                if (name.equalsIgnoreCase(searchName)) {
                    String department = data[2].trim();
                    String salary = data[3].trim();

                    System.out.println("Employee Found:");
                    System.out.println("Department: " + department);
                    System.out.println("Salary    : " + salary);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Employee '" + searchName + "' not found.");
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        scanner.close();
    }
}
