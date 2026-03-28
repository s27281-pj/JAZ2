package pl.CypCzer.sandbox;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello world");
    }

    // Zadanie 3.2
    @GetMapping("/model")
    public ResponseEntity<Car> getCarModel() {
        Car myCar = new Car("Tesla", "Model 3", 2024);
        return ResponseEntity.ok(myCar);
    }
}