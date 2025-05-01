import java.io.*;
import java.util.*;

public class ReadLargeCSV {
    public static void main(String[] args) {
        String filePath = "large_file.csv";
        int chunkSize = 100;
        int totalCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String header = br.readLine();
            List<String> chunk = new ArrayList<>();
            String line;

            while ((line = br.readLine()) != null) {
                chunk.add(line);

                if (chunk.size() == chunkSize) {
                    totalCount += chunk.size();
                    System.out.println("Processed " + totalCount + " records.");
                    chunk.clear();
                }
            }

            if (!chunk.isEmpty()) {
                totalCount += chunk.size();
                System.out.println("Processed " + totalCount + " records.");
            }

            System.out.println("✅ Done reading entire file.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
