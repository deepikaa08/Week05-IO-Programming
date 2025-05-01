import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class MergeJSONFiles {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Load both JSON files
            JsonNode json1 = mapper.readTree(new File("file1.json"));
            JsonNode json2 = mapper.readTree(new File("file2.json"));

            // Merge json2 into json1
            ObjectNode merged = (ObjectNode) json1;
            json2.fields().forEachRemaining(field -> merged.set(field.getKey(), field.getValue()));

            // Print the merged result
            System.out.println("Merged JSON:");
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(merged));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
