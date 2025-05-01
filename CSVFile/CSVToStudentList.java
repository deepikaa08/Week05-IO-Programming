import java.io.*;
import java.util.*;

class Student {
    private String id;
    private String name;
    private int age;
    private int marks;

    public Student(String id, String name, int age, int marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student[ID=" + id + ", Name=" + name + ", Age=" + age + ", Marks=" + marks + "]";
    }
}

public class CSVToStudentList {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\deepi\\OneDrive\\Desktop\\javaprac\\Week05\\Day1_training\\CSVFile\\students.csv";
        List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                String id = data[0].trim();
                String name = data[1].trim();
                int age = Integer.parseInt(data[2].trim());
                int marks = Integer.parseInt(data[3].trim());

                Student student = new Student(id, name, age, marks);
                students.add(student);
            }

            System.out.println("Student Objects:");
            for (Student s : students) {
                System.out.println(s);
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
