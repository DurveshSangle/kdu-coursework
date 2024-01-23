package org.example;

import org.example.config.ScanConfig;
import org.example.runner.Runner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScanConfig.class);

        Runner runnerClass = context.getBean(Runner.class);
        runnerClass.run();
    }
}