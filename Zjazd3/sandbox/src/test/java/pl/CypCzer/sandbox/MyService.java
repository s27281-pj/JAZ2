package pl.CypCzer.sandbox;

public class MyService {

    public String checkAge(int age) {
        if (age < 0) {
            return "Invalid age";
        }
        if (age < 18) {
            return "Minor";
        }
        return "Adult";
    }
}