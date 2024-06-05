package org.example;

import java.util.Scanner;

import static java.lang.Thread.sleep;

public class WaitinNotifyExmp {
    public static void main(String[] args) {
        System.out.println("Главный поток работает "+ Thread.currentThread().getName());
       Processing processing = new Processing();
        new Thread(()-> {
            try {
                processing.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(()-> {
            try {
                processing.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        System.out.println("Главный поток завершил работу "+ Thread.currentThread().getName());
    }
}


class Processing {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Метод produce работает");
            wait();
            System.out.println("Метод produce Возобновил работу");
        }

    }
    public void consume() throws  InterruptedException{
        Scanner scanner = new Scanner(System.in);
        sleep(500);
        synchronized (this){
            System.out.println(" Ждем оповещение");
            scanner.next();
            System.out.println("Оповещение пришло");
            notify();
            sleep(1000);

        }
    }
}
