package pl.CypCzer.sandbox.service;

import org.springframework.stereotype.Service;

@Service
public class ExceptionService {

    public void throwExampleException() {
        throw new RuntimeException("To jest mój przykładowy błąd");
    }
}