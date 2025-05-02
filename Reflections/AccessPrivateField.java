import java.lang.reflect.Field;

class Person {
    private int age;

    public Person() {
        this.age = 0;
    }
}

public class AccessPrivateField {
    public static void main(String[] args) {
        try {
            Person person = new Person();
            Class<?> cls = person.getClass();
            Field ageField = cls.getDeclaredField("age");
            ageField.setAccessible(true);
            ageField.set(person, 25);
            int ageValue = (int) ageField.get(person);
            System.out.println("Modified age: " + ageValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
