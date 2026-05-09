package pl.CypCzer.sandbox.controller;

import pl.CypCzer.sandbox.service.ExceptionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    private final ExceptionService exceptionService;

    public ExceptionController(ExceptionService exceptionService) {
        this.exceptionService = exceptionService;
    }

    @GetMapping({"/exception", "/excpetion"})
    public String throwException() {
        exceptionService.throwExampleException();
        return "never happens";
    }
}