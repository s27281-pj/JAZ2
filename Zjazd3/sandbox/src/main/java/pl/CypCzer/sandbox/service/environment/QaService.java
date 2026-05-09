package pl.CypCzer.sandbox.service.environment;

import org.springframework.stereotype.Service;

@Service
public class QaService implements HandlerServiceInterface {

    @Override
    public String getEnvironment() {
        return "QA";
    }

    @Override
    public String getMessage() {
        return "Hello from QA";
    }
}