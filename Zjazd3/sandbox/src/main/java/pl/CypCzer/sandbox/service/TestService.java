package pl.CypCzer.sandbox.service;

import pl.CypCzer.sandbox.model.Car;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    public String sayHello() {
        return "Hello world";
    }

    public Car getCarModel() {
        return new Car("Tesla", "Model 3", 2024);
    }

    public String getDynamicValue(String someValue) {
        return someValue;
    }

    public String getWithParam(String someValue) {
        return someValue;
    }

    public Car createCar(Car car) {
        if (car.getBrand() != null) {
            car.setBrand(car.getBrand() + " (zmieniony przez POST)");
        }
        return car;
    }
}