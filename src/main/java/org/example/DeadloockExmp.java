package org.example;

import static java.lang.Thread.sleep;

public class DeadloockExmp {
    public static void main(String[] args) {
        String monitor ="s";
        String monitor2 ="s2";
        new Thread(()->{
                synchronized (monitor) {
                    System.out.println(monitor +" синхронизован с потоком 1");
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (monitor2){
                        System.out.println(monitor2 + " синхронизирован с потоком 2 ");

                    }
                }
        }).start();
        new Thread(()->{
            synchronized (monitor2) {
                System.out.println(monitor2 +" синхронизован с потоком 1");
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (monitor){
                    System.out.println(monitor + " синхронизирован с потоком 2 ");

                }
            }
        }).start();

    }
}
