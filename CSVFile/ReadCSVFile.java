import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class ReadCSVFile {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\deepi\\OneDrive\\Desktop\\javaprac\\Week05\\Day1_training\\CSVFile\\students.csv";
        String line;

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found at: " + filePath);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();

            System.out.println("Student Details:");
            System.out.println("----------------");

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                String id = data[0];
                String name = data[1];
                String age = data[2];
                String marks = data[3];

                System.out.println("ID    : " + id);
                System.out.println("Name  : " + name);
                System.out.println("Age   : " + age);
                System.out.println("Marks : " + marks);
                System.out.println("----------------");
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
