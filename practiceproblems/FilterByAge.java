import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FilterByAge {
    public static void main(String[] args) {
        String json = "[\n" +
                "  {\"name\": \"Alice\", \"age\": 30},\n" +
                "  {\"name\": \"Bob\", \"age\": 22},\n" +
                "  {\"name\": \"Charlie\", \"age\": 28},\n" +
                "  {\"name\": \"Daisy\", \"age\": 24}\n" +
                "]";

        Gson gson = new Gson();

        // Convert JSON array to List<Person>
        Type listType = new TypeToken<List<Person>>() {}.getType();
        List<Person> people = gson.fromJson(json, listType);

        // Filter people with age > 25
        List<Person> filtered = new ArrayList<>();
        for (Person p : people) {
            if (p.age > 25) {
                filtered.add(p);
            }
        }

        // Print filtered people
        String resultJson = gson.toJson(filtered);
        System.out.println("Filtered (age > 25):");
        System.out.println(resultJson);
    }
}

// Helper class
class Person {
    String name;
    int age;
}
