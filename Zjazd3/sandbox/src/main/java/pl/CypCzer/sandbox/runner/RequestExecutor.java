package pl.CypCzer.sandbox.runner;

import pl.CypCzer.sandbox.service.FacadeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RequestExecutor implements CommandLineRunner {

    private final FacadeService facadeService;

    public RequestExecutor(FacadeService facadeService) {
        this.facadeService = facadeService;
    }

    @Override
    public void run(String... args) {
        String request = "test-request";
        String result = facadeService.execute(request);
        System.out.println(result);
    }
}