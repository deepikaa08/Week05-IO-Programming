import java.lang.reflect.Method;
import java.util.Scanner;

class MathOperations {
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }
}

public class DynamicMethodCall {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            MathOperations obj = new MathOperations();
            Class<?> cls = obj.getClass();

            System.out.print("Enter method name (add, subtract, multiply): ");
            String methodName = scanner.nextLine();

            System.out.print("Enter first number: ");
            int x = scanner.nextInt();
            System.out.print("Enter second number: ");
            int y = scanner.nextInt();

            Method method = cls.getMethod(methodName, int.class, int.class);
            Object result = method.invoke(obj, x, y);

            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        scanner.close();
    }
}
