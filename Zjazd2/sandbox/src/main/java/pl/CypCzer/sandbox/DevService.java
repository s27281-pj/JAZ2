package pl.CypCzer.sandbox;

import org.springframework.stereotype.Service;

@Service
public class DevService implements HandlerServiceInterface {

    @Override
    public String getMessage() {
        return "Hello from Dev";
    }
}