import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteCSVFile {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\deepi\\OneDrive\\Desktop\\javaprac\\Week05\\Day1_training\\CSVFile\\employees.csv";

        String[] header = {"ID", "Name", "Department", "Salary"};
        String[][] employeeData = {
                {"1", "John Doe", "HR", "55000"},
                {"2", "Jane Smith", "Engineering", "70000"},
                {"3", "Jim Brown", "Sales", "60000"},
                {"4", "Emily White", "Marketing", "65000"},
                {"5", "Michael Green", "Finance", "75000"}
        };

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(String.join(",", header));
            writer.newLine();
            for (String[] employee : employeeData) {
                writer.write(String.join(",", employee));
                writer.newLine();
            }

            System.out.println("Employee data has been written to " + filePath);

        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }
}
