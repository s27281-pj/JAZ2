package pl.CypCzer.sandbox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyServiceTest {

    private final MyService myService = new MyService();

    @Test
    void shouldReturnInvalidAgeForNegativeValue() {
        assertEquals("Invalid age", myService.checkAge(-1));
    }

    @Test
    void shouldReturnMinorForAgeBelow18() {
        assertEquals("Minor", myService.checkAge(15));
    }

    @Test
    void shouldReturnAdultForAge18AndMore() {
        assertEquals("Adult", myService.checkAge(18));
    }
}