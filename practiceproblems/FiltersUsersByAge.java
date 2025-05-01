import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;

public class FiltersUsersByAge {
    public static void main(String[] args) {
        String filePath = "users.json";
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(filePath)) {
            JsonArray usersArray = gson.fromJson(reader, JsonArray.class);

            System.out.println("Users older than 25:");
            for (int i = 0; i < usersArray.size(); i++) {
                JsonObject user = usersArray.get(i).getAsJsonObject();
                int age = user.get("age").getAsInt();
                if (age > 25) {
                    System.out.println(user);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
        }
    }
}
