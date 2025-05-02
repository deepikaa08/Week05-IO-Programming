import java.lang.reflect.Field;
import java.util.Map;

public class ObjectMap {

    public static class Person {
        public String name;
        public int age;
    }

    public static <T> T toObject(Class<T> clazz, Map<String, Object> properties) {
        try {
            T obj = clazz.getDeclaredConstructor().newInstance();
            for (Map.Entry<String, Object> entry : properties.entrySet()) {
                Field field = clazz.getDeclaredField(entry.getKey());
                field.setAccessible(true);
                field.set(obj, entry.getValue());
            }
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Map<String, Object> data = Map.of("name", "Alice", "age", 30);
        Person p = toObject(Person.class, data);
        System.out.println("Name: " + p.name + ", Age: " + p.age);
    }
}

