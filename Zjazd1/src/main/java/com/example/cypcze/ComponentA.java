package com.example.cypcze;


public class ComponentA {
    private final ComponentB componentB;

    public ComponentA(ComponentB componentB) {
        this.componentB = componentB;
    }

    public String name() {
        return "ComponentA";
    }

    public void helloFromA() {
        System.out.println("ComponentA -> helloFromA()");
    }
}