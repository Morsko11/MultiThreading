package org.example;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExmp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 20; i++) {
            executorService.submit(new MyThread("A"));
            executorService.submit(new MyThread("B"));
            executorService.submit(new MyThread("C"));
            executorService.submit(new MyThread("D"));
        }
        executorService.shutdown();
    }
}

class MyThread extends Thread {
    String name;

    public MyThread(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 6; i++) {
            System.out.println(name + " " + i);
        }
    }
}