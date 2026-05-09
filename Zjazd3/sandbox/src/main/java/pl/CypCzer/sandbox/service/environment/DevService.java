package pl.CypCzer.sandbox.service.environment;

import org.springframework.stereotype.Service;

@Service
public class DevService implements HandlerServiceInterface {

    @Override
    public String getEnvironment() {
        return "DEV";
    }

    @Override
    public String getMessage() {
        return "Hello from Dev";
    }
}