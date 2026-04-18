package pl.CypCzer.sandbox;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/homework")
public class HomeworkController {

    // 1. GET z PathVariable
    // Przykład: http://localhost:8080/homework/path/Audi
    @GetMapping("/path/{value}")
    public ResponseEntity<Car> getByPath(@PathVariable String value) {
        return ResponseEntity.ok(new Car(value, "FromPath", 2024));
    }

    // 2. GET z RequestParam
    // Przykład: http://localhost:8080/homework/param?brand=BMW
    @GetMapping("/param")
    public ResponseEntity<Car> getByParam(@RequestParam String brand) {
        return ResponseEntity.ok(new Car(brand, "FromParam", 2024));
    }

    // 3. POST z RequestBody
    // Zwraca dostarczone dane
    @PostMapping
    public ResponseEntity<Car> postCar(@RequestBody Car car) {
        return ResponseEntity.ok(car);
    }

    // 4. PUT z PathVariable i RequestBody
    // Przykład: http://localhost:8080/homework/update/123
    @PutMapping("/update/{id}")
    public ResponseEntity<Car> putCar(@PathVariable String id, @RequestBody Car car) {
        // Dodajemy ID do modelu, żeby pokazać, że PathVariable zadziałał
        car.setModel(car.getModel() + " (ID: " + id + ")");
        return ResponseEntity.ok(car);
    }

    // 5. DELETE z PathVariable
    // Zwraca status 200 bez body (ResponseEntity.ok().build())
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable String id) {
        System.out.println("Usuwanie zasobu o ID: " + id);
        return ResponseEntity.ok().build();
    }
}