package pl.CypCzer.sandbox.service;

import pl.CypCzer.sandbox.config.MyCustomProperties;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    private final MyCustomProperties myCustomProperties;

    public HelloService(MyCustomProperties myCustomProperties) {
        this.myCustomProperties = myCustomProperties;
    }

    public String buildHelloMessage() {
        return "loginUrl=" + myCustomProperties.getLoginUrl()
                + ", user=" + myCustomProperties.getCredentials().getUser()
                + ", password=" + myCustomProperties.getCredentials().getPassword();
    }
}