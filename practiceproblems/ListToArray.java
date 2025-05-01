import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class ListToArray {
    public static void main(String[] args) {
        List<CarModel> cars = new ArrayList<>();
        cars.add(new CarModel("Toyota", "Camry", 2020));
        cars.add(new CarModel("Honda", "Civic", 2021));
        cars.add(new CarModel("Ford", "Mustang", 2022));

        Gson gson = new Gson();
        String jsonArray = gson.toJson(cars);
        System.out.println(jsonArray);
    }
}

class CarModel {
    private String brand;
    private String model;
    private int year;

    public CarModel(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }
}
