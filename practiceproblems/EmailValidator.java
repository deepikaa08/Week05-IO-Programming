import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class EmailValidator {
    public static void main(String[] args) throws IOException {
        String schemaPath = "email_schema.json";
        String jsonDataPath = "data.json";

        ObjectMapper objectMapper = new ObjectMapper();


        JsonNode schemaNode = objectMapper.readTree(new File(schemaPath));
        JsonNode dataNode = objectMapper.readTree(new File(jsonDataPath));

        JsonSchemaFactory factory = JsonSchemaFactory.getInstance();
        JsonSchema schema = factory.getSchema(schemaNode);

        Set<ValidationMessage> validationResult = schema.validate(dataNode);

        if (validationResult.isEmpty()) {
            System.out.println("Email is valid.");
        } else {
            System.out.println("Email is invalid.");
            validationResult.forEach(msg -> System.out.println(msg.getMessage()));
        }
    }
}
