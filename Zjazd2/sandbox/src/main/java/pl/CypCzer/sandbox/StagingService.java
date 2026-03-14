package pl.CypCzer.sandbox;

import org.springframework.stereotype.Service;

@Service
public class StagingService implements HandlerServiceInterface {

    @Override
    public String getEnvironment() {
        return "STAGING";
    }

    @Override
    public String getMessage() {
        return "Hello from Staging";
    }
}