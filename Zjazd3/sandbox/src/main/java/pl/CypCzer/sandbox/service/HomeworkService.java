package pl.CypCzer.sandbox.service;

import pl.CypCzer.sandbox.model.Car;
import org.springframework.stereotype.Service;

@Service
public class HomeworkService {

    public Car getByPath(String value) {
        return new Car(value, "FromPath", 2024);
    }

    public Car getByParam(String brand) {
        return new Car(brand, "FromParam", 2024);
    }

    public Car postCar(Car car) {
        return car;
    }

    public Car putCar(String id, Car car) {
        car.setModel(car.getModel() + " (ID: " + id + ")");
        return car;
    }

    public void deleteCar(String id) {
        System.out.println("Usuwanie zasobu o ID: " + id);
    }
}