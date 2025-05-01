import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountRowsCSV {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\deepi\\OneDrive\\Desktop\\javaprac\\Week05\\Day1_training\\CSVFile\\employees.csv";
        String line;
        int rowCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                rowCount++;
            }
            System.out.println("Number of records (excluding header): " + rowCount);

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
