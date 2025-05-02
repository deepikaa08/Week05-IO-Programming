import java.lang.reflect.Method;

class SampleClass {
    public void methodOne() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void methodTwo() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class MethodExecution {
    public static void main(String[] args) {
        try {
            SampleClass obj = new SampleClass();
            Method[] methods = SampleClass.class.getDeclaredMethods();

            for (Method method : methods) {
                long startTime = System.nanoTime();

                method.setAccessible(true);
                method.invoke(obj);

                long endTime = System.nanoTime();
                long duration = endTime - startTime;

                System.out.println("Execution time of " + method.getName() + ": " + duration + " nanoseconds");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
