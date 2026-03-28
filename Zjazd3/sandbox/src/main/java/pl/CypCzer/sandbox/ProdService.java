package pl.CypCzer.sandbox;

import org.springframework.stereotype.Service;

@Service
public class ProdService implements HandlerServiceInterface {

    @Override
    public String getEnvironment() {
        return "PROD";
    }

    @Override
    public String getMessage() {
        return "Hello from Prod";
    }
}