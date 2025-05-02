import java.lang.reflect.Field;

class Configuration {
    private static String API_KEY = "ORIGINAL_KEY";
}

public class ModifyStaticField {
    public static void main(String[] args) {
        try {
            Class<?> cls = Configuration.class;
            Field field = cls.getDeclaredField("API_KEY");
            field.setAccessible(true);
            field.set(null, "NEW_KEY");
            String updatedKey = (String) field.get(null);
            System.out.println("Updated API_KEY: " + updatedKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
