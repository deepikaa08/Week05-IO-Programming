import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;

public class ReadJSONField {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("C:\\Users\\deepi\\OneDrive\\Desktop\\javaprac\\Week05\\Day2_training\\practiceproblems\\data.json")) {
            // Parse the JSON file into a JsonObject
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

            // Extract specific fields
            String name = jsonObject.get("name").getAsString();
            String email = jsonObject.get("email").getAsString();

            // Print the extracted values
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
