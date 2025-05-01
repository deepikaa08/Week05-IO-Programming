import java.io.*;
import java.util.*;

public class UpdateITSalary {
    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\deepi\\OneDrive\\Desktop\\javaprac\\Week05\\Day1_training\\CSVFile\\employees.csv";
        String outputFilePath = "C:\\Users\\deepi\\OneDrive\\Desktop\\javaprac\\Week05\\Day1_training\\CSVFile\\updatedemployees.csv";  // New file with updated salaries
        String line;

        try (
                BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
                BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))
        ) {
            // Read and write the header
            String header = br.readLine();
            bw.write(header);
            bw.newLine();

            // Process each line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                String department = data[2].trim();
                double salary = Double.parseDouble(data[3].trim());

                if (department.equalsIgnoreCase("IT")) {
                    // Increase salary by 10%
                    salary *= 1.10;
                    data[3] = String.format("%.2f", salary);
                }

                // Write updated record to the new file
                bw.write(String.join(",", data));
                bw.newLine();
            }

            System.out.println("Salaries updated and saved to " + outputFilePath);

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
