import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.util.List;
import java.util.Map;

public class JSONtoCSV {
    public static void main(String[] args) {
        String jsonFilePath = "C:\\Users\\deepi\\OneDrive\\Desktop\\javaprac\\Week05\\Day1_training\\CSVFile\\students.json";
        String csvFilePath = "C:\\Users\\deepi\\OneDrive\\Desktop\\javaprac\\Week05\\Day1_training\\CSVFile\\students2.csv";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> students = objectMapper.readValue(new File(jsonFilePath), List.class);

            BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "Name", "Age", "Marks"));

            for (Map<String, Object> student : students) {
                csvPrinter.printRecord(student.get("id"), student.get("name"), student.get("age"), student.get("marks"));
            }

            csvPrinter.flush();
            writer.close();

            System.out.println("✅ JSON converted to CSV and saved at: " + csvFilePath);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
