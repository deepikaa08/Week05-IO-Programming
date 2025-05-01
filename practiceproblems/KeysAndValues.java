import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class KeysAndValues {
    public static void main(String[] args) {
        String filePath = "data.json"; // Replace with your actual file path
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(filePath)) {
            // Parse JSON into JsonObject
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            // Iterate through keys and print their values
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                System.out.println("Key: " + entry.getKey() + " | Value: " + entry.getValue());
            }
        } catch (IOException e) {
            System.out.println(" Error reading JSON file: " + e.getMessage());
        }
    }
}
