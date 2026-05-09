package pl.CypCzer.sandbox.controller;

import pl.CypCzer.sandbox.model.Car;
import pl.CypCzer.sandbox.service.HomeworkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/homework")
public class HomeworkController {

    private final HomeworkService homeworkService;

    public HomeworkController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @GetMapping("/path/{value}")
    public ResponseEntity<Car> getByPath(@PathVariable String value) {
        return ResponseEntity.ok(homeworkService.getByPath(value));
    }

    @GetMapping("/param")
    public ResponseEntity<Car> getByParam(@RequestParam String brand) {
        return ResponseEntity.ok(homeworkService.getByParam(brand));
    }

    @PostMapping
    public ResponseEntity<Car> postCar(@RequestBody Car car) {
        return ResponseEntity.ok(homeworkService.postCar(car));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Car> putCar(@PathVariable String id, @RequestBody Car car) {
        return ResponseEntity.ok(homeworkService.putCar(id, car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable String id) {
        homeworkService.deleteCar(id);
        return ResponseEntity.ok().build();
    }
}