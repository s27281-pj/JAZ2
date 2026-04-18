package pl.CypCzer.sandbox;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    // Zadanie: Podstawowy Hello World
    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello world");
    }

    // Zadanie 3.2: Zwracanie obiektu klasy Car (GET)
    @GetMapping("/model")
    public ResponseEntity<Car> getCarModel() {
        Car myCar = new Car("Tesla", "Model 3", 2024);
        return ResponseEntity.ok(myCar);
    }

    // Zadanie 3.3: Zmienna w ścieżce (Path Variable)
    // URL: localhost:8080/test/hello/jakasWartosc
    @GetMapping("/hello/{someValue}")
    public ResponseEntity<String> getDynamicValue(@PathVariable String someValue) {
        return ResponseEntity.ok(someValue);
    }

    // Zadanie 3.4: Parametr zapytania (Request Parameter)
    // URL: localhost:8080/test/hello-param?reqParam=jakasWartosc
    @GetMapping("/hello-param")
    public ResponseEntity<String> getWithParam(@RequestParam(name = "reqParam") String someValue) {
        return ResponseEntity.ok(someValue);
    }

    // Zadanie 3.5: Przyjmowanie obiektu w Body (POST)
    // URL: localhost:8080/test/model
    @PostMapping("/model")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        // Opcjonalna modyfikacja dla sprawdzenia czy działa
        if (car.getBrand() != null) {
            car.setBrand(car.getBrand() + " (zmieniony przez POST)");
        }
        return ResponseEntity.ok(car);
    }
}