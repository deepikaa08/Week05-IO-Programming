import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class JSONvalidation {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Load and validate JSON from file
            JsonNode jsonNode = mapper.readTree(new File("data.json"));
            System.out.println("✅ JSON is valid.");
            System.out.println("Parsed JSON:");
            System.out.println(jsonNode.toPrettyString());
        } catch (IOException e) {
            System.out.println("Invalid JSON: " + e.getMessage());
        }
    }
}
