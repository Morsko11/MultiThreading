package org.example;

import java.util.Calendar;
import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
     /*   ThreadImpl thread = null ;
        for (int i = 0; i < 6; i++) {
        thread = new ThreadImpl();
        thread.start();}
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("rttt");
        }*/


     /*   Thread threadImpl2 = new Thread(() -> {
            System.out.println("Имя потока " + Thread.currentThread().getName());
            try {
                sleep(200);
            } catch (InterruptedException e) {
                System.out.println("Нас разбудили ");
            }
            System.out.println("поток завершил работу " + Thread.currentThread().getName());
        });
        threadImpl2.start();
*/
        //2 способ
    /*    Runnable runnable = ()->{System.out.println("Имя потока " + Thread.currentThread().getName());
            try {
                sleep(200);
            } catch (InterruptedException e) {
                System.out.println("Нас разбудили ");
            }
            System.out.println("поток завершил работу " + Thread.currentThread().getName());
        };*/


        //3 cпособ
     /*  Thread thread = new Thread(() -> {
            System.out.println("Имя потока " + Thread.currentThread().getName());
            try {
                sleep(200);
            } catch (InterruptedException e) {
                System.out.println("Нас разбудили ");
            }
            System.out.println("поток завершил работу " + Thread.currentThread().getName());
        });*/

/*InteraptClass interaptClass = new InteraptClass();
interaptClass.start();

interaptClass.join();

interaptClass.flag = false;*/

     /*   InteraptClass interaptClass = new InteraptClass();
        interaptClass.start();
        sleep(555);
        interaptClass.interrupt();*/

     Semaphore semaphore = new Semaphore(1);

        SemaforaClass semaforaClass = new SemaforaClass(semaphore,new Counter(),"Name");
        SemaforaClass semaforaClass2 = new SemaforaClass(semaphore,new Counter(),"Oleg");
        SemaforaClass semaforaClass3 = new SemaforaClass(semaphore,new Counter(),"Aseasdasdasd");
        semaforaClass.start();
        semaforaClass2.start();
        semaforaClass3.start();


        System.out.println(Thread.currentThread().getName());
    }
}


class Counter {
    int x = 0;

}

class SemaforaClass extends Thread {
    private Semaphore semaphore;
    private Counter counter;
    private String name;

    public SemaforaClass(Semaphore semaphore, Counter counter, String name) {
        this.semaphore = semaphore;
        this.counter = counter;
        this.name = name;
    }

    public void run() {
        try {
            System.out.println(name+" ожидает разрешение");
            semaphore.acquire();
            counter.x=1;
            for (int i = 0; i < 5; i++) {
                System.out.println(name+" "+ counter.x);
                counter.x++;
                sleep(100);
            }



        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Освобождает разрешение");
        semaphore.release();

    }
}

class MeThread extends Thread {
    private Counter counter;

    public MeThread(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        synchronized (counter) {
            for (int i = 0; i < 100; i++) {
                counter.x++;
                try {
                    System.out.println(counter.x + " общее кол во " + Thread.currentThread().getName());
                    sleep(1);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}

class InteraptClass extends Thread {


    public void run() {
        int counter = 1;
        System.out.println("Начал работу");
        while (!isInterrupted()) {
            System.out.println(counter++);
            try {
                sleep(200);
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println("Закончил работу");
    }


}

class ThreadImpl3 implements Callable<String> {

    @Override
    public String call() throws Exception {
        return null;
    }
}

class ThreadImpl2 extends Thread implements Runnable {

    @Override
    public void run() {
        System.out.println("Имя потока " + Thread.currentThread().getName());
        try {
            sleep(200);
        } catch (InterruptedException e) {
            System.out.println("Нас разбудили ");
        }
        System.out.println("поток завершил работу " + Thread.currentThread().getName());
    }
}


class ThreadImpl extends Thread {
    public void run() {
        System.out.println("Имя потока " + Thread.currentThread().getName());
        try {
            sleep(200);
        } catch (InterruptedException e) {
            System.out.println("Нас разбудили ");
        }
        System.out.println("поток завершил работу " + Thread.currentThread().getName());
    }
}

