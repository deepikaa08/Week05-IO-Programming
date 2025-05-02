class Student {
    String name;
    int id;

    public Student() {
        name = "John Doe";
        id = 101;
    }

    public void display() {
        System.out.println("Name: " + name + ", ID: " + id);
    }
}

public class CreateObjectDynamically {
    public static void main(String[] args) {
        try {
            Class<?> cls = Class.forName("Student");
            Object obj = cls.getDeclaredConstructor().newInstance();
            Student student = (Student) obj;
            student.display();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
