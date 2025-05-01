import java.io.*;
import java.util.*;

class StudentDetails {
    String id, name, age, marks, grade;

    public StudentDetails(String id, String name, String age, String marks, String grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
        this.grade = grade;
    }

    public String toCSV() {
        return String.join(",", id, name, age, marks, grade);
    }
}

public class MergedCSVFiles {
    public static void main(String[] args) {
        String file1 = "C:\\Users\\deepi\\OneDrive\\Desktop\\javaprac\\Week05\\Day1_training\\CSVFile\\student1.csv";
        String file2 = "C:\\Users\\deepi\\OneDrive\\Desktop\\javaprac\\Week05\\Day1_training\\CSVFile\\student2.csv";
        String outputFile = "C:\\Users\\deepi\\OneDrive\\Desktop\\javaprac\\Week05\\Day1_training\\CSVFile\\mergedfile.csv";

        Map<String, String[]> student1Map = new HashMap<>(); // ID -> [Name, Age]
        Map<String, String[]> student2Map = new HashMap<>(); // ID -> [Marks, Grade]

        try (
                BufferedReader br1 = new BufferedReader(new FileReader(file1));
                BufferedReader br2 = new BufferedReader(new FileReader(file2));
                BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
        ) {
            // Skip headers
            br1.readLine();
            br2.readLine();

            // Read students1.csv
            String line;
            while ((line = br1.readLine()) != null) {
                String[] data = line.split(",");
                student1Map.put(data[0].trim(), new String[]{data[1].trim(), data[2].trim()});
            }

            // Read students2.csv
            while ((line = br2.readLine()) != null) {
                String[] data = line.split(",");
                student2Map.put(data[0].trim(), new String[]{data[1].trim(), data[2].trim()});
            }

            // Write header
            bw.write("ID,Name,Age,Marks,Grade");
            bw.newLine();

            // Merge based on ID
            for (String id : student1Map.keySet()) {
                if (student2Map.containsKey(id)) {
                    String[] s1 = student1Map.get(id);
                    String[] s2 = student2Map.get(id);

                    StudentDetails student = new StudentDetails(id, s1[0], s1[1], s2[0], s2[1]);
                    bw.write(student.toCSV());
                    bw.newLine();
                }
            }

            System.out.println("Merged data written to: " + outputFile);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
