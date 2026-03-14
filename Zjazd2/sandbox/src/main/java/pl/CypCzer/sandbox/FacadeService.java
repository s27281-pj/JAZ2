package pl.CypCzer.sandbox;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FacadeService {

    private final DevService devService;
    private final QaService qaService;
    private final ProdService prodService;

    @Value("${app.environment}")
    private String environment;

    public FacadeService(DevService devService, QaService qaService, ProdService prodService) {
        this.devService = devService;
        this.qaService = qaService;
        this.prodService = prodService;
    }

    public String execute(String input) {
        switch (environment.toUpperCase()) {
            case "DEV":
                return devService.getMessage() + " | request: " + input;
            case "QA":
                return qaService.getMessage() + " | request: " + input;
            case "PROD":
                return prodService.getMessage() + " | request: " + input;
            default:
                return "Unknown environment: " + environment + " | request: " + input;
        }
    }
}