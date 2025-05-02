import java.lang.reflect.Field;

public class JSONRepresentation {

    public static String toJson(Object obj) {
        StringBuilder json = new StringBuilder("{");
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                json.append("\"").append(fields[i].getName()).append("\": ")
                        .append("\"").append(fields[i].get(obj)).append("\"");
                if (i < fields.length - 1) {
                    json.append(", ");
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        json.append("}");
        return json.toString();
    }

    public static class Person {
        public String name;
        public int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    public static void main(String[] args) {
        Person p = new Person("Alice", 30);
        String jsonRepresentation = toJson(p);
        System.out.println(jsonRepresentation);
    }
}
