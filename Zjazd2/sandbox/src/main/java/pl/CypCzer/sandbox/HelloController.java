package pl.CypCzer.sandbox;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final MyCustomProperties myCustomProperties;

    public HelloController(MyCustomProperties myCustomProperties) {
        this.myCustomProperties = myCustomProperties;
    }

    @GetMapping("/hello")
    public String hello() {
        return "loginUrl=" + myCustomProperties.getLoginUrl()
                + ", user=" + myCustomProperties.getCredentials().getUser()
                + ", password=" + myCustomProperties.getCredentials().getPassword();
    }
}