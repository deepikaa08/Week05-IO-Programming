import com.google.gson.Gson;

class Car {
    private String brand;
    private String model;
    private int year;

    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }
}

public class ObjecttoJSON {
    public static void main(String[] args) {
        Car car = new Car("Honda", "Civic", 2024);

        Gson gson = new Gson();
        String json = gson.toJson(car);

        System.out.println(json);
    }
}
