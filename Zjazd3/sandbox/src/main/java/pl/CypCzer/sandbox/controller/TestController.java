package pl.CypCzer.sandbox.controller;

import pl.CypCzer.sandbox.model.Car;
import pl.CypCzer.sandbox.service.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok(testService.sayHello());
    }

    @GetMapping("/model")
    public ResponseEntity<Car> getCarModel() {
        return ResponseEntity.ok(testService.getCarModel());
    }

    @GetMapping("/hello/{someValue}")
    public ResponseEntity<String> getDynamicValue(@PathVariable String someValue) {
        return ResponseEntity.ok(testService.getDynamicValue(someValue));
    }

    @GetMapping("/hello-param")
    public ResponseEntity<String> getWithParam(@RequestParam(name = "reqParam") String someValue) {
        return ResponseEntity.ok(testService.getWithParam(someValue));
    }

    @PostMapping("/model")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        return ResponseEntity.ok(testService.createCar(car));
    }
}