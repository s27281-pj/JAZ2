package pl.CypCzer.sandbox.controller;

import pl.CypCzer.sandbox.service.FacadeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final FacadeService facadeService;

    public MessageController(FacadeService facadeService) {
        this.facadeService = facadeService;
    }

    @GetMapping("/message")
    public String getMessage() {
        return facadeService.execute("rest-request");
    }
}