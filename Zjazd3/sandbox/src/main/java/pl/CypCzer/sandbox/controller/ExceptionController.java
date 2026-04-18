    package pl.CypCzer.sandbox.config;

    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class ExceptionController {

        @GetMapping({"/exception", "/excpetion"})
        public String throwException() {
            throw new RuntimeException("To jest mój przykładowy błąd");
        }
    }