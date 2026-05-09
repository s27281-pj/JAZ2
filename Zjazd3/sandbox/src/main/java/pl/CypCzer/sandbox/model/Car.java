package pl.CypCzer.sandbox.model;

public class Car {
    private String brand;
    private String model;
    private int year;

    // Konstruktor bezargumentowy Zadanie 3.5
    public Car() {
    }

    // Konstruktor
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    // Gettery i Settery 3.5
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}