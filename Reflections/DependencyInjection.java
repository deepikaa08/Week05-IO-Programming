import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Inject {}

class ServiceA {
    public void execute() {
        System.out.println("ServiceA executed.");
    }
}

class ServiceB {
    public void execute() {
        System.out.println("ServiceB executed.");
    }
}

class Client {
    @Inject
    private ServiceA serviceA;

    @Inject
    private ServiceB serviceB;

    public void run() {
        serviceA.execute();
        serviceB.execute();
    }
}

class DIContainer {
    private Map<Class<?>, Object> serviceRegistry = new HashMap<>();

    public void register(Class<?> serviceClass) throws IllegalAccessException, InstantiationException {
        Object serviceInstance = serviceClass.newInstance();
        serviceRegistry.put(serviceClass, serviceInstance);
    }

    public void injectDependencies(Object target) throws IllegalAccessException {
        Class<?> clazz = target.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                Object dependency = serviceRegistry.get(field.getType());
                if (dependency != null) {
                    field.set(target, dependency);
                }
            }
        }
    }
}

public class DependencyInjection {
    public static void main(String[] args) throws Exception {
        DIContainer container = new DIContainer();

        container.register(ServiceA.class);
        container.register(ServiceB.class);

        Client client = new Client();
        container.injectDependencies(client);

        client.run();
    }
}
