package pl.CypCzer.sandbox.service;

import pl.CypCzer.sandbox.service.environment.HandlerServiceInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacadeService {

    private final Map<String, HandlerServiceInterface> serviceMap = new HashMap<>();

    @Value("${app.environment:DEV}")
    private String environment;

    public FacadeService(List<HandlerServiceInterface> services) {
        for (HandlerServiceInterface service : services) {
            serviceMap.put(service.getEnvironment().toUpperCase(), service);
        }
    }

    public String execute(String input) {
        String selectedEnvironment = environment.toUpperCase();
        HandlerServiceInterface service = serviceMap.getOrDefault(selectedEnvironment, serviceMap.get("DEV"));

        if (service == null) {
            return "No service available for environment: " + selectedEnvironment + " | request: " + input;
        }

        return service.getMessage() + " | request: " + input;
    }
}