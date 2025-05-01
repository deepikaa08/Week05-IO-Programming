import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class DBToCSV {
    public static void main(String[] args) {

        String jdbcURL = "jdbc:mysql://localhost:3306/company";
        String username = "root";
        String password = "Root";

        String csvFile = "C:\\Users\\deepi\\OneDrive\\Desktop\\javaprac\\Week05\\Day1_training\\CSVFile\\database.csv";

        String sql = "SELECT id, name, department, salary FROM employees";

        try (

                Connection connection = DriverManager.getConnection(jdbcURL, username, password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))
        ) {
            writer.write("Employee ID,Name,Department,Salary");
            writer.newLine();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                double salary = resultSet.getDouble("salary");


                String line = id + "," + name + "," + department + "," + salary;
                writer.write(line);
                writer.newLine();
            }

            System.out.println("CSV Report generated: " + csvFile);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
}
