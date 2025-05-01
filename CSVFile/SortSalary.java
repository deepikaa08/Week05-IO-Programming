import java.io.*;
import java.util.*;

class Employee {
    String id, name, department;
    double salary;

    Employee(String id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String toString() {
        return String.format("ID: %s | Name: %s | Dept: %s | Salary: %.2f",
                id, name, department, salary);
    }
}

public class SortSalary {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\deepi\\OneDrive\\Desktop\\javaprac\\Week05\\Day1_training\\CSVFile\\employees.csv";
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0].trim();
                String name = data[1].trim();
                String dept = data[2].trim();
                double salary = Double.parseDouble(data[3].trim());

                employees.add(new Employee(id, name, dept, salary));
            }

            employees.sort((e1, e2) -> Double.compare(e2.salary, e1.salary));

            System.out.println("Top 5 Highest Paid Employees:");
            System.out.println("-----------------------------");
            for (int i = 0; i < Math.min(5, employees.size()); i++) {
                System.out.println(employees.get(i));
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
