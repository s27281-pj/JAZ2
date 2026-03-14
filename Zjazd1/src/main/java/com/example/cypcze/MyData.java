package com.example.cypcze;

public class MyData {
    private String name;
    private int number;

    public MyData(String name, int number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return "MyData{name='" + name + "', number=" + number + "}";
    }
}