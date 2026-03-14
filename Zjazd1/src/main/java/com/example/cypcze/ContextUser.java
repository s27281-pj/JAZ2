package com.example.cypcze;

import org.springframework.context.ApplicationContext;



public class ContextUser {
    private final ApplicationContext context;

    public ContextUser(ApplicationContext context) {
        this.context = context;
    }

    public void makeBeansAndCall() {
        ComponentA a = context.getBean("componentA", ComponentA.class);
        ComponentB b = context.getBean("componentB", ComponentB.class);

        a.helloFromA();
        b.helloFromB();
    }
}